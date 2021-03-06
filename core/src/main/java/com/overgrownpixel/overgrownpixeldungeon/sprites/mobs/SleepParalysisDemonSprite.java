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

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.R;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs.MirrorImage;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.ShadowParticle;
import com.overgrownpixel.overgrownpixeldungeon.sprites.hero.HeroSprite;
import com.watabou.noosa.Game;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.particles.Emitter;

public class SleepParalysisDemonSprite extends MobSprite {

	private static final int FRAME_WIDTH	= 12;
	private static final int FRAME_HEIGHT	= 15;

    private Emitter shadowcloud;

	public SleepParalysisDemonSprite() {
		super();
		
		texture( Dungeon.hero.heroClass.spritesheet() );
		updateArmor( 0 );
		idle();
	}
	
	@Override
	public void link( Char ch ) {
		super.link( ch );
		updateArmor( ((MirrorImage)ch).armTier );
        if (shadowcloud == null) {
            shadowcloud = emitter();
            shadowcloud.pour( ShadowParticle.CURSE, 0.7f );
        }
	}

    @Override
    public void update() {

        super.update();

        if (shadowcloud != null) {
            shadowcloud.visible = visible;
        }
        this.alpha(0.4f);
    }

    @Override
    public void kill() {
        super.kill();

        if (shadowcloud != null) {
            shadowcloud.on = false;
        }
    }
	
	public void updateArmor( int tier ) {
		TextureFilm film = new TextureFilm( HeroSprite.tiers(), tier, FRAME_WIDTH, FRAME_HEIGHT );
		
		idle = new Animation( 1, true );
		idle.frames( film, 0, 0, 0, 1, 0, 0, 1, 1 );
		
		run = new Animation( 20, true );
		run.frames( film, 2, 3, 4, 5, 6, 7 );
		
		die = new Animation( 20, false );
		die.frames( film, 0 );
		
		attack = new Animation( 15, false );
		attack.frames( film, 13, 14, 15, 0 );
		
		idle();
	}

    @Override
    public void onComplete( Animation anim ) {

        super.onComplete( anim );

        if (anim == die) {
            emitter().burst(ShadowParticle.UP, 15 );
        }
    }

    @Override
    public int blood() {
        return Game.instance.getResources().getInteger(R.integer.wraithblood);
    }
}
