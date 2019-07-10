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

import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.CharSprite;
import com.overgrownpixel.overgrownpixeldungeon.ui.BuffIndicator;

public class MagicalShield extends FlavourBuff {

	public static final float DURATION	= 50f;

	{
		type = buffType.POSITIVE;
		announced = true;
	}

    @Override
    public void fx(boolean on) {
        if (on) target.sprite.add(CharSprite.State.SHIELDED);
        else target.sprite.remove(CharSprite.State.SHIELDED);
    }
	
	@Override
	public int icon() {
		return BuffIndicator.FORCEFIELD;
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
