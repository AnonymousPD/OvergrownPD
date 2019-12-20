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

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.HalomethaneFire;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.HalomethaneBurning;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.TankcabbagePoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;

public class Tankcabbage extends Plant {

	{
		image = 47;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        GameScene.add( Blob.seed( enemy.pos, 1+2, HalomethaneFire.class ) );
    }

    @Override
    public void activate(Char ch) {
        GameScene.add( Blob.seed( ch.pos, 1+2, HalomethaneFire.class ) );
    }

    @Override
    public void activate() {
        GameScene.add( Blob.seed( pos, 1+2, HalomethaneFire.class ) );
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new TankcabbagePoisonParticle().getColor(), 10);
        Buff.affect(ch, HalomethaneBurning.class).reignite(ch);
    }

    @Override
    public Blob immunity() {
        return new HalomethaneFire();
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.NEWSEEDS_TANKCABBAGE;

			plantClass = Tankcabbage.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            Buff.affect(defender, HalomethaneBurning.class).reignite(defender);
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return TankcabbagePoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new TankcabbagePoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
