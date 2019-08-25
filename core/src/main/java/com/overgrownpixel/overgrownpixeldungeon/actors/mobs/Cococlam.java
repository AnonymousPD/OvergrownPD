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

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Cripple;
import com.overgrownpixel.overgrownpixeldungeon.sprites.mobs.CococlamSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Cococlam extends Mob {

    private static final String LEVEL	    = "level";

    private int level;

    {
        spriteClass = CococlamSprite.class;

        viewDistance = 3;

        EXP = 0;

        state = WANDERING;

        properties.add(Property.PLANT);
    }

    public void spawn( int level ) {
        this.level = level;

        HT = HP = (2 + level) * 4;
        defenseSkill = 9 + level;
        spriteClass = CococlamSprite.class;
    }

    @Override
    public void storeInBundle( Bundle bundle ) {
        super.storeInBundle( bundle );
        bundle.put( LEVEL, level );
    }

    @Override
    public void restoreFromBundle( Bundle bundle ) {
        super.restoreFromBundle( bundle );
        spawn( bundle.getInt( LEVEL ));
    }

    @Override
    public int attackSkill( Char target ) {
        return defenseSkill;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange( HT / 10, HT / 4 );
    }

    @Override
    public int attackProc( Char enemy, int damage ) {
        damage = super.attackProc( enemy, damage );
        if (enemy instanceof Mob) {
            ((Mob)enemy).aggro( this );
        }
        Buff.prolong( enemy, Cripple.class, Cripple.DURATION );
        return damage;
    }
}
