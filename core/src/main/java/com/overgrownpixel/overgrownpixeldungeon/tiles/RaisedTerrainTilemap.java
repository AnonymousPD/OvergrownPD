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

package com.overgrownpixel.overgrownpixeldungeon.tiles;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;

public class RaisedTerrainTilemap extends DungeonTilemap {
	
	public RaisedTerrainTilemap() {
		super(Dungeon.level.tilesTex());
		map( Dungeon.level.map, Dungeon.level.width() );
	}
	
	@Override
	protected int getTileVisual(int pos, int tile, boolean flat) {
		
		if (flat) return -1;
		
		if (tile == Terrain.HIGH_GRASS){
			return DungeonTileSheet.getVisualWithAlts(
					DungeonTileSheet.RAISED_HIGH_GRASS,
					pos) + 2;
		} else if (tile == Terrain.FURROWED_GRASS){
			return DungeonTileSheet.getVisualWithAlts(
					DungeonTileSheet.RAISED_FURROWED_GRASS,
					pos) + 2;
		} else if (tile == Terrain.SOIL_CORNWHEAT){
            return DungeonTileSheet.getVisualWithAlts(
                    DungeonTileSheet.RAISED_SOIL_CORNWHEAT_UPPER_PART,
                    pos);
        } else if (tile == Terrain.SOIL_STRAWWHEAT){
            return DungeonTileSheet.getVisualWithAlts(
                    DungeonTileSheet.RAISED_SOIL_STRAWWHEAT_UPPER_PART,
                    pos);
        } else if (tile == Terrain.SOIL_WATERWHEAT){
            return DungeonTileSheet.getVisualWithAlts(
                    DungeonTileSheet.RAISED_SOIL_WATERWHEAT_UPPER_PART,
                    pos);
        } else if (tile == Terrain.SOIL_GREENWHEAT){
            return DungeonTileSheet.getVisualWithAlts(
                    DungeonTileSheet.RAISED_SOIL_GREENWHEAT_UPPER_PART,
                    pos);
        }
		
		
		return -1;
	}
}
