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

package com.overgrownpixel.overgrownpixeldungeon.levels;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.R;
import com.overgrownpixel.overgrownpixeldungeon.items.Torch;
import com.overgrownpixel.overgrownpixeldungeon.levels.painters.HallsPainter;
import com.overgrownpixel.overgrownpixeldungeon.levels.painters.Painter;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.BlazingTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.CorrosionTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.CursingTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.DisarmingTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.DisintegrationTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.DistortionTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.ExplosiveTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.FlashingTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.FrostTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.GrimTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.GuardianTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.PitfallTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.RockfallTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.StormTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.SummoningTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.WarpingTrap;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.WeakeningTrap;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.tiles.DungeonTilemap;
import com.watabou.glwrap.Blending;
import com.watabou.noosa.Game;
import com.watabou.noosa.Group;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class HallsLevel extends RegularLevel {

	{
		
		viewDistance = Math.min( 26 - Dungeon.depth, viewDistance );
		
		color1 = Game.instance.getResources().getInteger(R.integer.hallslevel1);
		color2 = Game.instance.getResources().getInteger(R.integer.hallslevel2);
	}
	
	@Override
	protected int standardRooms() {
		//8 to 10, average 8.67
		return 8+Random.chances(new float[]{3, 2, 1});
	}
	
	@Override
	protected int specialRooms() {
		//2 to 3, average 2.5
		return 2 + Random.chances(new float[]{1, 1});
	}
	
	@Override
	protected Painter painter() {
		return new HallsPainter()
				.setWater(feeling == Feeling.WATER ? 0.70f : 0.15f, 6)
				.setGrass(feeling == Feeling.GRASS ? 0.65f : 0.10f, 3)
                .setPlants(feeling == Feeling.GRASS ? 10 : 5)
				.setTraps(nTraps(), trapClasses(), trapChances());
	}
	
	@Override
	public void create() {
		addItemToSpawn( new Torch() );
		super.create();
	}
	
	@Override
	public String tilesTex() {
		return Assets.TILES_HALLS;
	}
	
	@Override
	public String waterTex() {
		return Assets.WATER_HALLS;
	}
	
	@Override
	protected Class<?>[] trapClasses() {
		return new Class[]{ FrostTrap.class, StormTrap.class, CorrosionTrap.class, BlazingTrap.class, DisintegrationTrap.class,
				ExplosiveTrap.class, RockfallTrap.class, FlashingTrap.class, GuardianTrap.class, WeakeningTrap.class,
				SummoningTrap.class, WarpingTrap.class, CursingTrap.class, GrimTrap.class,
				PitfallTrap.class, DisarmingTrap.class, DistortionTrap.class };
	}

	@Override
	protected float[] trapChances() {
		return new float[]{ 8, 8, 8, 8, 8,
				4, 4, 4, 4, 4,
				2, 2, 2, 2,
				1, 1, 1 };
	}
	
	@Override
	public String tileName( int tile ) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(HallsLevel.class, "water_name");
			case Terrain.GRASS:
				return Messages.get(HallsLevel.class, "grass_name");
			case Terrain.HIGH_GRASS:
				return Messages.get(HallsLevel.class, "high_grass_name");
			case Terrain.STATUE:
			case Terrain.STATUE_SP:
				return Messages.get(HallsLevel.class, "statue_name");
			default:
				return super.tileName( tile );
		}
	}
	
	@Override
	public String tileDesc(int tile) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(HallsLevel.class, "water_desc");
			case Terrain.STATUE:
			case Terrain.STATUE_SP:
				return Messages.get(HallsLevel.class, "statue_desc");
			case Terrain.BOOKSHELF:
				return Messages.get(HallsLevel.class, "bookshelf_desc");
			default:
				return super.tileDesc( tile );
		}
	}
	
	@Override
	public Group addVisuals() {
		super.addVisuals();
		addHallsVisuals( this, visuals );
		return visuals;
	}
	
	public static void addHallsVisuals( Level level, Group group ) {
		for (int i=0; i < level.length(); i++) {
			if (level.map[i] == Terrain.WATER) {
				group.add( new Stream( i ) );
			}
		}
	}
	
	private static class Stream extends Group {
		
		private int pos;
		
		private float delay;
		
		public Stream( int pos ) {
			super();
			
			this.pos = pos;
			
			delay = Random.Float( 2 );
		}
		
		@Override
		public void update() {
			
			if (visible = (pos < Dungeon.level.heroFOV.length && Dungeon.level.heroFOV[pos])) {
				
				super.update();
				
				if ((delay -= Game.elapsed) <= 0) {
					
					delay = Random.Float( 2 );
					
					PointF p = DungeonTilemap.tileToWorld( pos );
					((FireParticle)recycle( FireParticle.class )).reset(
						p.x + Random.Float( DungeonTilemap.SIZE ),
						p.y + Random.Float( DungeonTilemap.SIZE ) );
				}
			}
		}
		
		@Override
		public void draw() {
			Blending.setLightMode();
			super.draw();
			Blending.setNormalMode();
		}
	}
	
	public static class FireParticle extends PixelParticle.Shrinking {
		
		public FireParticle() {
			super();
			
			color( Game.instance.getResources().getInteger(R.integer.hallsfire) );
			lifespan = 1f;
			
			acc.set( 0, +80 );
		}
		
		public void reset( float x, float y ) {
			revive();
			
			this.x = x;
			this.y = y;
			
			left = lifespan;
			
			speed.set( 0, -40 );
			size = 4;
		}
		
		@Override
		public void update() {
			super.update();
			float p = left / lifespan;
			am = p > 0.8f ? (1 - p) * 5 : 1;
		}
	}
}
