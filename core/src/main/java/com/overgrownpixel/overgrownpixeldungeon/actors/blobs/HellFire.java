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

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.HellBurning;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.HellElemental;
import com.overgrownpixel.overgrownpixeldungeon.effects.BlobEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.Pushing;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.HellFlameParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.Heap;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.PathFinder;

public class HellFire extends Blob {

	@Override
	protected void evolve() {

		boolean[] flamable = Dungeon.level.flamable;
		int cell;
		int fire;
		
		Freezing freeze = (Freezing)Dungeon.level.blobs.get( Freezing.class );

		boolean observe = false;

		for (int i = area.left-1; i <= area.right; i++) {
			for (int j = area.top-1; j <= area.bottom; j++) {
				cell = i + j*Dungeon.level.width();
				if (cur[cell] > 0) {
					
					if (freeze != null && freeze.volume > 0 && freeze.cur[cell] > 0){
						freeze.clear(cell);
						off[cell] = cur[cell] = 0;
						continue;
					}

					burn( cell );

					fire = cur[cell] - 1;
					if (fire <= 0 && flamable[cell]) {

						Dungeon.level.destroy( cell );

						observe = true;
						GameScene.updateMap( cell );

					}

				} else if (freeze == null || freeze.volume <= 0 || freeze.cur[cell] <= 0) {

					if (flamable[cell]
							&& (cur[cell-1] > 0
							|| cur[cell+1] > 0
							|| cur[cell-Dungeon.level.width()] > 0
							|| cur[cell+Dungeon.level.width()] > 0)) {
						fire = 4;
						burn( cell );
						area.union(i, j);
					} else {
						fire = 0;
					}

				} else {
					fire = 0;
				}

				volume += (off[cell] = fire);
			}
		}

		if (observe) {
			Dungeon.observe();
		}
	}
	
	public static void burn( int pos ) {
		Char ch = Actor.findChar( pos );
		if (ch != null && !ch.isImmune(HellFire.class)) {
			Buff.affect( ch, HellBurning.class ).reignite( ch );
		}
		
		Heap heap = Dungeon.level.heaps.get( pos );
		if (heap != null) {
			heap.burn();

			HellElemental hellElemental = new HellElemental();
            hellElemental.HP = hellElemental.HT;
            hellElemental.pos = pos;

            GameScene.add( hellElemental );
            Actor.addDelayed( new Pushing( hellElemental, pos, pos ), -1f );

            hellElemental.sprite.alpha( 0 );
            hellElemental.sprite.parent.add( new AlphaTweener( hellElemental.sprite, 1, 0.15f ) );

            Sample.INSTANCE.play( Assets.SND_BURNING );
		}

		Plant plant = Dungeon.level.plants.get( pos );
		if (plant != null){
			plant.wither();
		}

        if(Dungeon.level.flora.get(pos) != null){
            Dungeon.level.flora.get(pos).wither();
        }

        for(int i : PathFinder.NEIGHBOURS8){
            if(Dungeon.level.flora.get(pos+i) != null){
                Dungeon.level.flora.get(pos+i).wither();
            }
        }

        if(Dungeon.level.butter.get(pos) != null){
            Dungeon.level.butter.get(pos).melt();
        }

        for(int i : PathFinder.NEIGHBOURS8){
            if(Dungeon.level.butter.get(pos+i) != null){
                Dungeon.level.butter.get(pos+i).melt();
            }
        }
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		emitter.pour( HellFlameParticle.FACTORY, 0.03f );
	}
	
	@Override
	public String tileDesc() {
		return Messages.get(this, "desc");
	}
}
