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

import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.items.Generator;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;

public class PotionOfFlower extends ExoticPotion {
	
	{
		initials = 32;
	}

    @Override
    public void apply(Hero hero) {
        setKnown();
        int mobsEffected = 0;
        for(Mob mob : hero.visibleEnemiesList().toArray(new Mob[0])){
            try {
                Plant.Seed seed = (Plant.Seed) Generator.random(Generator.Category.SEED);
                Plant plant = seed.getPlantClass().newInstance();
                plant.activate(mob);
                mobsEffected++;
            } catch (Exception e){
                OvergrownPixelDungeon.reportException(e);
            }
        }

        if(mobsEffected > 0){
            GLog.i(Messages.get(this, "mobs_effected", mobsEffected));
        } else {
            GLog.i(Messages.get(this, "no_targets"));
        }
    }
}
