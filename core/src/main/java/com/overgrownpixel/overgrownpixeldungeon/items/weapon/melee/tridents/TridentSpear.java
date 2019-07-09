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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.tridents;

import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;

public class TridentSpear extends MeleeWeapon {

	{
		image = ItemSpriteSheet.TRIDENT_SPEAR;

		tier = 4;
		RCH = 2;
	}

	@Override
	public int max(int lvl) {
        return  4*(tier) +                	//16 base, down from 25
                Math.round(lvl*(tier+1));	//+5 per level, up from +5
	}

    @Override
    public int min(int lvl) {
        return  4*(tier) +                	//16 base, down from 25
                Math.round(lvl*(tier+1));	//+5 per level, up from +5
    }
}
