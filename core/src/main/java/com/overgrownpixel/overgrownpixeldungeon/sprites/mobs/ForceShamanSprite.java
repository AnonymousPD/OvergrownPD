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

package com.overgrownpixel.overgrownpixeldungeon.sprites.mobs;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Shaman;
import com.overgrownpixel.overgrownpixeldungeon.effects.Lightning;
import com.watabou.noosa.TextureFilm;

public class ForceShamanSprite extends MobSprite {

	public ForceShamanSprite() {
		super();
		
		texture( Assets.SHAMAN );

		int linenumber = 12;
		
		TextureFilm frames = new TextureFilm( texture, 12, 15 );
		
		idle = new Animation( 2, true );
		idle.frames( frames, 0+linenumber, 0+linenumber, 0+linenumber, 1+linenumber, 0+linenumber, 0+linenumber, 1+linenumber, 1+linenumber );
		
		run = new Animation( 12, true );
		run.frames( frames, 4+linenumber, 5+linenumber, 6+linenumber, 7+linenumber );
		
		attack = new Animation( 12, false );
		attack.frames( frames, 2+linenumber, 3+linenumber, 0+linenumber );
		
		zap = attack.clone();
		
		die = new Animation( 12, false );
		die.frames( frames, 8+linenumber, 9+linenumber, 10+linenumber );
		
		play( idle );
	}
	
	public void zap( int pos ) {

		parent.add( new Lightning( ch.pos, pos, (Shaman)ch ) );
		
		turnTo( ch.pos, pos );
		play( zap );
	}
}
