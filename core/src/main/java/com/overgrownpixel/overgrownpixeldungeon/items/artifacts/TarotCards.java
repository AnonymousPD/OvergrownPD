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

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Adjustment;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Aeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Art;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Chariot;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Death;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Devil;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Empress;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Fool;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Fortune;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Hangedman;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Hermit;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Hierophant;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Lovers;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Lust;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Magician;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Moon;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Priestess;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Stand;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Star;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Sun;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Tower;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.stands.Universe;
import com.overgrownpixel.overgrownpixeldungeon.effects.Pushing;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndBag;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class TarotCards extends Artifact {

    private Integer[] cards = new Integer[]{
            ItemSpriteSheet.TAROTCARD_FOOL, //1
            ItemSpriteSheet.TAROTCARD_MAGICIAN, //2
            ItemSpriteSheet.TAROTCARD_PRIESTESS, //3
            ItemSpriteSheet.TAROTCARD_EMPRESS, //4
            ItemSpriteSheet.TAROTCARD_EMPEROR, //5
            ItemSpriteSheet.TAROTCARD_HIEROPHANT, //6
            ItemSpriteSheet.TAROTCARD_LOVERS, //7
            ItemSpriteSheet.TAROTCARD_CHARIOT, //8
            ItemSpriteSheet.TAROTCARD_LUST, //9
            ItemSpriteSheet.TAROTCARD_HERMIT, //10
            ItemSpriteSheet.TAROTCARD_FORTUNE, //11
            ItemSpriteSheet.TAROTCARD_ADJUSTMENT, //12
            ItemSpriteSheet.TAROTCARD_HANGEDMAN, //13
            ItemSpriteSheet.TAROTCARD_DEATH, //14
            ItemSpriteSheet.TAROTCARD_ART, //15
            ItemSpriteSheet.TAROTCARD_DEVIL, //16
            ItemSpriteSheet.TAROTCARD_TOWER, //17
            ItemSpriteSheet.TAROTCARD_STAR, //18
            ItemSpriteSheet.TAROTCARD_MOON, //19
            ItemSpriteSheet.TAROTCARD_SUN, //20
            ItemSpriteSheet.TAROTCARD_AEON, //21
            ItemSpriteSheet.TAROTCARD_UNIVERSE, //22
    };

	{
		image = Random.element(cards);

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
            if(charge >= chargeCap){
                actions.add(AC_DRAW);
            }
        }
		return actions;
	}

	@Override
	public void execute( Hero hero, String action ) {

		super.execute(hero, action);

		if(action.equals(AC_SHUFFLE)){
		    image = Random.element(cards);
		    updateQuickslot();
            hero.damage((level()+1)*2, this);
            charge(hero);
            GLog.p(Messages.get(this, "charged"));
            hero.spend(1f);
        } else if(action.equals(AC_DRAW)){
		    int img = 0;
		    if(image == ItemSpriteSheet.TAROTCARD_FOOL) img = 1;
            if(image == ItemSpriteSheet.TAROTCARD_MAGICIAN) img = 2;
            if(image == ItemSpriteSheet.TAROTCARD_PRIESTESS) img = 3;
            if(image == ItemSpriteSheet.TAROTCARD_EMPRESS) img = 4;
            if(image == ItemSpriteSheet.TAROTCARD_EMPEROR) img = 5;
            if(image == ItemSpriteSheet.TAROTCARD_HIEROPHANT) img = 6;
            if(image == ItemSpriteSheet.TAROTCARD_LOVERS) img = 7;
            if(image == ItemSpriteSheet.TAROTCARD_CHARIOT) img = 8;
            if(image == ItemSpriteSheet.TAROTCARD_LUST) img = 9;
            if(image == ItemSpriteSheet.TAROTCARD_HERMIT) img = 10;
            if(image == ItemSpriteSheet.TAROTCARD_FORTUNE) img = 11;
            if(image == ItemSpriteSheet.TAROTCARD_ADJUSTMENT) img = 12;
            if(image == ItemSpriteSheet.TAROTCARD_HANGEDMAN) img = 13;
            if(image == ItemSpriteSheet.TAROTCARD_DEATH) img = 14;
            if(image == ItemSpriteSheet.TAROTCARD_ART) img = 15;
            if(image == ItemSpriteSheet.TAROTCARD_DEVIL) img = 16;
            if(image == ItemSpriteSheet.TAROTCARD_TOWER) img = 17;
            if(image == ItemSpriteSheet.TAROTCARD_STAR) img = 18;
            if(image == ItemSpriteSheet.TAROTCARD_MOON) img = 19;
            if(image == ItemSpriteSheet.TAROTCARD_SUN) img = 20;
            if(image == ItemSpriteSheet.TAROTCARD_AEON) img = 21;
            if(image == ItemSpriteSheet.TAROTCARD_UNIVERSE) img = 22;
            switch (img){
                case 1:
                    spawnTarotStand(new Fool());
                    GLog.p(Messages.get(this, "fool"));
                    break;
                case 2:
                    spawnTarotStand(new Magician());
                    GLog.p(Messages.get(this, "magician"));
                    break;
                case 3:
                    spawnTarotStand(new Priestess());
                    GLog.p(Messages.get(this, "priestess"));
                    break;
                case 4:
                    spawnTarotStand(new Empress());
                    GLog.p(Messages.get(this, "empress"));
                    break;
                case 5:
                    spawnTarotStand(new Empress());
                    GLog.p(Messages.get(this, "emperor"));
                    break;
                case 6:
                    spawnTarotStand(new Hierophant());
                    GLog.p(Messages.get(this, "hierophant"));
                    break;
                case 7:
                    spawnTarotStand(new Lovers());
                    GLog.p(Messages.get(this, "lovers"));
                    break;
                case 8:
                    spawnTarotStand(new Chariot());
                    GLog.p(Messages.get(this, "chariot"));
                    break;
                case 9:
                    spawnTarotStand(new Lust());
                    GLog.p(Messages.get(this, "lust"));
                    break;
                case 10:
                    spawnTarotStand(new Hermit());
                    GLog.p(Messages.get(this, "hermit"));
                    break;
                case 11:
                    spawnTarotStand(new Fortune());
                    GLog.p(Messages.get(this, "fortune"));
                    break;
                case 12:
                    spawnTarotStand(new Adjustment());
                    GLog.p(Messages.get(this, "adjustment"));
                    break;
                case 13:
                    spawnTarotStand(new Hangedman());
                    GLog.p(Messages.get(this, "hangedman"));
                    break;
                case 14:
                    spawnTarotStand(new Death());
                    GLog.p(Messages.get(this, "death"));
                    break;
                case 15:
                    spawnTarotStand(new Art());
                    GLog.p(Messages.get(this, "art"));
                    break;
                case 16:
                    spawnTarotStand(new Devil());
                    GLog.p(Messages.get(this, "devil"));
                    break;
                case 17:
                    spawnTarotStand(new Tower());
                    GLog.p(Messages.get(this, "tower"));
                    break;
                case 18:
                    spawnTarotStand(new Star());
                    GLog.p(Messages.get(this, "star"));
                    break;
                case 19:
                    spawnTarotStand(new Moon());
                    GLog.p(Messages.get(this, "moon"));
                    break;
                case 20:
                    spawnTarotStand(new Sun());
                    GLog.p(Messages.get(this, "sun"));
                    break;
                case 21:
                    spawnTarotStand(new Aeon());
                    GLog.p(Messages.get(this, "aeon"));
                    break;
                case 22:
                    spawnTarotStand(new Universe());
                    GLog.p(Messages.get(this, "universe"));
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

	private void spawnTarotStand(Stand stand){
	    for(Mob mob : Dungeon.level.mobs){
	        if(mob instanceof Stand){
	            int pos = mob.pos;
	            spriteDie(mob);
                stand.HP = stand.HT;
                stand.pos = pos;
                GameScene.add( stand );
                Actor.addDelayed( new Pushing( stand, pos, pos ), -1f );

                stand.sprite.alpha( 0 );
                stand.sprite.parent.add( new AlphaTweener( stand.sprite, 1, 0.15f ) );

                Sample.INSTANCE.play( Assets.SND_GHOST );
                return;
            }
        }
	    int pos = Dungeon.level.randomDestination();
	    while (!Dungeon.level.heroFOV[pos]){
	        pos = Dungeon.level.randomDestination();
        }
        stand.HP = stand.HT;
        stand.pos = pos;
        GameScene.add( stand );
        Actor.addDelayed( new Pushing( stand, pos, pos ), -1f );

        stand.sprite.alpha( 0 );
        stand.sprite.parent.add( new AlphaTweener( stand.sprite, 1, 0.15f ) );

        Sample.INSTANCE.play( Assets.SND_GHOST );
    }

    private void spriteDie(Char ch){
	    ch.destroy();
	    ch.sprite.die();
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
        return new TarotCards.Curse();
    }

    @Override
    public void charge(Hero target) {
        target.buff(TarotCards.Curse.class).charge();
    }

    public class Curse extends ArtifactBuff{
        public void charge() {
            charge = chargeCap;
        }
    }
}
