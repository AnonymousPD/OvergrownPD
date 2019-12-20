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
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Sunlight;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.ShadowParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.SunbloomPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.SungrassPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;

public class Sunbloom extends Plant {

	{
		image = 20;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        if(enemy instanceof Mob && enemy.properties().contains(Char.Property.UNDEAD)){
            enemy.die(this);
            if(Dungeon.level.heroFOV[enemy.pos]){
                enemy.sprite.emitter().start( ShadowParticle.UP, 0.05f, 10 );
            }
        }
    }

    @Override
    public void activate(Char ch) {
        if(ch instanceof Mob && ch.properties().contains(Char.Property.UNDEAD)){
            ch.die(this);
            if(Dungeon.level.heroFOV[ch.pos]){
                ch.sprite.emitter().start( ShadowParticle.UP, 0.05f, 10 );
            }
        }
    }

    @Override
    public void activate() {
        GameScene.add(Blob.seed(pos, 50, Sunlight.class));
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new SunbloomPoisonParticle().getColor(), 10);
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.NEWSEEDS_SUNBLOOM;

			plantClass = Sunbloom.class;

		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            if(defender instanceof Mob && defender.properties().contains(Char.Property.UNDEAD)){
                defender.die(this);
                if(Dungeon.level.heroFOV[defender.pos]){
                    defender.sprite.emitter().start( ShadowParticle.UP, 0.05f, 10 );
                }
            }
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return SunbloomPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new SungrassPoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
