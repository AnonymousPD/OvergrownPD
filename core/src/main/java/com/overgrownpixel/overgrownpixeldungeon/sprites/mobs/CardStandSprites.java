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
import com.watabou.noosa.TextureFilm;

public class CardStandSprites extends MobSprite {

    private Integer frame(int id, int frame){
        int factor = id*13;
        return frame+factor;
    }

    public CardStandSprites(int id) {
        super();

        texture( Assets.STANDS );

        TextureFilm frames = new TextureFilm( texture, 15, 16 );

        idle = new Animation( 2, true );
        idle.frames( frames, frame(id, 0), frame(id, 0), frame(id, 0), frame(id, 0) );

        run = new Animation( 10, true );
        run.frames( frames, frame(id, 0), frame(id, 1), frame(id, 2), frame(id, 1), frame(id, 0) );

        attack = new Animation( 15, false );
        attack.frames( frames, frame(id, 0), frame(id, 1), frame(id, 2), frame(id, 3), frame(id, 4), frame(id, 5), frame(id, 6), frame(id, 7), frame(id, 8), frame(id, 9), frame(id, 10), frame(id, 11), frame(id, 12) );

        die = new Animation( 10, false );
        die.frames( frames, frame(id, 0), frame(id, 12), frame(id, 0), frame(id, 12) );

        play( idle );
    }
}
