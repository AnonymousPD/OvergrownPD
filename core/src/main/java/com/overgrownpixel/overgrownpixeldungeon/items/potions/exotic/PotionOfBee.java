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

package com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic;

import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.Honeypot;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfHoney;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;

public class PotionOfBee extends ExoticPotion {
	
	{
		initials = 23;
	}

    @Override
    public void apply(Hero hero) {
        setKnown();
        Honeypot honeypot = new Honeypot();
        if(hero.enemy() != null){
            honeypot.shatter(hero.enemy(), hero.enemy().pos, false);
            GLog.i(Messages.get(this, "summoned_bee"));
        } else {
            new PotionOfHoney().apply(hero);
            GLog.i(Messages.get(this, "no_enemy"));
        }
    }
}
