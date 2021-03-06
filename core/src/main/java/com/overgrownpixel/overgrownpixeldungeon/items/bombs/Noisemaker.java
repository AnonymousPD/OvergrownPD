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

package com.overgrownpixel.overgrownpixeldungeon.items.bombs;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class Noisemaker extends Bomb {
	
	{
		image = ItemSpriteSheet.NOISEMAKER;
	}
	
	
	@Override
	public boolean explodesDestructively() {
		return false;
	}
	
	@Override
	public void explode(int cell) {
		super.explode(cell);
		
		Buff.affect(Dungeon.hero, Noise.class).set(cell);
	}
	
	public static class Noise extends Buff {
		
		int floor;
		int cell;
		
		int left;
		
		public void set(int cell){
			floor = Dungeon.depth;
			this.cell = cell;
			left = 8;
		}
		
		@Override
		public boolean act() {
			
			if (Dungeon.depth == floor){
				if (Dungeon.level.heroFOV[cell]) {
					CellEmitter.center( cell ).start( Speck.factory( Speck.SCREAM ), 0.3f, 3 );
					Sample.INSTANCE.play( Assets.SND_ALERT );
				} else {
					CellEmitter.center( cell ).start( Speck.factory( Speck.SCREAM ), 0.3f, 3 );
					Sample.INSTANCE.play( Assets.SND_ALERT, 0.25f );
				}
				
				for (Mob m : Dungeon.level.mobs.toArray(new Mob[0])){
					if (m.state != m.HUNTING) {
						m.beckon(cell);
					}
				}
			}
			
			if (left > 0) {
				spend(TICK * 15f);
				left--;
			} else {
				detach();
			}
			
			return true;
		}
		
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
		}
		
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
		}
	}
	
	@Override
	public int price() {
		//prices of ingredients
		return quantity * (20 + 40);
	}
}
