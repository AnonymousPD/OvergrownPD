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

package com.overgrownpixel.overgrownpixeldungeon.plants;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.BlandfruitPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.food.Blandfruit;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;

public class BlandfruitBush extends Plant {

	{
		image = 12;
	}

	@Override
	public void activate( Char ch ) {
		Dungeon.level.drop( new Blandfruit(), ch.pos ).sprite.drop();
	}

    @Override
    public void activate() {
        Dungeon.level.drop( new Blandfruit(), pos ).sprite.drop();
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new BlandfruitPoisonParticle().getColor(), 10);
    }

    @Override
    public Blob immunity() {
        return null;
    }

    @Override
    public void attackProc(Char enemy, int damage) {
        defaultProc(enemy, damage);
    }

	//This seed no longer drops, but has a sprite as it did drop prior to 0.7.0
	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.NEWSEEDS_FADELEAF;

			plantClass = Fadeleaf.class;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return BlandfruitPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new BlandfruitPoisonParticle();
        }
    }
}
