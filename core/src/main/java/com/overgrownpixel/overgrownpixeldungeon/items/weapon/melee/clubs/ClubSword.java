/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2016 Evan Debenham
 *
 * Lovecraft Pixel Dungeon
 * Copyright (C) 2016-2017 Leon Horn
 *
 * Plugin Pixel Dungeon
 * Copyright (C) 2017 Leon Horn
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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.clubs;

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;

public class ClubSword extends MeleeWeapon {

	{
		image = ItemSpriteSheet.CLUB_SWORD;

		tier = 4;
        ACC = 1.18f; //18% boost to accuracy
	}

    @Override
    public int max(int lvl) {
        return  5*(tier) +                	//20 base, down from 25
                Math.round(lvl*(tier));	    //+4 per level, down from +5
    }

    @Override
    public int defenseFactor(Char owner) {
        return 6; //extra defense
    }
}
