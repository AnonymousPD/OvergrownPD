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

package com.overgrownpixel.overgrownpixeldungeon.items.potions;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class PotionOfDirt extends Potion {

	{
		initials = 27;

		bones = true;
	}

    @Override
    public void shatter(int cell) {
        if (Dungeon.level.heroFOV[cell]) {
            setKnown();

            splash( cell );
            Sample.INSTANCE.play( Assets.SND_SHATTER );
        }

        for (int offset : PathFinder.NEIGHBOURS9){
            if (!Dungeon.level.solid[cell+offset] &&
                    (Dungeon.level.map[cell+offset] == Terrain.EMPTY ||
                            Dungeon.level.map[cell+offset] == Terrain.EMPTY_SP ||
                            Dungeon.level.map[cell+offset] == Terrain.EMPTY_DECO ||
                            Dungeon.level.map[cell+offset] == Terrain.EMBERS ||
                            Dungeon.level.map[cell+offset] == Terrain.GRASS ||
                            Dungeon.level.map[cell+offset] == Terrain.FURROWED_GRASS ||
                            Dungeon.level.map[cell+offset] == Terrain.HIGH_GRASS)) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(Terrain.SOIL_CORNWHEAT);
                arrayList.add(Terrain.SOIL_WATERWHEAT);
                arrayList.add(Terrain.SOIL_STRAWWHEAT);
                arrayList.add(Terrain.SOIL_GREENWHEAT);
                Level.set(cell+offset, Random.element(arrayList));
                GameScene.updateMap(cell+offset);
                if(Dungeon.level.plants.get(cell+offset) != null){
                    Dungeon.level.plants.get(cell+offset).trigger();
                }
                if(Dungeon.level.traps.get(cell+offset) != null){
                    Dungeon.level.traps.get(cell+offset).trigger();
                }
            }
        }
    }

    @Override
	public int price() {
		return isKnown() ? 50 * quantity : super.price();
	}
}
