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

package com.overgrownpixel.overgrownpixeldungeon.items.armor.curses;

import com.overgrownpixel.overgrownpixeldungeon.R;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Hunger;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.Armor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.Armor.Glyph;
import com.overgrownpixel.overgrownpixeldungeon.sprites.CharSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSprite.Glowing;
import com.overgrownpixel.overgrownpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Game;
import com.watabou.utils.Random;

public class Metabolism extends Glyph {

	private static ItemSprite.Glowing BLACK = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.curse) );
	
	@Override
	public int proc( Armor armor, Char attacker, Char defender, int damage) {

		if (Random.Int( 6 ) == 0) {

			//assumes using up 10% of starving, and healing of 1 hp per 10 turns;
			int healing = Math.min((int)Hunger.STARVING/100, defender.HT - defender.HP);

			if (healing > 0) {
				
				Hunger hunger = Buff.affect(defender, Hunger.class);
				
				if (hunger != null && !hunger.isStarving()) {
					
					hunger.reduceHunger( healing * -10 );
					BuffIndicator.refreshHero();
					
					defender.HP += healing;
					defender.sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
					defender.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healing ) );
				}
			}

		}
		
		return damage;
	}

	@Override
	public Glowing glowing() {
		return BLACK;
	}

	@Override
	public boolean curse() {
		return true;
	}
}
