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

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Cocoshield;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Cripple;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Cococlam;
import com.overgrownpixel.overgrownpixeldungeon.effects.Pushing;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.CocostuftPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.noosa.tweeners.AlphaTweener;

public class Cocostuft extends Plant {

	{
		image = 43;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        if (enemy instanceof Hero && ((Hero) enemy).subClass == HeroSubClass.WARDEN){
            Buff.prolong( enemy, Cocoshield.class, Cocoshield.DURATION );
        } else {
            Buff.prolong( enemy, Cripple.class, Cripple.DURATION );
        }
    }

    @Override
    public void activate(Char ch) {
        if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
            Buff.prolong( ch, Cocoshield.class, Cocoshield.DURATION );
        } else {
            Buff.prolong( ch, Cripple.class, Cripple.DURATION );
        }
    }

    @Override
    public void activate() {
        Cococlam cococlam = new Cococlam();
        cococlam.pos = pos;
        cococlam.spawn(Dungeon.depth);
        GameScene.add( cococlam );
        Actor.addDelayed( new Pushing( cococlam, pos, pos ), -1f );

        cococlam.sprite.alpha( 0 );
        cococlam.sprite.parent.add( new AlphaTweener( cococlam.sprite, 1, 0.15f ) );
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new CocostuftPoisonParticle().getColor(), 10);
        Buff.prolong( ch, Cocoshield.class, 2f );
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.NEWSEEDS_COCOSTUFT;

			plantClass = Cocostuft.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            Buff.prolong( defender, Cripple.class, Cripple.DURATION );
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return CocostuftPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new CocostuftPoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
