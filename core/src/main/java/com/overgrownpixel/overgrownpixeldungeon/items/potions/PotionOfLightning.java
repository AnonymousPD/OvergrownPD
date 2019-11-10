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

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.Lightning;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.SparkParticle;
import com.overgrownpixel.overgrownpixeldungeon.tiles.DungeonTilemap;
import com.overgrownpixel.overgrownpixeldungeon.utils.BArray;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class PotionOfLightning extends Potion {

	{
		initials = 42;

		bones = true;
	}

        private ArrayList<Char> affected = new ArrayList<>();

        ArrayList<Lightning.Arc> arcs = new ArrayList<>();

        private void arc( Char ch ) {

            affected.add( ch );

            int dist;
            if (Dungeon.level.water[ch.pos] && !ch.flying)
                dist = 2;
            else
                dist = 1;

            PathFinder.buildDistanceMap( ch.pos, BArray.not( Dungeon.level.solid, null ), dist );
            for (int i = 0; i < PathFinder.distance.length; i++) {
                if (PathFinder.distance[i] < Integer.MAX_VALUE){
                    Char n = Actor.findChar( i );
                    if (n == Dungeon.hero && PathFinder.distance[i] > 1)
                        //the hero is only zapped if they are adjacent
                        continue;
                    else if (n != null && !affected.contains( n )) {
                        arcs.add(new Lightning.Arc(ch.sprite.center(), n.sprite.center()));
                        arc(n);
                    }
                }
            }
        }

        @Override
        public void apply( Hero hero ) {
            affected.clear();
            arcs.clear();

            for(Mob mob : hero.visibleEnemiesList()){
                if (mob != null) {
                    arcs.add( new Lightning.Arc(curUser.sprite.center(), mob.sprite.center()));
                    arc(mob);
                }
            }

            ArrayList<Integer> cells = new ArrayList<>();
            for(int i = 10; i > 0; i--){
                int c = Random.Int(Dungeon.level.length());
                if(hero.fieldOfView[c] && !cells.contains(c)){
                    cells.add(c);
                }
            }

            for(int p : cells){
                arcs.add( new Lightning.Arc(curUser.sprite.center(), DungeonTilemap.raisedTileCenterToWorld(p)));
                CellEmitter.center( p ).burst( SparkParticle.FACTORY, 3 );
            }

            //don't want to wait for the effect before processing damage.
            curUser.sprite.parent.addToFront( new Lightning( arcs, null ) );
            //lightning deals less damage per-target, the more targets that are hit.
            float multipler = 0.4f + (0.6f/affected.size());

            for(Char ch : affected){
                if (Dungeon.level.water[ch.pos]) multipler = 1f;
                ch.damage(Math.round(ch.damageRoll() * multipler), this);
            }

            setKnown();
        }
	
	@Override
	public int price() {
		return isKnown() ? 50 * quantity : super.price();
	}
}
