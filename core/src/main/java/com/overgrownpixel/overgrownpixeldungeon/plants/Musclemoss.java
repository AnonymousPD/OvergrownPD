/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Overgrown Pixel Dungeon
 * Copyright (C) 2016-2019 Anon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This Program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without eben the implied warranty of
 * GNU General Public License for more details.
 *
 * You should have have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses>
 */

package com.overgrownpixel.overgrownpixeldungeon.plants;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.MusclemossPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfBlastWave;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Musclemoss extends Plant {

	{
		image = 18;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        try{
            int opposite;
            int path;
            do{
                path = PathFinder.NEIGHBOURS8[Random.Int( 8 )];
                opposite = pos + path;
            } while ((Dungeon.level.passable[opposite]) && (Dungeon.level.passable[opposite+path]));
            if((enemy instanceof Mob && Dungeon.level.mobs.contains(enemy)) || enemy instanceof Hero){
                Ballistica trajectory = new Ballistica(pos, opposite, Ballistica.MAGIC_BOLT);
                WandOfBlastWave.throwChar(enemy, trajectory, damage*10);
            }
        } catch (Exception e){
            OvergrownPixelDungeon.reportException(e);
        }
    }

    @Override
    public void activate(Char ch) {
        try{
            int opposite;
            int path;
            do{
                path = PathFinder.NEIGHBOURS8[Random.Int( 8 )];
                opposite = pos + path;
            } while ((Dungeon.level.passable[opposite]) && (Dungeon.level.passable[opposite+path]));
            if((ch instanceof Mob && Dungeon.level.mobs.contains(ch)) || ch instanceof Hero){
                Ballistica trajectory = new Ballistica(pos, opposite, Ballistica.MAGIC_BOLT);
                WandOfBlastWave.throwChar(ch, trajectory, 100);
            }
        } catch (Exception e){
            OvergrownPixelDungeon.reportException(e);
        }
    }

    @Override
    public void activate() {
        try{
            if(Actor.findChar(pos) != null){
                int opposite;
                int path;
                do{
                    path = PathFinder.NEIGHBOURS8[Random.Int( 8 )];
                    opposite = pos + path;
                } while ((Dungeon.level.passable[opposite]) && (Dungeon.level.passable[opposite+path]));
                if((Actor.findChar(pos) instanceof Mob &&Dungeon.level.mobs.contains(Actor.findChar(pos))) || Actor.findChar(pos) instanceof Hero){
                    Ballistica trajectory = new Ballistica(pos, opposite, Ballistica.MAGIC_BOLT);
                    WandOfBlastWave.throwChar(Actor.findChar(pos), trajectory, 100);
                }
            }
        } catch (Exception e){
            OvergrownPixelDungeon.reportException(e);
        }
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_MUSCLEMOSS;

			plantClass = Musclemoss.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            new Musclemoss().attackProc(defender, damage);
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return MusclemossPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new MusclemossPoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
