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
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.DewInfusion;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.Dewdrop;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class PotionOfDew extends Potion {

	{
		initials = 62;

		bones = true;
	}
	
	@Override
	public void apply( Hero hero ) {
        Buff.prolong(hero, DewInfusion.class, DewInfusion.DURATION);
        setKnown();
	}

    @Override
    public void shatter(int cell) {
        if (Dungeon.level.heroFOV[cell]) {
            setKnown();

            splash( cell );
            Sample.INSTANCE.play( Assets.SND_SHATTER );
        }

        for (int offset : PathFinder.NEIGHBOURS9){
            if (!Dungeon.level.solid[cell+offset]) {
                int nDrops = Random.NormalIntRange(3, 6);

                ArrayList<Integer> candidates = new ArrayList<Integer>();
                for (int i : PathFinder.NEIGHBOURS8){
                    if (Dungeon.level.passable[cell+offset+i]
                            && cell+offset+i != Dungeon.level.entrance
                            && cell+offset+i != Dungeon.level.exit){
                        candidates.add(cell+offset+i);
                    }
                }

                for (int i = 0; i < nDrops && !candidates.isEmpty(); i++){
                    Integer c = Random.element(candidates);
                    Dungeon.level.drop(new Dewdrop(), c).sprite.drop(cell+offset);
                    candidates.remove(c);
                }
            }
        }
    }

    @Override
	public int price() {
		return isKnown() ? 50 * quantity : super.price();
	}
}
