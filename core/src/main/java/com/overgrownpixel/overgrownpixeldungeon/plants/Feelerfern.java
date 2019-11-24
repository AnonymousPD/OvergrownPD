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
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Regrowth;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Feelers;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.FeelerfernPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;

public class Feelerfern extends Plant {

	{
		image = 45;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        Buff.prolong(enemy, Feelers.class, Feelers.DURATION);
    }

    @Override
    public void activate(Char ch) {
        Buff.prolong(ch, Feelers.class, Feelers.DURATION);
    }

    @Override
    public void activate() {
        GameScene.add(Blob.seed(pos, 10, Regrowth.class));
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new FeelerfernPoisonParticle().getColor(), 10);
        Buff.prolong(ch, Feelers.class, 2f);
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_FEELERFERN;

			plantClass = Feelerfern.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            Buff.prolong(attacker, Feelers.class, Feelers.DURATION);
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return FeelerfernPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new FeelerfernPoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
