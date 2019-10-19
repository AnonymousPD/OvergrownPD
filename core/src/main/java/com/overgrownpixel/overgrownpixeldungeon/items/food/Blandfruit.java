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

package com.overgrownpixel.overgrownpixeldungeon.items.food;

import com.overgrownpixel.overgrownpixeldungeon.Challenges;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.R;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Hunger;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.Recipe;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.Potion;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfBall;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfBanana;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfBlessing;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfButter;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfChilli;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfDew;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfDigesting;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfDirt;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfEgg;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfExperience;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfEye;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfFirelightning;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfFirestorm;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfFlora;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfFrost;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfGlowing;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfGoo;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfGrass;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfHarvest;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfHealing;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfHoney;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfHunger;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfHydrogenFire;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfHypno;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfIceStorm;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfInfection;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfInvisibility;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfKiwi;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfLantern;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfLevitation;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfLightning;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfLove;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfMindVision;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfMuscle;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfParalyticGas;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfParasites;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfPeanuts;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfPepper;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfProtection;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfPurity;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfRegrowth;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfSecreting;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfSeed;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfShadows;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfShield;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfSlowness;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfSmoke;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfSnowstorm;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfSoda;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfSteam;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfStrength;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfSun;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfTeleporting;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfTime;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfTomatoSoup;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfToxicGas;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfUltraviolett;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfVine;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfWater;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfWine;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfWithering;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant.Seed;
import com.overgrownpixel.overgrownpixeldungeon.plants.Sungrass;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class Blandfruit extends Food {

	public Potion potionAttrib = null;
	public ItemSprite.Glowing potionGlow = null;

	{
		stackable = true;
		image = ItemSpriteSheet.BLANDFRUIT;

		//only applies when blandfruit is cooked
		energy = Hunger.STARVING;

		bones = true;
	}

	@Override
	public boolean isSimilar( Item item ) {
		if ( super.isSimilar(item) ){
			Blandfruit other = (Blandfruit) item;
			if (potionAttrib == null && other.potionAttrib == null) {
					return true;
			} else if (potionAttrib != null && other.potionAttrib != null
					&& potionAttrib.isSimilar(other.potionAttrib)){
					return true;
			}
		}
		return false;
	}

	@Override
	public void execute( Hero hero, String action ) {

		if (action.equals( AC_EAT ) && potionAttrib == null) {

			GLog.w( Messages.get(this, "raw"));
			return;

		}

		super.execute(hero, action);

		if (action.equals( AC_EAT ) && potionAttrib != null){

			potionAttrib.apply(hero);

		}
	}

	@Override
	public String desc() {
		if (potionAttrib== null) {
			return super.desc();
		} else {
			String desc = Messages.get(this, "desc_cooked") + "\n\n";
			if (potionAttrib instanceof PotionOfFrost
				|| potionAttrib instanceof PotionOfLiquidFlame
				|| potionAttrib instanceof PotionOfToxicGas
				|| potionAttrib instanceof PotionOfParalyticGas) {
				desc += Messages.get(this, "desc_throw");
			} else {
				desc += Messages.get(this, "desc_eat");
			}
			return desc;
		}
	}

	@Override
	public int price() {
		return 20 * quantity;
	}

	public Item cook(Seed seed){

		try {
			return imbuePotion(Potion.SeedToPotion.types.get(seed.getClass()).newInstance());
		} catch (Exception e) {
			OvergrownPixelDungeon.reportException(e);
			return null;
		}

	}

	public Item imbuePotion(Potion potion){

		potionAttrib = potion;
		potionAttrib.anonymize();

		potionAttrib.image = ItemSpriteSheet.BLANDFRUIT;

		if (potionAttrib instanceof PotionOfHealing){
			name = Messages.get(this, "sunfruit");
			potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.blandfruitsunfruit) );
		} else if (potionAttrib instanceof PotionOfStrength){
			name = Messages.get(this, "rotfruit");
			potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.blandfruitrotfruit) );
		} else if (potionAttrib instanceof PotionOfParalyticGas){
			name = Messages.get(this, "earthfruit");
			potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.blandfruitearthfruit) );
		} else if (potionAttrib instanceof PotionOfInvisibility){
			name = Messages.get(this, "blindfruit");
			potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.blandfruitblindfruit) );
		} else if (potionAttrib instanceof PotionOfLiquidFlame){
			name = Messages.get(this, "firefruit");
			potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.blandfruitfirefruit) );
		} else if (potionAttrib instanceof PotionOfFrost){
			name = Messages.get(this, "icefruit");
			potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.blandfruiticefruit) );
		} else if (potionAttrib instanceof PotionOfMindVision){
			name = Messages.get(this, "fadefruit");
			potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.blandfruitfadefruit) );
		} else if (potionAttrib instanceof PotionOfToxicGas){
			name = Messages.get(this, "sorrowfruit");
			potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.blandfruitsorrowfruit) );
		} else if (potionAttrib instanceof PotionOfLevitation) {
			name = Messages.get(this, "stormfruit");
			potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.blandfruitstormfruit) );
		} else if (potionAttrib instanceof PotionOfPurity) {
			name = Messages.get(this, "dreamfruit");
			potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.blandfruitdreamfruit) );
		} else if (potionAttrib instanceof PotionOfExperience) {
			name = Messages.get(this, "starfruit");
			potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.blandfruitstarfruit) );
		} else if (potionAttrib instanceof PotionOfBall) {
            name = Messages.get(this, "ballfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.ballcroppoisonparticle) );
        } else if (potionAttrib instanceof PotionOfBanana) {
            name = Messages.get(this, "bananafruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.bananabeanpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfBlessing) {
            name = Messages.get(this, "blessingfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.blueeyedsusanpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfButter) {
            name = Messages.get(this, "butterfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.butterlionpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfChilli) {
            name = Messages.get(this, "chillifruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.chillisnapperpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfDew) {
            name = Messages.get(this, "dewfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.dewcatcherpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfDigesting) {
            name = Messages.get(this, "stomachfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.sourpitcherpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfDirt) {
            name = Messages.get(this, "dirtfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.dirtdaisypoisonparticle) );
        } else if (potionAttrib instanceof PotionOfEgg) {
            name = Messages.get(this, "eggfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.eggbloompoisonparticle) );
        } else if (potionAttrib instanceof PotionOfEye) {
            name = Messages.get(this, "eyefruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.eyeeunoymuspoisonparticle) );
        } else if (potionAttrib instanceof PotionOfFirelightning) {
            name = Messages.get(this, "firelightningfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.crimsoncrownpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfFirestorm) {
            name = Messages.get(this, "firestormfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.firefoxglovepoisonparticle) );
        } else if (potionAttrib instanceof PotionOfFlora) {
            name = Messages.get(this, "florafruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.flowertreepoisonparticle) );
        } else if (potionAttrib instanceof PotionOfGlowing) {
            name = Messages.get(this, "glowfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.chandaliertailoisonparticle) );
        } else if (potionAttrib instanceof PotionOfGoo) {
            name = Messages.get(this, "goofruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.goograsspoisonparticle) );
        } else if (potionAttrib instanceof PotionOfGrass) {
            name = Messages.get(this, "grassfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.grasslillypoisonparticle) );
        } else if (potionAttrib instanceof PotionOfHarvest) {
            name = Messages.get(this, "harvestfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.cornwheatpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfHoney) {
            name = Messages.get(this, "honeyfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.combflowerpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfHunger) {
            name = Messages.get(this, "hungerfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.apricobushpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfHydrogenFire) {
            name = Messages.get(this, "hydrofirefruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.tankcabbagepoisonparticle) );
        } else if (potionAttrib instanceof PotionOfHypno) {
            name = Messages.get(this, "hypnofruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.hypnohemppoisonparticle) );
        } else if (potionAttrib instanceof PotionOfIceStorm) {
            name = Messages.get(this, "icestormfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.frostcornpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfInfection) {
            name = Messages.get(this, "infectionfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.larvaleavepoisonparticle) );
        } else if (potionAttrib instanceof PotionOfKiwi) {
            name = Messages.get(this, "kiwifruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.kiwivetchpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfLantern) {
            name = Messages.get(this, "lanternfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.lavenderlanternpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfLightning) {
            name = Messages.get(this, "lightningfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.lightninglillypoisonparticle) );
        } else if (potionAttrib instanceof PotionOfLove) {
            name = Messages.get(this, "lovefruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.clitbalmpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfMuscle) {
            name = Messages.get(this, "musclefruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.musclemosspoisonparticle) );
        } else if (potionAttrib instanceof PotionOfParasites) {
            name = Messages.get(this, "parasitefruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.parasiteshrubpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfPeanuts) {
            name = Messages.get(this, "peanutfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.peanutpetalpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfPepper) {
            name = Messages.get(this, "pepperfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.crimsonpepperpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfProtection) {
            name = Messages.get(this, "protectionfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.cocostuftpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfRegrowth) {
            name = Messages.get(this, "regrowthfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.feelerfernpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfSecreting) {
            name = Messages.get(this, "secretingfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.venusflytrappoisonparticle) );
        } else if (potionAttrib instanceof PotionOfSeed) {
            name = Messages.get(this, "seedfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.seedpodpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfShadows) {
            name = Messages.get(this, "shadowfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.shadowbloompoisonparticle) );
        } else if (potionAttrib instanceof PotionOfShield) {
            name = Messages.get(this, "shieldfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.rosepoisonparticle) );
        } else if (potionAttrib instanceof PotionOfSlowness) {
            name = Messages.get(this, "slownessfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.willowcanepoisonparticle) );
        } else if (potionAttrib instanceof PotionOfSmoke) {
            name = Messages.get(this, "smokefruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.nightshadeonionpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfSnowstorm) {
            name = Messages.get(this, "snowstormfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.snowhedgepoisonparticle) );
        } else if (potionAttrib instanceof PotionOfSoda) {
            name = Messages.get(this, "sodafruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.poppoplarpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfSteam) {
            name = Messages.get(this, "steamfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.steamweedpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfSun) {
            name = Messages.get(this, "sunnyfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.sunbloompoisonparticle) );
        } else if (potionAttrib instanceof PotionOfTeleporting) {
            name = Messages.get(this, "teleportingfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.blackholeflowerpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfTime) {
            name = Messages.get(this, "timefruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.clockcypresspoisonparticle) );
        } else if (potionAttrib instanceof PotionOfTomatoSoup) {
            name = Messages.get(this, "tomatofruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.tomatobushpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfUltraviolett) {
            name = Messages.get(this, "ultrafruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.suncarnivorepoisonparticle) );
        } else if (potionAttrib instanceof PotionOfVine) {
            name = Messages.get(this, "vinefruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.grassvinepoisonparticle) );
        } else if (potionAttrib instanceof PotionOfWater) {
            name = Messages.get(this, "waterfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.waterweedpoisonparticle) );
        } else if (potionAttrib instanceof PotionOfWine) {
            name = Messages.get(this, "winefruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.gobgrapepoisonparticle) );
        } else if (potionAttrib instanceof PotionOfWithering) {
            name = Messages.get(this, "witherfruit");
            potionGlow = new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.witherfennelpoisonparticle) );
        }

		return this;
	}

	public static final String POTIONATTRIB = "potionattrib";
	
	@Override
	protected void onThrow(int cell) {
		if (Dungeon.level.map[cell] == Terrain.WELL || Dungeon.level.pit[cell]) {
			super.onThrow( cell );
			
		} else if (potionAttrib instanceof PotionOfLiquidFlame ||
				potionAttrib instanceof PotionOfToxicGas ||
				potionAttrib instanceof PotionOfParalyticGas ||
				potionAttrib instanceof PotionOfFrost ||
				potionAttrib instanceof PotionOfLevitation ||
				potionAttrib instanceof PotionOfPurity ||
                potionAttrib instanceof PotionOfBall) {

			potionAttrib.shatter( cell );
			Dungeon.level.drop(new Chunks(), cell).sprite.drop();
			
		} else {
			super.onThrow( cell );
		}
	}
	
	@Override
	public void reset() {
		if (potionAttrib != null)
			imbuePotion(potionAttrib);
		else
			super.reset();
	}
	
	@Override
	public void storeInBundle(Bundle bundle){
		super.storeInBundle(bundle);
		bundle.put( POTIONATTRIB , potionAttrib);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		if (bundle.contains(POTIONATTRIB)) {
			imbuePotion((Potion) bundle.get(POTIONATTRIB));
		}
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return potionGlow;
	}
	
	public static class CookFruit extends Recipe {
		
		@Override
		//also sorts ingredients if it can
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 2) return false;
			
			if (ingredients.get(0) instanceof Blandfruit){
				if (!(ingredients.get(1) instanceof Seed)){
					return false;
				}
			} else if (ingredients.get(0) instanceof Seed){
				if (ingredients.get(1) instanceof Blandfruit){
					Item temp = ingredients.get(0);
					ingredients.set(0, ingredients.get(1));
					ingredients.set(1, temp);
				} else {
					return false;
				}
			} else {
				return false;
			}
			
			Blandfruit fruit = (Blandfruit) ingredients.get(0);
			Seed seed = (Seed) ingredients.get(1);
			
			if (fruit.quantity() >= 1 && fruit.potionAttrib == null
				&& seed.quantity() >= 1){

				if (Dungeon.isChallenged(Challenges.NO_HEALING)
						&& seed instanceof Sungrass.Seed){
					return false;
				}

				return true;
			}
			
			return false;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 3;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;
			
			ingredients.get(0).quantity(ingredients.get(0).quantity() - 1);
			ingredients.get(1).quantity(ingredients.get(1).quantity() - 1);
			
			
			return new Blandfruit().cook((Seed) ingredients.get(1));
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;
			
			return new Blandfruit().cook((Seed) ingredients.get(1));
		}
	}

	public static class Chunks extends Food {

		{
			stackable = true;
			image = ItemSpriteSheet.BLAND_CHUNKS;

			energy = Hunger.STARVING;

			bones = true;
		}

	}

}
