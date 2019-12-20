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

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Slow;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.ClockcypressPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.Random;

public class Clockcypress extends Plant {

	{
		image = 54;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        Buff.prolong( enemy, Slow.class, Slow.DURATION );
    }

    @Override
    public void activate(Char ch) {
        TimekeepersHourglass timekeepersHourglass = new TimekeepersHourglass();
        int time = ch.attackSkill(ch);
        if(ch instanceof Hero){
            time = (((Hero) ch).subClass == HeroSubClass.WARDEN) ? 10 : ch.attackSkill(ch);
        }
        timekeepersHourglass.getTimeStopEffectFreeze(time);
    }

    @Override
    public void activate() {
        TimekeepersHourglass timekeepersHourglass = new TimekeepersHourglass();
        timekeepersHourglass.getTimeStopEffectStasis(Random.Int(3, 5));
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new ClockcypressPoisonParticle().getColor(), 10);
        TimekeepersHourglass timekeepersHourglass = new TimekeepersHourglass();
        timekeepersHourglass.getTimeStopEffectStasis(2);
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.NEWSEEDS_CLOCKCYPRESS;

			plantClass = Clockcypress.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            TimekeepersHourglass timekeepersHourglass = new TimekeepersHourglass();
            timekeepersHourglass.getTimeStopEffectFreeze(damage);
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return ClockcypressPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new ClockcypressPoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
