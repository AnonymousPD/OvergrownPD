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
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.effects.Splash;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.ui.BuffIndicator;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.Image;
import com.watabou.utils.PointF;

import static com.watabou.utils.Random.NormalFloat;

public class Thorns extends FlavourBuff {
	
	{
		type = buffType.NEGATIVE;
		announced = true;
	}
	
	public static final float DURATION	= 10f;

    @Override
    public boolean attachTo( Char target ) {
        if (!target.flying && super.attachTo( target )) {
            target.rooted = true;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean act() {
        if (target.isAlive()) {

            float level = 3f;

            level = NormalFloat(level / 2f, level);
            int dmg = Math.round(level);

            if (dmg > 0) {

                target.damage( dmg, this );
                if (target.sprite.visible) {
                    Splash.at( target.sprite.center(), -PointF.PI / 2, PointF.PI / 6,
                            target.sprite.blood(), Math.min( 10 * dmg / target.HT, 10 ) );
                }

                if (target == Dungeon.hero && !target.isAlive()) {
                    Dungeon.fail( getClass() );
                    GLog.n( Messages.get(this, "ondeath") );
                }

                spend( TICK );
            } else {
                detach();
            }

        } else {

            detach();

        }

        return true;
    }

    @Override
    public void detach() {
        target.rooted = false;
        super.detach();
    }
	
	@Override
	public int icon() {
		return BuffIndicator.ROSETHORNS;
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
