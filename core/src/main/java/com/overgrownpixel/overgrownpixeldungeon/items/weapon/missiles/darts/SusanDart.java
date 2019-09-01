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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts;

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.effects.Flare;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;

public class SusanDart extends TippedDart {
	
	{
		image = ItemSpriteSheet.DART_SUSAN;
	}
	
	@Override
	public int proc(Char attacker, Char defender, int damage) {
        if(attacker instanceof Hero){
            if(((Hero) attacker).belongings.weapon.cursed){
                ScrollOfRemoveCurse scroll = new ScrollOfRemoveCurse();
                boolean bool = scroll.uncurse((Hero)attacker, ((Hero) attacker).belongings.weapon);
                if(bool) new Flare( 6, 32 ).show( attacker.sprite, 1f ) ;
            }
        }
        if (attacker.alignment == defender.alignment){
            return 0;
        }
		return super.proc(attacker, defender, damage);
	}
}
