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

package com.overgrownpixel.overgrownpixeldungeon.actors.mobs;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Ooze;
import com.overgrownpixel.overgrownpixeldungeon.sprites.mobs.GooPlantSprite;
import com.watabou.utils.Random;

public class GooPlant extends Mob {

	{
		spriteClass = GooPlantSprite.class;
		
		HP = HT = Dungeon.depth;
		defenseSkill = Dungeon.depth;

		alignment = Alignment.ALLY;
	}

    @Override
    public int attackProc(Char enemy, int damage) {
        Buff.affect( enemy, Ooze.class ).set( 20f );
        return super.attackProc(enemy, damage);
    }

    @Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, Dungeon.depth );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return Dungeon.depth;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, Dungeon.depth);
	}
}
