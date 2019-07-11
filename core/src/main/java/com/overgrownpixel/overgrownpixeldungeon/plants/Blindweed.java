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
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Blindness;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Cripple;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Invisibility;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.BlindweedPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.Random;

public class Blindweed extends Plant {
	
	{
		image = 11;
	}
	
	@Override
	public void activate( Char ch ) {
		
		if (ch != null) {
			if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
				Buff.affect(ch, Invisibility.class, 10f);
			} else {
				int len = Random.Int(5, 10);
				Buff.prolong(ch, Blindness.class, len);
				Buff.prolong(ch, Cripple.class, len);
				if (ch instanceof Mob) {
					if (((Mob) ch).state == ((Mob) ch).HUNTING) ((Mob) ch).state = ((Mob) ch).WANDERING;
					((Mob) ch).beckon(Dungeon.level.randomDestination());
				}
			}
		}
		
		if (Dungeon.level.heroFOV[ch.pos]) {
			CellEmitter.get( ch.pos ).burst( Speck.factory( Speck.LIGHT ), 4 );
		}
	}

    @Override
    public void activate() {
        spawnLasher(pos);
    }

    @Override
    public void attackProc(Char enemy, int damage) {
        if (enemy != null) {
            if (enemy instanceof Hero && ((Hero) enemy).subClass == HeroSubClass.WARDEN){
                Buff.affect(enemy, Invisibility.class, 10f);
            } else {
                int len = Random.Int(5, 10);
                Buff.prolong(enemy, Blindness.class, len);
                Buff.prolong(enemy, Cripple.class, len);
                if (enemy instanceof Mob) {
                    if (((Mob) enemy).state == ((Mob) enemy).HUNTING) ((Mob) enemy).state = ((Mob) enemy).WANDERING;
                    ((Mob) enemy).beckon(Dungeon.level.randomDestination());
                }
            }
        }

        if (Dungeon.level.heroFOV[enemy.pos]) {
            CellEmitter.get( enemy.pos ).burst( Speck.factory( Speck.LIGHT ), 4 );
        }
    }
	
	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.SEED_BLINDWEED;

			plantClass = Blindweed.class;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return BlindweedPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new BlindweedPoisonParticle();
        }

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            new Blindweed().attackProc(defender, damage);
        }
    }
}
