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

package com.overgrownpixel.overgrownpixeldungeon.items.artifacts;

import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Adrenaline;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Healing;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Luck;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Shield;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndBag;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class CursedCards extends Artifact {


	{
		image = ItemSpriteSheet.ARTIFACT_CARD1;

		levelCap = 10;

		charge = 0;
		partialCharge = 0;
		chargeCap = 10;

		defaultAction = AC_SHUFFLE;
	}

	public static final String AC_SHUFFLE = "SHUFFLE";
	public static final String AC_DRAW = "DRAW";

	protected WndBag.Mode mode = WndBag.Mode.FOOD;

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
        if (isEquipped( hero )){
            actions.add(AC_SHUFFLE);
            if(charge >= chargeCap && cursed){
                actions.add(AC_DRAW);
            }
        }
		return actions;
	}

	private Integer[] cards = new Integer[]{
	        ItemSpriteSheet.ARTIFACT_CARD1,
            ItemSpriteSheet.ARTIFACT_CARD2,
            ItemSpriteSheet.ARTIFACT_CARD3,
            ItemSpriteSheet.ARTIFACT_CARD4,
    };

	@Override
	public void execute( Hero hero, String action ) {

		super.execute(hero, action);

		if(action.equals(AC_SHUFFLE)){
		    image = Random.element(cards);
		    updateQuickslot();
		    if(cursed){
		        hero.damage((level()+1)*2, this);
		        charge(hero);
		        GLog.p(Messages.get(this, "charged"));
                hero.spend(1f);
            } else {
		        GLog.n(Messages.get(this, "not_cursed"));
            }
        } else if(action.equals(AC_DRAW)){
		    int img = 0;
		    if(image == ItemSpriteSheet.ARTIFACT_CARD1) img = 1;
            if(image == ItemSpriteSheet.ARTIFACT_CARD2) img = 2;
            if(image == ItemSpriteSheet.ARTIFACT_CARD3) img = 3;
            if(image == ItemSpriteSheet.ARTIFACT_CARD4) img = 4;
            switch (img){
                case 1:
                    GLog.p(Messages.get(this, "pikes"));
                    Buff.affect(hero, Adrenaline.class, Adrenaline.DURATION*(level()+1));
                    break;
                case 2:
                    GLog.p(Messages.get(this, "tiles"));
                    Buff.prolong(hero, Shield.class, Shield.DURATION*(level()+1));
                    break;
                case 3:
                    GLog.p(Messages.get(this, "hearts"));
                    Buff.affect(hero, Healing.class).setHeal((level()+1)*2, 0, 1);
                    break;
                case 4:
                    GLog.p(Messages.get(this, "clovers"));
                    Buff.prolong(hero, Luck.class, Luck.DURATION+(level()+1));
                    break;
            }
            int newimage = Random.element(cards);
            while(newimage == image){
                newimage = Random.element(cards);
            }
            image = newimage;
            charge = 0;
            updateQuickslot();
            hero.spend(1f);
        }
	}
	
	@Override
	public String desc() {
		String desc = super.desc();

        if (cursed) {
            desc += "\n\n" +Messages.get(this, "cursed");
        } else {
            desc += "\n\n" +Messages.get(this, "uncursed");
        }

		return desc;
	}

	@Override
	public void level(int value) {
		super.level(value);
		chargeCap = 10 + level();
	}

	@Override
	public Item upgrade() {
		super.upgrade();
		chargeCap = 10 + level();
		return this;
	}

	private static final String CARD = "card";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put( CARD, image );
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		image = bundle.getInt(CARD);
	}

    @Override
    protected ArtifactBuff passiveBuff() {
        return new CursedCards.Curse();
    }

    @Override
    public void charge(Hero target) {
        target.buff(CursedCards.Curse.class).charge();
    }

    public class Curse extends ArtifactBuff{
        public void charge() {
            charge = chargeCap;
        }
    }
}
