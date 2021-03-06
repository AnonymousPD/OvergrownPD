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
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.PoisonGas;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Poison;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.ToxicImbue;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.PoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.SorrowmossPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;

public class Sorrowmoss extends Plant {

	{
		image = 6;
	}
	
	@Override
	public void activate( Char ch ) {
		if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
			Buff.affect(ch, ToxicImbue.class).set(15f);
		}
		
		if (ch != null) {
			Buff.affect( ch, Poison.class ).set( 5 + Math.round(2*Dungeon.depth / 3f) );
		}
		
		if (Dungeon.level.heroFOV[ch.pos]) {
			CellEmitter.center( ch.pos ).burst( PoisonParticle.SPLASH, 3 );
		}
	}

    @Override
    public void activate() {
        GameScene.add(Blob.seed(pos, 50, PoisonGas.class));
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new SorrowmossPoisonParticle().getColor(), 10);
        Buff.affect(ch, ToxicImbue.class).set(2f);
    }

    @Override
    public void attackProc(Char enemy, int damage) {
        if (enemy instanceof Hero && ((Hero) enemy).subClass == HeroSubClass.WARDEN){
            Buff.affect(enemy, ToxicImbue.class).set(15f);
        }

        if (enemy != null) {
            Buff.affect( enemy, Poison.class ).set( 5 + Math.round(2*Dungeon.depth / 3f) );
        }

        if (Dungeon.level.heroFOV[enemy.pos]) {
            CellEmitter.center( enemy.pos ).burst( PoisonParticle.SPLASH, 3 );
        }
    }

    @Override
    public Blob immunity() {
        return null;
    }
	
	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.NEWSEEDS_SORROWMOSS;

			plantClass = Sorrowmoss.class;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return SorrowmossPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new SorrowmossPoisonParticle();
        }

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            new Sorrowmoss().attackProc(defender, damage);
        }
    }
}
