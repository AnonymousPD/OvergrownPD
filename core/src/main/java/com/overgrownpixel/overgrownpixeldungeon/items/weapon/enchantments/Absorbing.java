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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments;

import com.overgrownpixel.overgrownpixeldungeon.R;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.LivingPlant;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.Weapon;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSprite;
import com.watabou.noosa.Game;

public class Absorbing extends Weapon.Enchantment {

    private static ItemSprite.Glowing COLOR = new ItemSprite.Glowing(Game.instance.getResources().getInteger(R.integer.absorbing));

    @Override
    public int proc(Weapon weapon, Char attacker, Char defender, int damage) {

        if(weapon instanceof MeleeWeapon){
            if (((MeleeWeapon) weapon).seed == null) {

                if(defender instanceof LivingPlant){
                    ((MeleeWeapon) weapon).seed = ((LivingPlant) defender).plantClass.getPlant(((LivingPlant) defender).plantClass);
                    ((MeleeWeapon) weapon).setPoisonTurns((weapon.level()+1)*3);
                }

            }
        }

        return damage;
    }
	
	@Override
	public ItemSprite.Glowing glowing() {
		return COLOR;
	}
}