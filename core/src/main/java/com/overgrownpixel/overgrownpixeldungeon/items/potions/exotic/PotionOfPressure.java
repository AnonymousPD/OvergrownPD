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

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs.NPC;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;

public class PotionOfPressure extends ExoticPotion {
	
	{
		initials = 52;
	}

    @Override
    public void apply(Hero hero) {
        setKnown();
        int mobsEffected = 0;
        for(Mob mob : Dungeon.level.mobs.toArray(new Mob[0])){
            if(!(mob instanceof NPC) && !mob.isImmune(this.getClass())){
                if(!mob.properties().contains(Char.Property.BOSS) || !mob.properties().contains(Char.Property.MINIBOSS)){
                    mob.sprite.height = mob.sprite.height() / 2;
                    mob.damage(hero.STR, this);
                    mobsEffected++;
                }
            }
        }

        if(mobsEffected > 0){
            GLog.i(Messages.get(this, "mobs_effected", mobsEffected));
        } else {
            GLog.i(Messages.get(this, "no_targets"));
        }
    }
}
