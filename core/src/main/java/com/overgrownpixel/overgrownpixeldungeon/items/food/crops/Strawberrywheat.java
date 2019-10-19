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

package com.overgrownpixel.overgrownpixeldungeon.items.food.crops;

import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.VitaminC;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.VitaminG;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.VitaminS;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.VitaminSuper;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.VitaminW;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;

public class Strawberrywheat extends Wheat {

	{
		image = ItemSpriteSheet.STRAWBERRYWHEAT;
	}

    @Override
    public void effect(Hero hero) {
        Buff.prolong(hero, VitaminS.class, VitaminS.DURATION);
        if(hero.buff(VitaminC.class) != null
                && hero.buff(VitaminG.class) != null
                && hero.buff(VitaminS.class) != null
                && hero.buff(VitaminW.class) != null){
            for(Buff buff : hero.buffs()){
                if(buff instanceof VitaminC
                        || buff instanceof VitaminG
                        || buff instanceof VitaminS
                        || buff instanceof VitaminW){
                    buff.detach();
                }
            }
            Buff.prolong(hero, VitaminSuper.class, VitaminSuper.DURATION);
        }
    }
}
