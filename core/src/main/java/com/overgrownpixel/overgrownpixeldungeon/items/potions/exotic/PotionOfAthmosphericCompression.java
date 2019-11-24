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
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs.NPC;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;

public class PotionOfAthmosphericCompression extends ExoticPotion {
	
	{
		initials = 42;
	}

    @Override
    public void apply(Hero hero) {
        setKnown();
        int length = Dungeon.level.length();
        boolean[] water = Dungeon.level.water;
        int mobsHurt = 0;

        for (int i=0; i < length; i++) {

            if(water[i] == true){
                Level.set(i, Terrain.EMPTY);
                GameScene.updateMap(i);
                if(Actor.findChar(i) instanceof Mob){
                    if(!(Actor.findChar(i) instanceof NPC) && !Actor.findChar(i).isImmune(this.getClass())){
                        Actor.findChar(i).damage(5,  this);
                        mobsHurt++;
                    }
                }
                if(Dungeon.level.heroFOV[i]){
                    CellEmitter.get( i ).start( Speck.factory( Speck.STEAM ), 0.2f, 8 );
                }
            }
        }

        if(mobsHurt > 0){
            GLog.i(Messages.get(this, "mobs_effected", mobsHurt));
        } else {
            GLog.i(Messages.get(this, "no_targets"));
        }
    }
}
