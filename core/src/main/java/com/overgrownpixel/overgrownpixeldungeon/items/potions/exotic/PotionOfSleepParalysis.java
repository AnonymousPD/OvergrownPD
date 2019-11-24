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

package com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs.SleepParalysisDemon;
import com.overgrownpixel.overgrownpixeldungeon.effects.Pushing;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.AlphaTweener;

public class PotionOfSleepParalysis extends ExoticPotion {
	
	{
		initials = 49;
	}

    @Override
    public void shatter(int cell) {

	    if (Dungeon.level.heroFOV[cell]) {
            setKnown();

            splash( cell );
            Sample.INSTANCE.play( Assets.SND_SHATTER );
        }

	    SleepParalysisDemon sleepParalysisDemon = new SleepParalysisDemon();
        sleepParalysisDemon.HP = sleepParalysisDemon.HT;
        sleepParalysisDemon.pos = cell;

        GameScene.add( sleepParalysisDemon );
        Actor.addDelayed( new Pushing( sleepParalysisDemon, cell, cell ), -1f );

        sleepParalysisDemon.sprite.alpha( 0 );
        sleepParalysisDemon.sprite.parent.add( new AlphaTweener( sleepParalysisDemon.sprite, 1, 0.15f ) );

        Sample.INSTANCE.play( Assets.SND_CURSED );

    }
}
