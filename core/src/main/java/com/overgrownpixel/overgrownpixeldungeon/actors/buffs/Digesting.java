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

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.ui.BuffIndicator;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.Image;

public class Digesting extends FlavourBuff implements Hero.Doom {
	
	{
		type = buffType.NEGATIVE;
		announced = true;
	}
	
	public static final float DURATION	= 100f;

    @Override
    public void detach() {
        super.detach();
        target.damage(Math.round(Dungeon.depth/2), this);
    }

    @Override
	public int icon() {
		return BuffIndicator.DIGESTING;
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

    @Override
    public void onDeath() {
        Dungeon.fail( getClass() );
        GLog.n( Messages.get(this, "ondeath") );
    }
}
