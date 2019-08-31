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

import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blizzard;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.ConfusionGas;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.CorrosiveGas;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Depressant;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Electricity;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Fire;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Firewind;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Freezing;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.HalomethaneFire;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Inferno;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Miasma;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.NectarWind;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.ParalyticGas;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.PoisonGas;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Regrowth;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.SmokeScreen;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.StenchGas;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.StormCloud;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Sunlight;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.ToxicGas;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.UnfilteredSunlight;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Web;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

public class BlobImmunity extends FlavourBuff {
	
	{
		type = buffType.POSITIVE;
	}
	
	public static final float DURATION	= 20f;
	
	@Override
	public int icon() {
		return BuffIndicator.IMMUNITY;
	}
	
	@Override
	public void tintIcon(Image icon) {
		greyIcon(icon, 5f, cooldown());
	}
	
	@Override
	public String toString() {
		return Messages.get(this, "name");
	}

	{
		//all harmful blobs
		immunities.add( Blizzard.class );
		immunities.add( ConfusionGas.class );
		immunities.add( CorrosiveGas.class );
		immunities.add( Electricity.class );
		immunities.add( Fire.class );
		immunities.add( Freezing.class );
		immunities.add( Inferno.class );
		immunities.add( ParalyticGas.class );
		immunities.add( Regrowth.class );
		immunities.add( SmokeScreen.class );
		immunities.add( StenchGas.class );
		immunities.add( StormCloud.class );
		immunities.add( ToxicGas.class );
		immunities.add( Web.class );
        immunities.add( Depressant.class );
        immunities.add( HalomethaneFire.class );
        immunities.add( Miasma.class );
        immunities.add( NectarWind.class );
        immunities.add( PoisonGas.class );
        immunities.add( Sunlight.class );
        immunities.add( UnfilteredSunlight.class );
        immunities.add( Firewind.class );
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns());
	}
}
