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
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.GobgrapePoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.food.Grape;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.Random;

public class Gobgrape extends Plant {

	{
		image = 56;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        if(Random.Float() < 0.3f) Dungeon.level.drop(new Grape(), enemy.pos).sprite.drop(enemy.pos);
    }

    @Override
    public void activate(Char ch) {
        if(Random.Float() < 0.3f) Dungeon.level.drop(new Grape(), ch.pos).sprite.drop(ch.pos);
    }

    @Override
    public void activate() {
        if(Random.Float() < 0.3f) Dungeon.level.drop(new Grape(), pos).sprite.drop(pos);
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new GobgrapePoisonParticle().getColor(), 10);
        Dungeon.level.drop(new Grape(), ch.pos).sprite.drop(ch.pos);
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.NEWSEEDS_GOBGRAPE;

			plantClass = Gobgrape.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            if(Random.Float() < 0.3f) Dungeon.level.drop(new Grape(), defender.pos).sprite.drop(defender.pos);
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return GobgrapePoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new GobgrapePoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
