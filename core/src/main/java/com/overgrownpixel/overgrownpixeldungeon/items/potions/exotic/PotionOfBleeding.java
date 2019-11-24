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

import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Bleeding;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs.NPC;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;

public class PotionOfBleeding extends ExoticPotion {
	
	{
		initials = 57;
	}

    @Override
    public void apply(Hero hero) {
	    setKnown();
	    int mobsBleeding = 0;
        for(Mob mob : hero.visibleEnemiesList().toArray(new Mob[0])){
            if(mob != null){
                if(!(mob instanceof NPC) && !mob.isImmune(this.getClass())){
                    mob.damage(1, this);
                    mob.sprite.burst(mob.sprite.blood(), 20);
                    Buff.affect(mob, Bleeding.class).set(hero.STR);
                    mobsBleeding++;
                }
            }
        }
        if(mobsBleeding > 0){
            GLog.i(Messages.get(this, "mobs_effected", mobsBleeding));
        } else {
            GLog.i(Messages.get(this, "no_targets"));
        }
    }
}
