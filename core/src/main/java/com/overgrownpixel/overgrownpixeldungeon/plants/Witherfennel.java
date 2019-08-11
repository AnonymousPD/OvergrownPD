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
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Wither;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.WitherfennelPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.PathFinder;

public class Witherfennel extends Plant {

	{
		image = 30;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        Buff.prolong(enemy, Wither.class, Wither.DURATION);
    }

    @Override
    public void activate(Char ch) {
        Buff.prolong(ch, Wither.class, Wither.DURATION);
    }

    @Override
    public void activate() {
        for(int p : PathFinder.NEIGHBOURS8){
            if (Dungeon.level.map[pos+p] == Terrain.HIGH_GRASS || Dungeon.level.map[pos+p] == Terrain.GRASS){
                Level.set(pos+p, Terrain.FURROWED_GRASS);
            }
            if(Dungeon.level.plants.get(pos+p) != null){
                Dungeon.level.plants.get(pos+p).wither();
            }
        }
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_WITHERFENNEL;

			plantClass = Witherfennel.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            Buff.prolong(defender, Wither.class, Wither.DURATION);
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return WitherfennelPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new WitherfennelPoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
