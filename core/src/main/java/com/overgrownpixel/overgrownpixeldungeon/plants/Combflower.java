/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Overgrown Pixel Dungeon
 * Copyright (C) 2016-2019 Anon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This Program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without eben the implied warranty of
 * GNU General Public License for more details.
 *
 * You should have have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses>
 */

package com.overgrownpixel.overgrownpixeldungeon.plants;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Honeyed;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.SugarRush;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Bee;
import com.overgrownpixel.overgrownpixeldungeon.effects.Pushing;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.CombflowerPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.noosa.tweeners.AlphaTweener;

public class Combflower extends Plant {

	{
		image = 39;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        Buff.prolong(enemy, Honeyed.class, Honeyed.DURATION);
    }

    @Override
    public void activate(Char ch) {
	    float duration = SugarRush.DURATION;
	    if(ch instanceof Hero){
            duration = (((Hero)ch).subClass == HeroSubClass.WARDEN) ? SugarRush.DURATION * 2 : SugarRush.DURATION;
        }
        Buff.prolong(ch, SugarRush.class, duration);
    }

    @Override
    public void activate() {
        Bee bee = new Bee();
        bee.spawn( Dungeon.depth );
        bee.setPotInfo( pos, null );
        bee.HP = bee.HT;
        bee.pos = pos;

        GameScene.add( bee );
        Actor.addDelayed( new Pushing( bee, pos, pos ), -1f );

        bee.sprite.alpha( 0 );
        bee.sprite.parent.add( new AlphaTweener( bee.sprite, 1, 0.15f ) );

        Sample.INSTANCE.play( Assets.SND_BEE );
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_COMBFLOWER;

			plantClass = Combflower.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            Buff.prolong(defender, Honeyed.class, Honeyed.DURATION);
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return CombflowerPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new CombflowerPoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
