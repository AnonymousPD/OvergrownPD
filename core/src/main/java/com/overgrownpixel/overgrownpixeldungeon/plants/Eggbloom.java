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
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.EggbloomPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.food.Egg;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.Random;

public class Eggbloom extends Plant {

	{
		image = 44;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
	    float chances = 0.3f;
	    if(enemy instanceof Hero){
            chances = (((Hero)enemy).subClass == HeroSubClass.WARDEN) ? 0.7f : 0.3f;
        }
        if(Random.Float() < chances) Dungeon.level.drop(new Egg(), enemy.pos).sprite.drop(enemy.pos);
    }

    @Override
    public void activate(Char ch) {
        float chances = 0.3f;
        if(ch instanceof Hero){
            chances = (((Hero)ch).subClass == HeroSubClass.WARDEN) ? 0.7f : 0.3f;
        }
        if(Random.Float() < chances) Dungeon.level.drop(new Egg(), ch.pos).sprite.drop(ch.pos);
    }

    @Override
    public void activate() {
        Dungeon.level.drop(new Egg(), pos).sprite.drop(pos);
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_EGGBLOOM;

			plantClass = Eggbloom.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
		    float chances = 0.3f;
		    if(attacker instanceof Hero){
                chances = (((Hero)attacker).subClass == HeroSubClass.WARDEN) ? 0.7f : 0.3f;
            }
            if(Random.Float() < chances) Dungeon.level.drop(new Egg(), defender.pos).sprite.drop(attacker.pos);
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return EggbloomPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new EggbloomPoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
