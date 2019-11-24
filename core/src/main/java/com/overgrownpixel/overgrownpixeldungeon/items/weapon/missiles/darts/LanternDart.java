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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.R;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Blindness;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.Game;
import com.watabou.utils.Random;

public class LanternDart extends TippedDart {
	
	{
		image = ItemSpriteSheet.DART_LAMP;
	}
	
	@Override
	public int proc(Char attacker, Char defender, int damage) {
        GameScene.flash( Game.instance.getResources().getInteger(R.integer.lavenderflash) );
        for(Mob mob : Dungeon.level.mobs.toArray(new Mob[0])){
            if(mob != null) Buff.prolong( mob, Blindness.class, Random.Int( 2, 5 ) );
        }
        if(Dungeon.hero != null) Buff.prolong( Dungeon.hero, Blindness.class, Random.Int( 2, 5 ) );
        if (attacker.alignment == defender.alignment){
            return 0;
        }
		return super.proc(attacker, defender, damage);
	}
}
