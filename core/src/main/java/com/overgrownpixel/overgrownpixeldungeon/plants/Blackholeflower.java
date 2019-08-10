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
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.SpaceTimePowers;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.BlackholeflowerPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.overgrownpixel.overgrownpixeldungeon.levels.features.Chasm;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.InterlevelScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.Game;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;

public class Blackholeflower extends Plant {

	{
		image = 25;
	}
    public void attackProc(Char enemy, int damage) {
        if(enemy instanceof Mob && !enemy.properties().contains(Char.Property.MINIBOSS) && !enemy.properties().contains(Char.Property.BOSS)){
            if(enemy.isAlive()) Chasm.mobFall((Mob) enemy);
        }
        if(enemy instanceof Hero) {
            if (Dungeon.bossLevel() || Dungeon.depth <= 1) {

                GLog.w(Messages.get(ScrollOfTeleportation.class, "no_tele"));
                return;

            }

            Buff buff = Dungeon.hero.buff(TimekeepersHourglass.timeFreeze.class);
            if (buff != null) buff.detach();

            InterlevelScene.mode = InterlevelScene.Mode.RETURN;
            InterlevelScene.returnDepth = Dungeon.depth-1;
            InterlevelScene.returnPos = -1;
            Game.switchScene( InterlevelScene.class );
        }
    }

    @Override
    public void activate(Char ch) {
        ch.damage(Math.round(ch.HP/2), this);
        if(ch instanceof Hero) {
            if(((Hero) ch).subClass == HeroSubClass.WARDEN){
                Buff.prolong(ch, SpaceTimePowers.class, SpaceTimePowers.DURATION);
            } else {
                if (Dungeon.bossLevel() || Dungeon.depth <= 1) {

                    GLog.w(Messages.get(ScrollOfTeleportation.class, "no_tele"));
                    return;

                }

                Buff buff = Dungeon.hero.buff(TimekeepersHourglass.timeFreeze.class);
                if (buff != null) buff.detach();

                InterlevelScene.mode = InterlevelScene.Mode.RETURN;
                InterlevelScene.returnDepth = Dungeon.depth-1;
                InterlevelScene.returnPos = -1;
                Game.switchScene( InterlevelScene.class );
            }
        }
        if(ch instanceof Mob && !ch.properties().contains(Char.Property.MINIBOSS) && !ch.properties().contains(Char.Property.BOSS)){
            if(ch.isAlive()) Chasm.mobFall((Mob) ch);
        }
    }

    @Override
    public void activate() {
        spawnLasher(pos);
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_BLACKHOLEFLOWER;

			plantClass = Blackholeflower.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            new Blackholeflower().attackProc(defender, damage);
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return BlackholeflowerPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new BlackholeflowerPoisonParticle();
        }
		
		@Override
		public int price() {
			return 30 * quantity;
		}
	}
}
