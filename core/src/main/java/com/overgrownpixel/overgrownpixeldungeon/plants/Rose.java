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
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.RoseBarrier;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Thorns;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.RosePoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.DriedRose;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;

public class Rose extends Plant {

	{
		image = 36;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        Buff.prolong(enemy, Thorns.class, Thorns.DURATION);
    }

    @Override
    public void activate(Char ch) {
        Buff.prolong(ch, RoseBarrier.class, RoseBarrier.DURATION);
    }

    @Override
    public void activate() {
        DriedRose rose = Dungeon.hero.belongings.getItem( DriedRose.class );
        if (rose != null && rose.isIdentified() && !rose.cursed){
            //aim to drop 1 petal every 2 floors
            int petalsNeeded = (int) Math.ceil((float)((Dungeon.depth / 2) - rose.droppedPetals) / 3);

            for (int i=1; i <= petalsNeeded; i++) {
                //the player may miss a single petal and still max their rose.
                if (rose.droppedPetals < 11) {
                    Dungeon.level.drop(new DriedRose.Petal(), pos).sprite.drop(pos);
                    rose.droppedPetals++;
                }
            }
        }
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
		    image = ItemSpriteSheet.SEED_ROSE;

			plantClass = Rose.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            Buff.prolong(defender, Thorns.class, Thorns.DURATION);
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return RosePoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new RosePoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
