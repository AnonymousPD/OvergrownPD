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

package com.overgrownpixel.overgrownpixeldungeon.effects;

import com.overgrownpixel.overgrownpixeldungeon.R;
import com.overgrownpixel.overgrownpixeldungeon.tiles.DungeonTilemap;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;

public class CheckedCell extends Image {
	
	private float alpha;
	
	public CheckedCell( int pos ) {
		super( TextureCache.createSolid( Game.instance.getResources().getInteger(R.integer.checkedcell) ) );

		origin.set( 0.5f );
		
		point( DungeonTilemap.tileToWorld( pos ).offset(
			DungeonTilemap.SIZE / 2,
			DungeonTilemap.SIZE / 2 ) );
		
		alpha = 0.8f;
	}
	
	@Override
	public void update() {
		if ((alpha -= Game.elapsed) > 0) {
			alpha( alpha );
			scale.set( DungeonTilemap.SIZE * alpha );
		} else {
			killAndErase();
		}
	}
}
