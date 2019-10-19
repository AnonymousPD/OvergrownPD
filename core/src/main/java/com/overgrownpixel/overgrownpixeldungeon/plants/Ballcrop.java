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
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Balling;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.SuperBalling;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.BallcropPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;

public class Ballcrop extends Plant {

	{
		image = 40;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
	    if(enemy instanceof Hero && ((Hero) enemy).subClass == HeroSubClass.WARDEN){
            Buff.prolong(enemy, SuperBalling.class, SuperBalling.DURATION);
        } else {
            Buff.prolong(enemy, Balling.class, Balling.DURATION);
        }
    }

    @Override
    public void activate(Char ch) {
        if(ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
            Buff.prolong(ch, SuperBalling.class, SuperBalling.DURATION);
        } else {
            Buff.prolong(ch, Balling.class, Balling.DURATION);
        }
    }

    @Override
    public void activate() {
        for(Mob mob : Dungeon.level.mobs){
            Buff.prolong(mob, Balling.class, Balling.DURATION/2);
        }
        if(Dungeon.hero != null){
            if(Dungeon.hero.subClass == HeroSubClass.WARDEN){
                Buff.prolong(Dungeon.hero, SuperBalling.class, SuperBalling.DURATION/2);
            } else {
                Buff.prolong(Dungeon.hero, Balling.class, Balling.DURATION/2);
            }
        }
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_BALLCROP;

			plantClass = Ballcrop.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            if(defender instanceof Hero && ((Hero) defender).subClass == HeroSubClass.WARDEN){
                Buff.prolong(defender, SuperBalling.class, SuperBalling.DURATION);
            } else {
                Buff.prolong(defender, Balling.class, Balling.DURATION);
            }
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return BallcropPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new BallcropPoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
