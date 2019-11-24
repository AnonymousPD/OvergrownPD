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

package com.overgrownpixel.overgrownpixeldungeon.effects;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.R;
import com.overgrownpixel.overgrownpixeldungeon.sprites.CharSprite;
import com.watabou.noosa.Game;
import com.watabou.noosa.Gizmo;
import com.watabou.noosa.audio.Sample;

public class IronSkin extends Gizmo {

	private float phase;

	private CharSprite target;

	public IronSkin(CharSprite target ) {
		super();

		this.target = target;
		phase = 0;
	}
	
	@Override
	public void update() {
		super.update();

		if ((phase += Game.elapsed * 2) < 1) {
			target.tint(  Game.instance.getResources().getInteger(R.integer.ironskin), phase * 0.6f );
		} else {
			target.tint(  Game.instance.getResources().getInteger(R.integer.ironskin), 0.6f );
		}
	}
	
	public void ironDown() {

		target.resetColor();
		killAndErase();

		if (visible) {
			Splash.at( target.center(), Game.instance.getResources().getInteger(R.integer.ironskin), 5 );
            Sample.INSTANCE.play( Assets.SND_EVOKE );
		}
	}
	
	public static IronSkin ironUp(CharSprite sprite ) {
		
		IronSkin ironSkin = new IronSkin( sprite );
		if (sprite.parent != null)
			sprite.parent.add( ironSkin );
		
		return ironSkin;
	}
}
