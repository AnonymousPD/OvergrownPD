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

import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.FlowertreePoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.Generator;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;

public class Flowertree extends Plant {

	{
		image = 61;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        try {
            Plant.Seed seed = (Plant.Seed) Generator.random(Generator.Category.SEED);
            seed.couch(enemy.pos, null, false);
        } catch (Exception e){
            OvergrownPixelDungeon.reportException(e);
        }
    }

    @Override
    public void activate(Char ch) {
        try {
            Plant.Seed seed = (Plant.Seed) Generator.random(Generator.Category.SEED);
            seed.couch(ch.pos,null, false);
        } catch (Exception e){
            OvergrownPixelDungeon.reportException(e);
        }
    }

    @Override
    public void activate() {
        try {
            Plant.Seed seed = (Plant.Seed) Generator.random(Generator.Category.SEED);
            seed.couch(pos, null, false);
        } catch (Exception e){
            OvergrownPixelDungeon.reportException(e);
        }
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new FlowertreePoisonParticle().getColor(), 10);
        try {
            Plant.Seed seed = (Plant.Seed) Generator.random(Generator.Category.SEED);
            seed.getPlantClass().newInstance().spiceEffect(ch);
        } catch (Exception e){
            OvergrownPixelDungeon.reportException(e);
        }
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.NEWSEEDS_FLOWERTREE;

			plantClass = Flowertree.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            try {
                Plant.Seed seed = (Plant.Seed) Generator.random(Generator.Category.SEED);
                seed.couch(defender.pos, null, false);
            } catch (Exception e){
                OvergrownPixelDungeon.reportException(e);
            }
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return FlowertreePoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new FlowertreePoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
