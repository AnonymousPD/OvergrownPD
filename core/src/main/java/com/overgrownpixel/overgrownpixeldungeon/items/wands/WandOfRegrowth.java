/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
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

package com.overgrownpixel.overgrownpixeldungeon.items.wands;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Regrowth;
import com.overgrownpixel.overgrownpixeldungeon.effects.MagicMissile;
import com.overgrownpixel.overgrownpixeldungeon.items.Dewdrop;
import com.overgrownpixel.overgrownpixeldungeon.items.Generator;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Blooming;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.MagesStaff;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.plants.Starflower;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.ColorMath;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class WandOfRegrowth extends Wand {

	{
		image = ItemSpriteSheet.WAND_REGROWTH;

		collisionProperties = Ballistica.STOP_TERRAIN;
	}

	//the actual affected cells
	private HashSet<Integer> affectedCells;
	//the cells to trace growth particles to, for visual effects.
	private HashSet<Integer> visualCells;
	private int direction = 0;
	
	private int totChrgUsed = 0;
	
	@Override
	protected void onZap( Ballistica bolt ) {

		//ignore tiles which can't have anything grow in them.
		for (Iterator<Integer> i = affectedCells.iterator(); i.hasNext();) {
			int c = Dungeon.level.map[i.next()];
			if (!(c == Terrain.EMPTY ||
					c == Terrain.EMBERS ||
					c == Terrain.EMPTY_DECO ||
					c == Terrain.GRASS ||
					c == Terrain.HIGH_GRASS ||
					c == Terrain.FURROWED_GRASS)) {
				i.remove();
			}
		}

		float numPlants, numDews, numPods, numStars;
		
		int overLimit = totChrgUsed - chargeLimit(Dungeon.hero.lvl);

		int chrgUsed = chargesPerCast();
		//numbers greater than n*100% means n guaranteed plants, e.g. 210% = 2 plants w/10% chance for 3 plants.
		numPlants = 0.2f + chrgUsed*chrgUsed*0.020f; //scales from 22% to 220%
		numDews = 0.05f + chrgUsed*chrgUsed*0.016f; //scales from 6.6% to 165%
		numPods = 0.02f + chrgUsed*chrgUsed*0.013f; //scales from 3.3% to 135%
		numStars = (chrgUsed*chrgUsed*chrgUsed/5f)*0.005f; //scales from 0.1% to 100%
		
		if (overLimit > 0){
			numPlants -= overLimit*0.02f;
			numDews -= overLimit*0.02f;
			numPods -= overLimit*0.02f;
			numStars -= overLimit*0.02f;
		}
		
		placePlants(numPlants, numDews, numPods, numStars);

		for (int i : affectedCells){
			int c = Dungeon.level.map[i];
			if (c == Terrain.EMPTY ||
					c == Terrain.EMBERS ||
					c == Terrain.EMPTY_DECO) {
				Level.set( i, Terrain.GRASS );
			}

			Char ch = Actor.findChar(i);
			if (ch != null){
				processSoulMark(ch, chargesPerCast());
			}
			
			if (Random.Int(50) < overLimit) {
				if (Dungeon.level.map[i] == Terrain.GRASS) {
					Level.set( i, Terrain.FURROWED_GRASS );
					GameScene.updateMap( i );
				}
				GameScene.add(Blob.seed(i, 9, Regrowth.class));
			} else {
				GameScene.add(Blob.seed(i, 10, Regrowth.class));
			}
			
		}
		
		totChrgUsed += chrgUsed;
	}
	
	private int chargeLimit( int heroLevel ){
		if (level() >= 12){
			return Integer.MAX_VALUE;
		} else {
			//4 charges per hero level at +0, with another 2-4 each upgrade from +1 to +9.
			//then +7 at +10, +16 at +11, and infinite at +12.
			return Math.round(((4 + 2*level())*heroLevel) * (11f/12f + 1f/(12f-level())));
		}
	}

	private void spreadRegrowth(int cell, float strength){
		if (strength >= 0 && Dungeon.level.passable[cell]){
			affectedCells.add(cell);
			if (strength >= 1.5f) {
				spreadRegrowth(cell + PathFinder.CIRCLE8[left(direction)], strength - 1.5f);
				spreadRegrowth(cell + PathFinder.CIRCLE8[direction], strength - 1.5f);
				spreadRegrowth(cell + PathFinder.CIRCLE8[right(direction)], strength-1.5f);
			} else {
				visualCells.add(cell);
			}
		} else if (!Dungeon.level.passable[cell])
			visualCells.add(cell);
	}

	private void placePlants(float numPlants, float numDews, float numPods, float numStars){
		Iterator<Integer> cells = affectedCells.iterator();
		Level floor = Dungeon.level;

		while(cells.hasNext() && Random.Float() <= numPlants){
			Plant.Seed seed = (Plant.Seed) Generator.random(Generator.Category.SEED);
			floor.plant(seed, cells.next());

			numPlants --;
		}

		while (cells.hasNext() && Random.Float() <= numDews){
			floor.plant(new Dewcatcher.Seed(), cells.next());
			numDews --;
		}

		while (cells.hasNext() && Random.Float() <= numPods){
			floor.plant(new Seedpod.Seed(), cells.next());
			numPods --;
		}

		while (cells.hasNext() && Random.Float() <= numStars){
			floor.plant(new Starflower.Seed(), cells.next());
			numStars --;
		}

	}

	private int left(int direction){
		return direction == 0 ? 7 : direction-1;
	}

	private int right(int direction){
		return direction == 7 ? 0 : direction+1;
	}

	@Override
	public void onHit(MagesStaff staff, Char attacker, Char defender, int damage) {
		new Blooming().proc(staff, attacker, defender, damage);
	}

	protected void fx( Ballistica bolt, Callback callback ) {

		affectedCells = new HashSet<>();
		visualCells = new HashSet<>();

		int maxDist = Math.round(1.2f + chargesPerCast()*.8f);
		int dist = Math.min(bolt.dist, maxDist);

		for (int i = 0; i < PathFinder.CIRCLE8.length; i++){
			if (bolt.sourcePos+PathFinder.CIRCLE8[i] == bolt.path.get(1)){
				direction = i;
				break;
			}
		}

		float strength = maxDist;
		for (int c : bolt.subPath(1, dist)) {
			strength--; //as we start at dist 1, not 0.
			if (Dungeon.level.passable[c]) {
				affectedCells.add(c);
				spreadRegrowth(c + PathFinder.CIRCLE8[left(direction)], strength - 1);
				spreadRegrowth(c + PathFinder.CIRCLE8[direction], strength - 1);
				spreadRegrowth(c + PathFinder.CIRCLE8[right(direction)], strength - 1);
			} else {
				visualCells.add(c);
			}
		}

		//going to call this one manually
		visualCells.remove(bolt.path.get(dist));

		for (int cell : visualCells){
			//this way we only get the cells at the tip, much better performance.
			((MagicMissile)curUser.sprite.parent.recycle( MagicMissile.class )).reset(
					MagicMissile.FOLIAGE_CONE,
					curUser.sprite,
					cell,
					null
			);
		}
		MagicMissile.boltFromChar( curUser.sprite.parent,
				MagicMissile.FOLIAGE_CONE,
				curUser.sprite,
				bolt.path.get(dist/2),
				callback );

		Sample.INSTANCE.play( Assets.SND_ZAP );
	}

	@Override
	protected int initialCharges() {
		return 1;
	}

	@Override
	//consumes all available charges, needs at least one.
	protected int chargesPerCast() {
		return Math.max(1, curCharges);
	}

	@Override
	public void staffFx(MagesStaff.StaffParticle particle) {
		particle.color( ColorMath.random(0x004400, 0x88CC44) );
		particle.am = 1f;
		particle.setLifespan(1f);
		particle.setSize( 1f, 1.5f);
		particle.shuffleXY(0.5f);
		float dst = Random.Float(11f);
		particle.x -= dst;
		particle.y += dst;
	}
	
	private static final String TOTAL = "totChrgUsed";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put( TOTAL, totChrgUsed );
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		totChrgUsed = bundle.getInt(TOTAL);
	}
	
	public static class Dewcatcher extends Plant{

		{
			image = 13;
		}

		@Override
		public void activate( Char ch ) {

			int nDrops = Random.NormalIntRange(3, 6);

			ArrayList<Integer> candidates = new ArrayList<Integer>();
			for (int i : PathFinder.NEIGHBOURS8){
				if (Dungeon.level.passable[pos+i]
						&& pos+i != Dungeon.level.entrance
						&& pos+i != Dungeon.level.exit){
					candidates.add(pos+i);
				}
			}

			for (int i = 0; i < nDrops && !candidates.isEmpty(); i++){
				Integer c = Random.element(candidates);
				Dungeon.level.drop(new Dewdrop(), c).sprite.drop(pos);
				candidates.remove(c);
			}

		}

		//seed is never dropped, only care about plant class
		public static class Seed extends Plant.Seed {
			{
				plantClass = Dewcatcher.class;
			}
		}
	}

	public static class Seedpod extends Plant{

		{
			image = 14;
		}

		@Override
		public void activate( Char ch ) {

			int nSeeds = Random.NormalIntRange(2, 4);

			ArrayList<Integer> candidates = new ArrayList<Integer>();
			for (int i : PathFinder.NEIGHBOURS8){
				if (Dungeon.level.passable[pos+i]
						&& pos+i != Dungeon.level.entrance
						&& pos+i != Dungeon.level.exit){
					candidates.add(pos+i);
				}
			}

			for (int i = 0; i < nSeeds && !candidates.isEmpty(); i++){
				Integer c = Random.element(candidates);
				Dungeon.level.drop(Generator.random(Generator.Category.SEED), c).sprite.drop(pos);
				candidates.remove(c);
			}

		}

		//seed is never dropped, only care about plant class
		public static class Seed extends Plant.Seed {
			{
				plantClass = Seedpod.class;
			}
		}

	}

}
