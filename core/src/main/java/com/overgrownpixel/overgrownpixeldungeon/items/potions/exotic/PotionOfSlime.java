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
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.tiles.butters.Butter;
import com.overgrownpixel.overgrownpixeldungeon.tiles.butters.PotionButterVariant1;
import com.overgrownpixel.overgrownpixeldungeon.tiles.butters.PotionButterVariant2;
import com.overgrownpixel.overgrownpixeldungeon.tiles.butters.PotionButterVariant3;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class PotionOfSlime extends ExoticPotion {
	
	{
		initials = 59;
	}

    private ArrayList butters = new ArrayList<Butter>();

    @Override
    public void apply(Hero hero) {
        setKnown();
        int mobsEffected = 0;
        for(Mob mob : Dungeon.level.mobs.toArray(new Mob[0])){
            for (int offset : PathFinder.NEIGHBOURS9){
                if (Dungeon.level.passable[mob.pos+offset]
                        && (Dungeon.level.map[mob.pos+offset] == Terrain.EMPTY
                        || Dungeon.level.map[mob.pos+offset] == Terrain.EMPTY_DECO
                        || Dungeon.level.map[mob.pos+offset] == Terrain.EMPTY_SP)
                        && Dungeon.level.plants.get(mob.pos+offset) == null) {

                    butters.add(new PotionButterVariant1());
                    butters.add(new PotionButterVariant2());
                    butters.add(new PotionButterVariant3());

                    Dungeon.level.setButters((Butter) Random.element(butters), mob.pos+offset);
                    GameScene.updateMap(mob.pos+offset);

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
