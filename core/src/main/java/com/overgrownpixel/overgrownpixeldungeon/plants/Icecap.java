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

package com.overgrownpixel.overgrownpixeldungeon.plants;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Fire;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Freezing;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.FrostImbue;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.IceCapPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.BArray;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.PathFinder;

public class Icecap extends Plant {
	
	{
		image = 4;
	}
	
	@Override
	public void activate( Char ch ) {
		
		if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
			Buff.affect(ch, FrostImbue.class, 15f);
		}
		
		PathFinder.buildDistanceMap( ch.pos, BArray.not( Dungeon.level.losBlocking, null ), 1 );
		
		Fire fire = (Fire)Dungeon.level.blobs.get( Fire.class );
		
		for (int i=0; i < PathFinder.distance.length; i++) {
			if (PathFinder.distance[i] < Integer.MAX_VALUE) {
				Freezing.affect( i, fire );
			}
		}
	}

    @Override
    public void activate() {
        PathFinder.buildDistanceMap( pos, BArray.not( Dungeon.level.losBlocking, null ), 1 );

        Fire fire = (Fire)Dungeon.level.blobs.get( Fire.class );

        for (int i=0; i < PathFinder.distance.length; i++) {
            if (PathFinder.distance[i] < Integer.MAX_VALUE) {
                Freezing.affect( i, fire );
            }
        }
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new IceCapPoisonParticle().getColor(), 10);
        Buff.affect(ch, FrostImbue.class, 2f);
    }

    @Override
    public void attackProc(Char enemy, int damage) {
        if (enemy instanceof Hero && ((Hero) enemy).subClass == HeroSubClass.WARDEN){
            Buff.affect(enemy, FrostImbue.class, 15f);
        }

        PathFinder.buildDistanceMap( enemy.pos, BArray.not( Dungeon.level.losBlocking, null ), 1 );

        Fire fire = (Fire)Dungeon.level.blobs.get( Fire.class );

        for (int i=0; i < PathFinder.distance.length; i++) {
            if (PathFinder.distance[i] < Integer.MAX_VALUE) {
                Freezing.affect( i, fire );
            }
        }
    }

    @Override
    public Blob immunity() {
        return new Freezing();
    }
	
	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.NEWSEEDS_ICECAP;

			plantClass = Icecap.class;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return IceCapPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new IceCapPoisonParticle();
        }

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            new Icecap().attackProc(defender, damage);
        }
    }
}
