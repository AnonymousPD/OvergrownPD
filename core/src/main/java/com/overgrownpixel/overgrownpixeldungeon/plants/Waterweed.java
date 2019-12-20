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
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.WaterOfAwareness;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.WaterOfHealth;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.WaterOfTransmutation;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.WellWater;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.WaterweedPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Waterweed extends Plant {

	{
		image = 32;
	}

    private static final Class<?>[] WATERS =
            {WaterOfAwareness.class, WaterOfHealth.class, WaterOfTransmutation.class};

    @Override
    public void attackProc(Char enemy, int damage) {
        if(Dungeon.level.map[enemy.pos] == Terrain.EMPTY || Dungeon.level.map[enemy.pos] == Terrain.GRASS){
            Level.set(enemy.pos, Terrain.WATER);
            GameScene.updateMap(enemy.pos);
        }
        if(Dungeon.level.map[enemy.pos] == Terrain.EMPTY_WELL){
            @SuppressWarnings("unchecked")
            Class<? extends WellWater> waterClass = (Class<? extends WellWater>) Random.element( WATERS );
            WellWater.seed(enemy.pos, 1, waterClass, Dungeon.level);
            Level.set(enemy.pos, Terrain.WELL);
            GameScene.updateMap(enemy.pos);
        }
    }

    @Override
    public void activate(Char ch) {
        for(int p : PathFinder.NEIGHBOURS8){
            if(Dungeon.level.map[ch.pos+p] == Terrain.EMPTY || Dungeon.level.map[ch.pos+p] == Terrain.GRASS){
                Level.set(ch.pos+p, Terrain.WATER);
                GameScene.updateMap(ch.pos+p);
            }
            if(Dungeon.level.map[ch.pos+p] == Terrain.EMPTY_WELL){
                @SuppressWarnings("unchecked")
                Class<? extends WellWater> waterClass = (Class<? extends WellWater>) Random.element( WATERS );
                WellWater.seed(ch.pos+p, 1, waterClass, Dungeon.level);
                Level.set(ch.pos+p, Terrain.WELL);
                GameScene.updateMap(ch.pos+p);
            }
        }
    }

    @Override
    public void activate() {
        for(int p : PathFinder.NEIGHBOURS8){
            if(Dungeon.level.map[pos+p] == Terrain.EMPTY || Dungeon.level.map[pos+p] == Terrain.GRASS){
                Level.set(pos+p, Terrain.WATER);
                GameScene.updateMap(pos+p);
            }
            if(Dungeon.level.map[pos+p] == Terrain.EMPTY_WELL){
                @SuppressWarnings("unchecked")
                Class<? extends WellWater> waterClass = (Class<? extends WellWater>) Random.element( WATERS );
                WellWater.seed(pos+p, 1, waterClass, Dungeon.level);
                Level.set(pos+p, Terrain.WELL);
                GameScene.updateMap(pos+p);
            }
        }
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new WaterweedPoisonParticle().getColor(), 10);
        for(int p : PathFinder.NEIGHBOURS8){
            if(Dungeon.level.map[ch.pos+p] == Terrain.EMPTY || Dungeon.level.map[ch.pos+p] == Terrain.GRASS){
                Level.set(ch.pos+p, Terrain.WATER);
                GameScene.updateMap(ch.pos+p);
            }
            if(Dungeon.level.map[ch.pos+p] == Terrain.EMPTY_WELL){
                @SuppressWarnings("unchecked")
                Class<? extends WellWater> waterClass = (Class<? extends WellWater>) Random.element( WATERS );
                WellWater.seed(ch.pos+p, 1, waterClass, Dungeon.level);
                Level.set(ch.pos+p, Terrain.WELL);
                GameScene.updateMap(ch.pos+p);
            }
        }
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.NEWSEEDS_WATERWEED;

			plantClass = Waterweed.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            new Waterweed().attackProc(defender, damage);
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return WaterweedPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new WaterweedPoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
