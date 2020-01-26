/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Overgrown Pixel Dungeon
 * Copyright (C) 2018-2019 Anon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.overgrownpixel.overgrownpixeldungeon.items.food;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Badges;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.Statistics;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Coughing;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Hunger;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Recharging;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.effects.SpellSprite;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndBag;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class Food extends Item {

	public static final float TIME_TO_EAT	= 3f;
    public static final float TIME_TO_SPICE	= 2f;
	
	public static final String AC_EAT	= "EAT";

    public static final String AC_SPICE	= "SPICE";
	
	public float energy = Hunger.HUNGRY;
	public String message = Messages.get(this, "eat_msg");

	public Plant.Seed seed = null;
    public static final String SEED	= "SPICE";

    protected WndBag.Mode mode = WndBag.Mode.SEED;
    protected String inventoryTitle = Messages.get(this, "inv_title");

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(SEED, seed);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        try {
            seed = (Plant.Seed) bundle.getClass(SEED).newInstance();
        } catch (Exception e){
            OvergrownPixelDungeon.reportException(e);
        }
        if(seed != null){
            name = Messages.get(this, "name") + " " + Messages.get(this, "spiced_with").toLowerCase() + " " + Messages.get(this.seed, "name");
        }
    }

    {
		stackable = true;
		image = ItemSpriteSheet.RATION;

		bones = true;

		defaultAction = AC_EAT;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_EAT );
        if(seed == null) actions.add( AC_SPICE );
		return actions;
	}

    @Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals( AC_EAT )) {

            if(hero.buff(Coughing.class) != null){
                GLog.n(Messages.get(this, "coughing"));
                return;
            }
			
			detach( hero.belongings.backpack );
			
			satisfy(hero);
			GLog.i( message );
			
			foodProc( hero );

			eatEffect( hero );
			
			hero.sprite.operate( hero.pos );
			hero.busy();
			SpellSprite.show( hero, SpellSprite.FOOD );
			Sample.INSTANCE.play( Assets.SND_EAT );
			
			hero.spend( TIME_TO_EAT );
			
			Statistics.foodEaten++;
			Badges.validateFoodEaten();
			
		}

        if (action.equals( AC_SPICE )) {
            GameScene.selectItem( itemSelector, mode, inventoryTitle );
        }
	}

    protected static WndBag.Listener itemSelector = new WndBag.Listener() {
        @Override
        public void onSelect( Item item ) {

            if (item == null) return;

            if(curItem != null){
                ((Food) curItem).seed = ((Plant.Seed) item);
                curUser.spend( TIME_TO_SPICE );
                item.detach(curUser.belongings.backpack);
                ((Food) curItem).name = Messages.get(curItem.getClass(), "name") + " " + Messages.get(curItem.getClass(), "spiced_with").toLowerCase() + " " + Messages.get(((Food) curItem).seed.getClass(), "name");
            } else {
                return;
            }
        }
    };

	public void eatEffect(Char hero){
        if(seed != null){
            try{
                Plant plant = seed.getPlantClass().newInstance();
                plant.spiceEffect(hero);
            } catch (Exception e){
                OvergrownPixelDungeon.reportException(e);
            }
        }
    }
	
	protected void satisfy( Hero hero ){
		Buff.affect(hero, Hunger.class).satisfy( energy );
	}
	
	public static void foodProc( Hero hero ){
		switch (hero.heroClass) {
			case WARRIOR:
				if (hero.HP < hero.HT) {
					hero.HP = Math.min( hero.HP + 5, hero.HT );
					hero.sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
				}
				break;
			case MAGE:
				//1 charge
				Buff.affect( hero, Recharging.class, 4f );
				ScrollOfRecharging.charge( hero );
				break;
			case ROGUE:
			case HUNTRESS:
				break;
		}
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
	
	@Override
	public int price() {
		return 10 * quantity;
	}
}
