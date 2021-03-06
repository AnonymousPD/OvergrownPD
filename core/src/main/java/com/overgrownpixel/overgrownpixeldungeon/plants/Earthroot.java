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
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Barkskin;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.FlavourBuff;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.EarthParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.EarthrootPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Image;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.Bundle;

public class Earthroot extends Plant {
	
	{
		image = 8;
	}
	
	@Override
	public void activate( Char ch ) {
		
		if (ch == Dungeon.hero) {
			if (Dungeon.hero.subClass == HeroSubClass.WARDEN){
				Buff.affect(ch, Barkskin.class).set(Dungeon.hero.lvl + 5, 5);
			} else {
				Buff.affect(ch, Armor.class).level(ch.HT);
			}
		}
		
		if (Dungeon.level.heroFOV[ch.pos]) {
			CellEmitter.bottom( ch.pos ).start( EarthParticle.FACTORY, 0.05f, 8 );
			Camera.main.shake( 1, 0.4f );
		}
	}

    @Override
    public void activate() {
        spawnLasher(pos);
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new EarthrootPoisonParticle().getColor(), 10);
        Buff.affect(ch, Barkskin.class).set(2, 2);
        if (Dungeon.level.heroFOV[ch.pos]) {
            CellEmitter.bottom( ch.pos ).start( EarthParticle.FACTORY, 0.05f, 8 );
            Camera.main.shake( 1, 0.4f );
        }
    }

    @Override
    public void attackProc(Char enemy, int damage) {
        if (enemy == Dungeon.hero) {
            if (Dungeon.hero.subClass == HeroSubClass.WARDEN){
                Buff.affect(enemy, Barkskin.class).set(Dungeon.hero.lvl + 5, 5);
            } else {
                Buff.affect(enemy, Armor.class).level(enemy.HT);
            }
        }

        if (Dungeon.level.heroFOV[enemy.pos]) {
            CellEmitter.bottom( enemy.pos ).start( EarthParticle.FACTORY, 0.05f, 8 );
            Camera.main.shake( 1, 0.4f );
        }
    }

    @Override
    public Blob immunity() {
        return null;
    }
	
	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.NEWSEEDS_EARTHROOT;

			plantClass = Earthroot.class;

			bones = true;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return EarthrootPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new EarthrootPoisonParticle();
        }

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            new Earthroot().attackProc(defender, damage);
        }
    }
	
	public static class Armor extends Buff {
		
		private static final float STEP = 1f;
		
		private int pos;
		private int level;

		{
			type = buffType.POSITIVE;
			announced = true;
		}
		
		@Override
		public boolean attachTo( Char target ) {
			pos = target.pos;
			return super.attachTo( target );
		}
		
		@Override
		public boolean act() {
			if (target.pos != pos) {
				detach();
			}
			spend( STEP );
			return true;
		}
		
		private static int blocking(){
			return (Dungeon.depth + 5)/2;
		}
		
		public int absorb( int damage ) {
			int block = Math.min( damage, blocking());
			if (level <= block) {
				detach();
				return damage - block;
			} else {
				level -= block;
				BuffIndicator.refreshHero();
				return damage - block;
			}
		}
		
		public void level( int value ) {
			if (level < value) {
				level = value;
				BuffIndicator.refreshHero();
			}
			pos = target.pos;
		}
		
		@Override
		public int icon() {
			return BuffIndicator.ARMOR;
		}
		
		@Override
		public void tintIcon(Image icon) {
			FlavourBuff.greyIcon(icon, target.HT/4f, level);
		}
		
		@Override
		public String toString() {
			return Messages.get(this, "name");
		}

		@Override
		public String desc() {
			return Messages.get(this, "desc", blocking(), level);
		}

		private static final String POS		= "pos";
		private static final String LEVEL	= "level";
		
		@Override
		public void storeInBundle( Bundle bundle ) {
			super.storeInBundle( bundle );
			bundle.put( POS, pos );
			bundle.put( LEVEL, level );
		}
		
		@Override
		public void restoreFromBundle( Bundle bundle ) {
			super.restoreFromBundle( bundle );
			pos = bundle.getInt( POS );
			level = bundle.getInt( LEVEL );
		}
	}
}
