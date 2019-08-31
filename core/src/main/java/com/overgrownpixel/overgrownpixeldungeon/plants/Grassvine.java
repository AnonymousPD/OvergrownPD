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
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.GrassvinePoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;

public class Grassvine extends Plant {

	{
		image = 59;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        Level.set( enemy.pos, Terrain.HIGH_GRASS );
        GameScene.updateMap( enemy.pos );
    }

    @Override
    public void activate(Char ch) {
        Level.set( ch.pos,  Terrain.HIGH_GRASS );
        GameScene.updateMap( ch.pos );
    }

    @Override
    public void activate() {
        Level.set( pos,  Terrain.HIGH_GRASS );
        GameScene.updateMap( pos );
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_GRASSVINE;

			plantClass = Grassvine.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            Level.set( defender.pos,  Terrain.HIGH_GRASS );
            GameScene.updateMap( defender.pos );
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return GrassvinePoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new GrassvinePoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
