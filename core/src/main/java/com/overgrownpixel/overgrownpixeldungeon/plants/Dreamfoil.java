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

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Bleeding;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.BlobImmunity;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Cripple;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Drowsy;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.MagicalSleep;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Poison;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Slow;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Vertigo;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Weakness;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.DreamfoilPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;

public class Dreamfoil extends Plant {

	{
		image = 7;
	}

	@Override
	public void activate( Char ch ) {

		if (ch != null) {
			if (ch instanceof Mob) {
				Buff.affect(ch, MagicalSleep.class);
			} else if (ch instanceof Hero){
				GLog.i( Messages.get(this, "refreshed") );
				Buff.detach( ch, Poison.class );
				Buff.detach( ch, Cripple.class );
				Buff.detach( ch, Weakness.class );
				Buff.detach( ch, Bleeding.class );
				Buff.detach( ch, Drowsy.class );
				Buff.detach( ch, Slow.class );
				Buff.detach( ch, Vertigo.class);
				
				if (((Hero) ch).subClass == HeroSubClass.WARDEN){
					Buff.affect(ch, BlobImmunity.class, 10f);
				}
				
			}
		}
	}

    @Override
    public void activate() {
        spawnLasher(pos);
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new DreamfoilPoisonParticle().getColor(), 10);
        Buff.affect(ch, BlobImmunity.class, 2f);

    }

    @Override
    public void attackProc(Char enemy, int damage) {
	    if(enemy instanceof Hero){
            defaultProc(enemy, damage);
        } else {
	        activate(enemy);
        }
    }

    @Override
    public Blob immunity() {
        return null;
    }

	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.NEWSEEDS_DREAMFOIL;

			plantClass = Dreamfoil.class;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return DreamfoilPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new DreamfoilPoisonParticle();
        }

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            new Dreamfoil().activate(defender);
        }
    }
}