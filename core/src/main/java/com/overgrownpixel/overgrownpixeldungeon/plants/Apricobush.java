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
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Healing;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Hunger;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.ApricobushPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.food.Aprico;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.Random;

public class Apricobush extends Plant {

	{
		image = 15;
	}

    protected void satisfy( Hero hero ){
        (hero.buff( Hunger.class )).satisfy( 100f );
    }

    protected void starve(Hero hero){
        (hero.buff( Hunger.class )).reduceHunger(50f);
    }

    @Override
    public void attackProc(Char enemy, int damage) {
	    if(Random.Float() < 0.3f){
            if(enemy instanceof Hero){
                starve((Hero) enemy);
            }
        }
    }

    @Override
    public void activate(Char ch) {
        if (ch != null) {
            if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
                Buff.affect( ch, Healing.class ).setHeal(Math.round(ch.HT/2), 0.25f, 0);
                satisfy((Hero) ch);
            } else {
                Buff.affect( ch, Healing.class ).setHeal(Math.round(ch.HT/4), 0.25f, 0);
            }
        }
    }

    @Override
    public void activate() {
        Dungeon.level.drop(new Aprico(), pos).sprite.drop(pos);
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_APRICOBUSH;

			plantClass = Apricobush.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
		    if(attacker instanceof Hero){
                statisfy((Hero) attacker);
            }
            Buff.affect( attacker, Healing.class ).setHeal(Math.round(damage/10), 0.25f, 0);
        }

        private void statisfy(Hero hero) {
            (hero.buff( Hunger.class )).satisfy( 20f );
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return ApricobushPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new ApricobushPoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
