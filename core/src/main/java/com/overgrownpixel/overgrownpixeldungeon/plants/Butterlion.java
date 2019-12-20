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

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Heavy;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.ButterlionPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.PathFinder;

public class Butterlion extends Plant {

    {
		image = 17;
	}

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new ButterlionPoisonParticle().getColor(), 10);
        if (Dungeon.level.heroFOV[ch.pos]) {
            CellEmitter.get( ch.pos ).start( Speck.factory( Speck.ROCK ), 0.07f, 10 );
            Camera.main.shake( 1, 0.7f );
            Sample.INSTANCE.play( Assets.SND_ROCKS );
        }
        ch.damage(1, this);
        if(ch == Dungeon.hero && !ch.isAlive()){
            Dungeon.fail(getClass());
            GLog.n( Messages.get(this, "ondeath") );
        }
    }

    @Override
    public void attackProc(Char enemy, int damage) {
        if (Dungeon.level.heroFOV[enemy.pos]) {
            CellEmitter.get( enemy.pos ).start( Speck.factory( Speck.ROCK ), 0.07f, 10 );
            Camera.main.shake( 3, 0.7f );
            Sample.INSTANCE.play( Assets.SND_ROCKS );
        }
        enemy.damage(damage, this);
        if(enemy == Dungeon.hero && !enemy.isAlive()){
            Dungeon.fail(getClass());
            GLog.n( Messages.get(this, "ondeath") );
        }
        for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
            Char ch = Actor.findChar(enemy.pos + PathFinder.NEIGHBOURS8[i]);
            if(ch instanceof Mob && ch.alignment != Char.Alignment.ALLY){
                ch.damage(damage, this);
            }
        }
    }

    @Override
    public void activate(Char ch) {
        if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
            Buff.prolong( ch, Heavy.class, Heavy.DURATION );
        }
        if (Dungeon.level.heroFOV[ch.pos]) {
            CellEmitter.get( ch.pos ).start( Speck.factory( Speck.ROCK ), 0.07f, 10 );
            Camera.main.shake( 3, 0.7f );
            Sample.INSTANCE.play( Assets.SND_ROCKS );
        }
        ch.damage(Math.round(ch.HP/8), this);
        if(ch == Dungeon.hero && !ch.isAlive()){
            Dungeon.fail(getClass());
            GLog.n( Messages.get(this, "ondeath") );
        }
        for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
            Char ch1 = Actor.findChar(ch.pos + PathFinder.NEIGHBOURS8[i]);
            if(ch1 instanceof Mob && ch1.alignment != Char.Alignment.ALLY){
                ch1.damage(Math.round(ch.HP/8), this);
            }
        }
    }

    @Override
    public void activate() {
        if (Dungeon.level.heroFOV[pos]) {
            CellEmitter.get( pos ).start( Speck.factory( Speck.ROCK ), 0.07f, 10 );
            Camera.main.shake( 3, 0.7f );
            Sample.INSTANCE.play( Assets.SND_ROCKS );
        }
        for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
            Char ch = Actor.findChar(pos + PathFinder.NEIGHBOURS8[i]);
            if(ch instanceof Mob && ch.alignment != Char.Alignment.ALLY){
                ch.damage(Math.round(ch.HP/8), this);
            }
        }
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.NEWSEEDS_BUTTERLION;

			plantClass = Butterlion.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            new Butterlion().attackProc(defender, damage);
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return ButterlionPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new ButterlionPoisonParticle();
        }
		
		@Override
		public int price() {
			return 30 * quantity;
		}
	}
}
