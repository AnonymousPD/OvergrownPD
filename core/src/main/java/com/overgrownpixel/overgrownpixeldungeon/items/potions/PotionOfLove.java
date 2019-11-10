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
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Charm;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.watabou.noosa.audio.Sample;

public class PotionOfLove extends Potion {

	{
		initials = 20;

		bones = true;
	}
	
	@Override
	public void apply( Hero hero ) {
	    for(Mob mob : Dungeon.level.mobs){
	        if(hero.visibleEnemiesList().contains(mob)){
                Buff.affect( mob, Charm.class, 10f).object = hero.id();
                mob.sprite.centerEmitter().start( Speck.factory( Speck.HEART ), 0.2f, 5 );
            }
        }
	}

    @Override
    public void shatter(int cell) {
        if (Dungeon.level.heroFOV[cell]) {
            setKnown();

            splash( cell );
            Sample.INSTANCE.play( Assets.SND_SHATTER );
        }

        for(Mob mob : Dungeon.level.mobs){
            if(mob.fieldOfView[cell] && Dungeon.hero.fieldOfView[cell]){
                Buff.affect( mob, Charm.class, 10f).object = Dungeon.hero.id();
                mob.sprite.centerEmitter().start( Speck.factory( Speck.HEART ), 0.2f, 5 );
            }
        }
    }

    @Override
	public int price() {
		return isKnown() ? 50 * quantity : super.price();
	}
}
