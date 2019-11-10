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
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.curses.Corrosion;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.CloakOfShadows;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.plants.Swiftthistle;
import com.overgrownpixel.overgrownpixeldungeon.sprites.CharSprite;
import com.overgrownpixel.overgrownpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;

import java.util.ArrayList;
import java.util.Arrays;

public class Shadow extends FlavourBuff {

	public static final float DURATION	= 20f;

	{
		type = buffType.NEUTRAL;
		announced = true;
	}
	
	@Override
	public boolean attachTo( Char target ) {
        ArrayList<Class<?>> buffs = new ArrayList<>(Arrays.asList(removableBuffs));
        for(Buff buff : target.buffs()){
            Class b = buff.getClass();
            if(buffs.contains(b)){
                buff.detach();
            }
        }
		if (super.attachTo( target )) {
			target.invisible++;
			if (target instanceof Hero && ((Hero) target).subClass == HeroSubClass.ASSASSIN){
				Buff.affect(target, Preparation.class);
			}
            Dungeon.observe();
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void detach() {
		if (target.invisible > 0)
			target.invisible--;
		super.detach();
        Dungeon.observe();
	}

    private static final Class<?>[] removableBuffs = new Class<?>[]{
            Adrenaline.class, AdrenalineSurge.class, Amok.class, ArcaneArmor.class,
            Awareness.class, Balling.class, Barkskin.class, Barrier.class, BeetleInfected.class,
            Berserk.class, Bleeding.class, Bless.class, Blindness.class, Burning.class, Charm.class,
            Chill.class, Cocoshield.class, Combo.class, Corrosion.class, Corruption.class, Cripple.class,
            Dehydrated.class, Digesting.class, Doom.class, Drowsy.class, EarthImbue.class, FireImbue.class,
            Foresight.class, Frost.class, FrostImbue.class, Fury.class, Glowing.class, HalomethaneBurning.class,
            Haste.class, Healing.class, HeatAura.class, Heavy.class, Honeyed.class, IceAura.class,
            Invisibility.class, Levitation.class, Light.class, MagicalSleep.class, MagicalSight.class,
            MagicalShield.class, MagicImmune.class, MarkOfTheNut.class, MindVision.class, Momentum.class,
            Ooze.class, Paralysis.class, ParasiticInfection.class, ParasiticSymbiosis.class, Poison.class,
            PrismaticGuard.class, Roots.class, RoseBarrier.class, Secreting.class, Shadows.class, Sleep.class,
            Slippery.class, Slow.class, SoulMark.class, SnowedIn.class, SpaceTimePowers.class, Speed.class, Sprouting.class,
            Stamina.class, Steaming.class, Stunned.class, SugarRush.class, Terror.class, Thorns.class, ToxicImbue.class,
            TrailOfFire.class, Vertigo.class, Weakness.class, Wither.class, VitaminC.class, VitaminS.class, VitaminW.class,
            VitaminG.class, VitaminSuper.class, SuperBalling.class, SoulFire.class, DewInfusion.class, Feelers.class,
            FleshEatingAcid.class, Eyeing.class, FiendBurning.class, Drunk.class, High.class,
    };

    @Override
    public boolean act() {
        ArrayList<Class<?>> buffs = new ArrayList<>(Arrays.asList(removableBuffs));
        for(Buff buff : target.buffs()){
            Class b = buff.getClass();
            if(buffs.contains(b)){
                buff.detach();
            }
        }
        return super.act();
    }

    @Override
	public int icon() {
		return BuffIndicator.SHADOW;
	}
	
	@Override
	public void tintIcon(Image icon) {
		greyIcon(icon, 5f, cooldown());
	}

	@Override
	public void fx(boolean on) {
		if (on) target.sprite.add( CharSprite.State.SHADOW );
		else if (target.invisible == 0) target.sprite.remove( CharSprite.State.SHADOW );
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

	public static void dispel() {
		Shadow buff = Dungeon.hero.buff( Shadow.class );
		if (buff != null) {
			buff.detach();
		}
		CloakOfShadows.cloakStealth cloakBuff = Dungeon.hero.buff( CloakOfShadows.cloakStealth.class );
		if (cloakBuff != null) {
			cloakBuff.dispel();
		}
		
		//these aren't forms of invisibilty, but do dispel at the same time as it.
		TimekeepersHourglass.timeFreeze timeFreeze = Dungeon.hero.buff( TimekeepersHourglass.timeFreeze.class );
		if (timeFreeze != null) {
			timeFreeze.detach();
		}
		
		Preparation prep = Dungeon.hero.buff( Preparation.class );
		if (prep != null){
			prep.detach();
		}

        Invisibility invisibility = Dungeon.hero.buff( Invisibility.class );
        if (invisibility != null){
            invisibility.detach();
        }
		
		Swiftthistle.TimeBubble bubble =  Dungeon.hero.buff( Swiftthistle.TimeBubble.class );
		if (bubble != null){
			bubble.detach();
		}
	}
}
