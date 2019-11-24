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

package com.overgrownpixel.overgrownpixeldungeon.items.potions;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.R;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class PotionOfSun extends Potion {

	{
		initials = 53;

		bones = true;
	}

    @Override
    public void shatter(int cell) {
        GameScene.flash(Game.instance.getResources().getInteger(R.integer.whiteflashsun));
        ArrayList<Mob> mobs = new ArrayList<>();
        for(Mob mob : Dungeon.level.mobs.toArray(new Mob[0])){
            if(mob.properties().contains(Char.Property.UNDEAD)){
                mobs.add(mob);
            }
        }
        for(Mob mob : mobs){
            mob.die(this);
        }
        mobs.clear();
        Sample.INSTANCE.play(Assets.SND_BLAST);
    }

    @Override
	public int price() {
		return isKnown() ? 50 * quantity : super.price();
	}
}
