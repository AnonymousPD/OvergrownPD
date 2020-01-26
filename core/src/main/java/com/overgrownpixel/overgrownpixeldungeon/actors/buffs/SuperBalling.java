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

package com.overgrownpixel.overgrownpixeldungeon.actors.buffs;

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.tiles.DungeonTilemap;
import com.overgrownpixel.overgrownpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Random;

public class SuperBalling extends FlavourBuff {
	
	{
		type = buffType.NEUTRAL;
		announced = true;
	}
	
	public static final float DURATION	= 20f;
	
	@Override
	public int icon() {
		return BuffIndicator.BALLING;
	}

    @Override
    public boolean attachTo(Char target) {
        if(target.isImmune(this.getClass())){
            return super.attachTo(target);
        }
        target.sprite.origin.set( target.sprite.width / 2, target.sprite.height - DungeonTilemap.SIZE / 2 );
        target.sprite.angularSpeed = Random.Int( 2 ) == 0 ? -1440 : 1440;
        return super.attachTo(target);
    }

    @Override
    public void detach() {
        target.sprite.origin.set(target.sprite.x, target.sprite.y);
        target.sprite.angularSpeed = 0f;
        target.sprite.angle = 0f;
        super.detach();
    }

    @Override
	public void tintIcon(Image icon) {
		greyIcon(icon, 5f, cooldown());
	}
	
	@Override
	public String toString() {
		return Messages.get(this, "name");
	}

    @Override
    public String heroMessage() {
        return Messages.get(this, "heromsg");
    }

    @Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns());
	}
	
}
