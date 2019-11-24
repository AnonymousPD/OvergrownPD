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

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
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
import com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfHaste;
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
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;

import java.util.ArrayList;
import java.util.HashMap;

public class ExoticPotion extends Potion {
	
	{
		//sprite = equivalent potion sprite but one row down
	}
	
	public static final HashMap<Class<?extends Potion>, Class<?extends ExoticPotion>> regToExo = new HashMap<>();
	public static final HashMap<Class<?extends ExoticPotion>, Class<?extends Potion>> exoToReg = new HashMap<>();
	static{
		regToExo.put(PotionOfHealing.class, PotionOfShielding.class);
		exoToReg.put(PotionOfShielding.class, PotionOfHealing.class);
		
		regToExo.put(PotionOfToxicGas.class, PotionOfCorrosiveGas.class);
		exoToReg.put(PotionOfCorrosiveGas.class, PotionOfToxicGas.class);
		
		regToExo.put(PotionOfStrength.class, PotionOfAdrenalineSurge.class);
		exoToReg.put(PotionOfAdrenalineSurge.class, PotionOfStrength.class);
		
		regToExo.put(PotionOfFrost.class, PotionOfSnapFreeze.class);
		exoToReg.put(PotionOfSnapFreeze.class, PotionOfFrost.class);
		
		regToExo.put(PotionOfHaste.class, PotionOfStamina.class);
		exoToReg.put(PotionOfStamina.class, PotionOfHaste.class);
		
		regToExo.put(PotionOfLiquidFlame.class, PotionOfDragonsBreath.class);
		exoToReg.put(PotionOfDragonsBreath.class, PotionOfLiquidFlame.class);
		
		regToExo.put(PotionOfInvisibility.class, PotionOfShroudingFog.class);
		exoToReg.put(PotionOfShroudingFog.class, PotionOfInvisibility.class);
		
		regToExo.put(PotionOfMindVision.class, PotionOfMagicalSight.class);
		exoToReg.put(PotionOfMagicalSight.class, PotionOfMindVision.class);
		
		regToExo.put(PotionOfLevitation.class, PotionOfStormClouds.class);
		exoToReg.put(PotionOfStormClouds.class, PotionOfLevitation.class);
		
		regToExo.put(PotionOfExperience.class, PotionOfHolyFuror.class);
		exoToReg.put(PotionOfHolyFuror.class, PotionOfExperience.class);
		
		regToExo.put(PotionOfPurity.class, PotionOfCleansing.class);
		exoToReg.put(PotionOfCleansing.class, PotionOfPurity.class);
		
		regToExo.put(PotionOfParalyticGas.class, PotionOfEarthenArmor.class);
		exoToReg.put(PotionOfEarthenArmor.class, PotionOfParalyticGas.class);
        /***/
        regToExo.put(PotionOfBall.class, PotionOfSpiral.class);
        exoToReg.put(PotionOfSpiral.class, PotionOfBall.class);

        regToExo.put(PotionOfBanana.class, PotionOfProtain.class);
        exoToReg.put(PotionOfProtain.class, PotionOfBanana.class);

        regToExo.put(PotionOfBlessing.class, PotionOfHoly.class);
        exoToReg.put(PotionOfHoly.class, PotionOfBlessing.class);

        regToExo.put(PotionOfButter.class, PotionOfButterbread.class);
        exoToReg.put(PotionOfButterbread.class, PotionOfButter.class);

        regToExo.put(PotionOfChilli.class, PotionOfTears.class);
        exoToReg.put(PotionOfTears.class, PotionOfChilli.class);

        regToExo.put(PotionOfDew.class, PotionOfSuperdew.class);
        exoToReg.put(PotionOfSuperdew.class, PotionOfDew.class);

        regToExo.put(PotionOfDigesting.class, PotionOfStomach.class);
        exoToReg.put(PotionOfStomach.class, PotionOfDigesting.class);

        regToExo.put(PotionOfDirt.class, PotionOfSoil.class);
        exoToReg.put(PotionOfSoil.class, PotionOfDirt.class);

        regToExo.put(PotionOfEgg.class, PotionOfOrb.class);
        exoToReg.put(PotionOfOrb.class, PotionOfEgg.class);

        regToExo.put(PotionOfEye.class, PotionOfAllSeeing.class);
        exoToReg.put(PotionOfAllSeeing.class, PotionOfEye.class);

        regToExo.put(PotionOfFirelightning.class, PotionOfBallLightning.class);
        exoToReg.put(PotionOfBallLightning.class, PotionOfFirelightning.class);

        regToExo.put(PotionOfFirestorm.class, PotionOfHellstorm.class);
        exoToReg.put(PotionOfHellstorm.class, PotionOfFirestorm.class);

        regToExo.put(PotionOfFlora.class, PotionOfFlower.class);
        exoToReg.put(PotionOfFlower.class, PotionOfFlora.class);

        regToExo.put(PotionOfGlowing.class, PotionOfRadiation.class);
        exoToReg.put(PotionOfRadiation.class, PotionOfGlowing.class);

        regToExo.put(PotionOfGoo.class, PotionOfGloop.class);
        exoToReg.put(PotionOfGloop.class, PotionOfGoo.class);

        regToExo.put(PotionOfGrass.class, PotionOfHighgrass.class);
        exoToReg.put(PotionOfHighgrass.class, PotionOfGrass.class);

        regToExo.put(PotionOfHarvest.class, PotionOfAutumn.class);
        exoToReg.put(PotionOfAutumn.class, PotionOfHarvest.class);

        regToExo.put(PotionOfHoney.class, PotionOfBee.class);
        exoToReg.put(PotionOfBee.class, PotionOfHoney.class);

        regToExo.put(PotionOfHunger.class, PotionOfStarving.class);
        exoToReg.put(PotionOfStarving.class, PotionOfHunger.class);

        regToExo.put(PotionOfHydrogenFire.class, PotionOfMagicFire.class);
        exoToReg.put(PotionOfMagicFire.class, PotionOfHydrogenFire.class);

        regToExo.put(PotionOfHypno.class, PotionOfControl.class);
        exoToReg.put(PotionOfControl.class, PotionOfHypno.class);

        regToExo.put(PotionOfIceStorm.class, PotionOfAbsoluteZero.class);
        exoToReg.put(PotionOfAbsoluteZero.class, PotionOfIceStorm.class);

        regToExo.put(PotionOfInfection.class, PotionOfPlague.class);
        exoToReg.put(PotionOfPlague.class, PotionOfInfection.class);

        regToExo.put(PotionOfKiwi.class, PotionOfTerror.class);
        exoToReg.put(PotionOfTerror.class, PotionOfKiwi.class);

        regToExo.put(PotionOfLantern.class, PotionOfBeacon.class);
        exoToReg.put(PotionOfBeacon.class, PotionOfLantern.class);

        regToExo.put(PotionOfLightning.class, PotionOfAthmosphericCompression.class);
        exoToReg.put(PotionOfAthmosphericCompression.class, PotionOfLightning.class);

        regToExo.put(PotionOfLove.class, PotionOfReproduction.class);
        exoToReg.put(PotionOfReproduction.class, PotionOfLove.class);

        regToExo.put(PotionOfMuscle.class, PotionOfBrain.class);
        exoToReg.put(PotionOfBrain.class, PotionOfMuscle.class);

        regToExo.put(PotionOfParasites.class, PotionOfWorm.class);
        exoToReg.put(PotionOfWorm.class, PotionOfParasites.class);

        regToExo.put(PotionOfPeanuts.class, PotionOfNuts.class);
        exoToReg.put(PotionOfNuts.class, PotionOfPeanuts.class);

        regToExo.put(PotionOfPepper.class, PotionOfHotness.class);
        exoToReg.put(PotionOfHotness.class, PotionOfPepper.class);

        regToExo.put(PotionOfProtection.class, PotionOfArmor.class);
        exoToReg.put(PotionOfArmor.class, PotionOfProtection.class);

        regToExo.put(PotionOfRegrowth.class, PotionOfImmortality.class);
        exoToReg.put(PotionOfImmortality.class, PotionOfRegrowth.class);

        regToExo.put(PotionOfSecreting.class, PotionOfBleeding.class);
        exoToReg.put(PotionOfBleeding.class, PotionOfSecreting.class);

        regToExo.put(PotionOfSeed.class, PotionOfSowing.class);
        exoToReg.put(PotionOfSowing.class, PotionOfSeed.class);

        regToExo.put(PotionOfShadows.class, PotionOfSleepParalysis.class);
        exoToReg.put(PotionOfSleepParalysis.class, PotionOfShadows.class);

        regToExo.put(PotionOfShield.class, PotionOfIronSkin.class);
        exoToReg.put(PotionOfIronSkin.class, PotionOfShield.class);

        regToExo.put(PotionOfSlowness.class, PotionOfSlime.class);
        exoToReg.put(PotionOfSlime.class, PotionOfSlowness.class);

        regToExo.put(PotionOfSmoke.class, PotionOfAsh.class);
        exoToReg.put(PotionOfAsh.class, PotionOfSmoke.class);

        regToExo.put(PotionOfSnowstorm.class, PotionOfHail.class);
        exoToReg.put(PotionOfHail.class, PotionOfSnowstorm.class);

        regToExo.put(PotionOfSoda.class, PotionOfSugar.class);
        exoToReg.put(PotionOfSugar.class, PotionOfSoda.class);

        regToExo.put(PotionOfSteam.class, PotionOfPressure.class);
        exoToReg.put(PotionOfPressure.class, PotionOfSteam.class);

        regToExo.put(PotionOfSun.class, PotionOfSupernova.class);
        exoToReg.put(PotionOfSupernova.class, PotionOfSun.class);

        regToExo.put(PotionOfTeleporting.class, PotionOfQuantumsoup.class);
        exoToReg.put(PotionOfQuantumsoup.class, PotionOfTeleporting.class);

        regToExo.put(PotionOfTime.class, PotionOfRelativity.class);
        exoToReg.put(PotionOfRelativity.class, PotionOfTime.class);

        regToExo.put(PotionOfTomatoSoup.class, PotionOfSwelling.class);
        exoToReg.put(PotionOfSwelling.class, PotionOfTomatoSoup.class);

        regToExo.put(PotionOfUltraviolett.class, PotionOfLaserbeam.class);
        exoToReg.put(PotionOfLaserbeam.class, PotionOfUltraviolett.class);

        regToExo.put(PotionOfVine.class, PotionOfStrung.class);
        exoToReg.put(PotionOfStrung.class, PotionOfVine.class);

        regToExo.put(PotionOfWater.class, PotionOfTsunami.class);
        exoToReg.put(PotionOfTsunami.class, PotionOfWater.class);

        regToExo.put(PotionOfWine.class, PotionOfAlcohol.class);
        exoToReg.put(PotionOfAlcohol.class, PotionOfWine.class);

        regToExo.put(PotionOfWithering.class, PotionOfDeath.class);
        exoToReg.put(PotionOfDeath.class, PotionOfWithering.class);
	}
	
	@Override
	public boolean isKnown() {
		return anonymous || (handler != null && handler.isKnown( exoToReg.get(this.getClass()) ));
	}
	
	@Override
	public void setKnown() {
		if (!isKnown()) {
			handler.know(exoToReg.get(this.getClass()));
			updateQuickslot();
			Potion p = Dungeon.hero.belongings.getItem(getClass());
			if (p != null)  p.setAction();
			p = Dungeon.hero.belongings.getItem(exoToReg.get(this.getClass()));
			if (p != null)  p.setAction();
		}
	}
	
	@Override
	public void reset() {
		super.reset();
		if (handler != null && handler.contains(exoToReg.get(this.getClass()))) {
			image = handler.image(exoToReg.get(this.getClass())) + 16;
			color = handler.label(exoToReg.get(this.getClass()));
		}
	}
	
	@Override
	//20 gold more than its none-exotic equivalent
	public int price() {
		try {
			return (exoToReg.get(getClass()).newInstance().price() + 20) * quantity;
		} catch (Exception e){
			OvergrownPixelDungeon.reportException(e);
			return 0;
		}
	}
	
	public static class PotionToExotic extends Recipe{
		
		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			int s = 0;
			Potion p = null;
			
			for (Item i : ingredients){
				if (i instanceof Plant.Seed){
					s++;
				} else if (regToExo.containsKey(i.getClass())) {
					p = (Potion)i;
				}
			}
			
			return p != null && s == 2;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 0;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Item result = null;
			
			for (Item i : ingredients){
				i.quantity(i.quantity()-1);
				if (regToExo.containsKey(i.getClass())) {
					try {
						result = regToExo.get(i.getClass()).newInstance();
					} catch (Exception e) {
						OvergrownPixelDungeon.reportException(e);
					}
				}
			}
			return result;
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			for (Item i : ingredients){
				if (regToExo.containsKey(i.getClass())) {
					try {
						return regToExo.get(i.getClass()).newInstance();
					} catch (Exception e) {
						OvergrownPixelDungeon.reportException(e);
					}
				}
			}
			return null;
			
		}
	}
}
