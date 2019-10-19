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

package com.overgrownpixel.overgrownpixeldungeon.levels.features;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.CropParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.food.crops.Cornwheatshaft;
import com.overgrownpixel.overgrownpixeldungeon.items.food.crops.Greenapplewheat;
import com.overgrownpixel.overgrownpixeldungeon.items.food.crops.Strawberrywheat;
import com.overgrownpixel.overgrownpixeldungeon.items.food.crops.Waterwheat;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;

public class Crops {

	public static void trample( Level level, int pos ) {

		if (level.map[pos] == Terrain.SOIL_CORNWHEAT){
            Level.set(pos, Terrain.SOIL);
            level.drop(new Cornwheatshaft(), pos).sprite.drop();
		} else if (level.map[pos] == Terrain.SOIL_STRAWWHEAT){
            Level.set(pos, Terrain.SOIL);
            level.drop(new Strawberrywheat(), pos).sprite.drop();
        } else if (level.map[pos] == Terrain.SOIL_WATERWHEAT){
            Level.set(pos, Terrain.SOIL);
            level.drop(new Waterwheat(), pos).sprite.drop();
        } else if (level.map[pos] == Terrain.SOIL_GREENWHEAT){
            Level.set(pos, Terrain.SOIL);
            level.drop(new Greenapplewheat(), pos).sprite.drop();
        }

		if (OvergrownPixelDungeon.scene() instanceof GameScene) {
			GameScene.updateMap(pos);

			CellEmitter.get(pos).burst(CropParticle.GENERAL, 4);
			if (Dungeon.level.heroFOV[pos]) Dungeon.observe();
		}
	}
}
