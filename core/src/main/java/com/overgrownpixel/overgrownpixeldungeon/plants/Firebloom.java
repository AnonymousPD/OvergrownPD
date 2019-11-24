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
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Fire;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.FireImbue;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.FlameParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.FirebloomPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;

public class Firebloom extends Plant {
	
	{
		image = 1;
	}
	
	@Override
	public void activate( Char ch ) {
		
		if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
			Buff.affect(ch, FireImbue.class).set(15f);
		}
		
		GameScene.add( Blob.seed( ch.pos, 2, Fire.class ) );
		
		if (Dungeon.level.heroFOV[ch.pos]) {
			CellEmitter.get( ch.pos ).burst( FlameParticle.FACTORY, 5 );
		}
	}

    @Override
    public void activate() {
        GameScene.add( Blob.seed( pos, 2, Fire.class ) );

        if (Dungeon.level.heroFOV[pos]) {
            CellEmitter.get( pos ).burst( FlameParticle.FACTORY, 5 );
        }
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new FirebloomPoisonParticle().getColor(), 10);
        Buff.affect(ch, FireImbue.class).set(2f);
    }

    @Override
    public void attackProc(Char enemy, int damage) {
        if (enemy instanceof Hero && ((Hero) enemy).subClass == HeroSubClass.WARDEN){
            Buff.affect(enemy, FireImbue.class).set(15f);
        }

        GameScene.add( Blob.seed( enemy.pos, 2, Fire.class ) );

        if (Dungeon.level.heroFOV[enemy.pos]) {
            CellEmitter.get( enemy.pos ).burst( FlameParticle.FACTORY, 5 );
        }
    }

    @Override
    public Blob immunity() {
        return null;
    }
	
	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.SEED_FIREBLOOM;

			plantClass = Firebloom.class;
		}

        @Override
        public Emitter.Factory getPixelParticle() {
            return FirebloomPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new FirebloomPoisonParticle();
        }

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            new Firebloom().attackProc(defender, damage);
        }
    }
}
