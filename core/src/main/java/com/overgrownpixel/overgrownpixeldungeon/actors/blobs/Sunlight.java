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

package com.overgrownpixel.overgrownpixeldungeon.actors.blobs;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.effects.BlobEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.LightParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.Generator;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;

public class Sunlight extends Blob {
	
	@Override
	protected void evolve() {
		super.evolve();
		
		if (volume > 0) {
			int cell;
			for (int i = area.left; i < area.right; i++) {
				for (int j = area.top; j < area.bottom; j++) {
					cell = i + j*Dungeon.level.width();
					if (off[cell] > 0) {
					    if(Dungeon.level.plants.get(cell) == null){
                            Plant.Seed seed = (Plant.Seed) Generator.random(Generator.Category.SEED);
                            seed.couch(cell, null, false);
                            GameScene.updateMap( cell );
                        }
					}
				}
			}
			Dungeon.observe();
		}
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		
		emitter.start( LightParticle.FACTORY, 0.2f, 20 );
	}
}
