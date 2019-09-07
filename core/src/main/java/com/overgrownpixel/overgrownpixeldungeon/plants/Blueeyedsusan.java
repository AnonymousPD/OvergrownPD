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
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.Flare;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.BlueEyedSusanPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.Dewdrop;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;

public class Blueeyedsusan extends Plant {

	{
		image = 41;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        if(enemy instanceof Hero){
            if(((Hero) enemy).subClass == HeroSubClass.WARDEN){
                boolean uncursed = false;
                for(Item item : ((Hero) enemy).belongings){
                    if(item.cursed){
                        ScrollOfRemoveCurse scroll = new ScrollOfRemoveCurse();
                        uncursed = scroll.uncurse((Hero)enemy, item);
                    }
                }
                if(uncursed){
                    new Flare( 6, 32 ).show( enemy.sprite, 1f ) ;
                }
            } else
            if(((Hero) enemy).belongings.weapon.cursed){
                ScrollOfRemoveCurse scroll = new ScrollOfRemoveCurse();
                boolean bool = scroll.uncurse((Hero)enemy, ((Hero) enemy).belongings.weapon);
                if(bool) new Flare( 6, 32 ).show( enemy.sprite, 1f ) ;
            }
        }
        if(enemy instanceof Mob){
            enemy.HP = enemy.HT;
        }
    }

    @Override
    public void activate(Char ch) {
        if(ch instanceof Hero){
            if(((Hero) ch).subClass == HeroSubClass.WARDEN){
                boolean uncursed = false;
                for(Item item : ((Hero) ch).belongings){
                    if(item.cursed){
                        ScrollOfRemoveCurse scroll = new ScrollOfRemoveCurse();
                        uncursed = scroll.uncurse((Hero)ch, item);
                    }
                }
                if(uncursed){
                    new Flare( 6, 32 ).show( ch.sprite, 1f ) ;
                }
            } else
            if(((Hero) ch).belongings.weapon.cursed){
                ScrollOfRemoveCurse scroll = new ScrollOfRemoveCurse();
                boolean bool = scroll.uncurse((Hero)ch, ((Hero) ch).belongings.weapon);
                if(bool) new Flare( 6, 32 ).show( ch.sprite, 1f ) ;
            }
        }
        if(ch instanceof Mob){
            ch.HP = ch.HT;
        }
    }

    @Override
    public void activate() {
        Dungeon.level.drop(new Dewdrop(), pos).sprite.drop(pos);
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_BLUEEYEDSUSAN;

			plantClass = Blueeyedsusan.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            if(attacker instanceof Hero){
                if(((Hero) attacker).subClass == HeroSubClass.WARDEN){
                    boolean uncursed = false;
                    for(Item item : ((Hero) attacker).belongings){
                        if(item.cursed){
                            ScrollOfRemoveCurse scroll = new ScrollOfRemoveCurse();
                            uncursed = scroll.uncurse((Hero)attacker, item);
                        }
                    }
                    if(uncursed){
                        new Flare( 6, 32 ).show( attacker.sprite, 1f ) ;
                    }
                } else
                if(((Hero) attacker).belongings.weapon.cursed){
                    ScrollOfRemoveCurse scroll = new ScrollOfRemoveCurse();
                    boolean bool = scroll.uncurse((Hero)attacker, ((Hero) attacker).belongings.weapon);
                    if(bool) new Flare( 6, 32 ).show( attacker.sprite, 1f ) ;
                }
            }
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return BlueEyedSusanPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new BlueEyedSusanPoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
