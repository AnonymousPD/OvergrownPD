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

package com.overgrownpixel.overgrownpixeldungeon.journal;

import com.overgrownpixel.overgrownpixeldungeon.Badges;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.ClothArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.HuntressArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.LeatherArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.MageArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.MailArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.PlateArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.RogueArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.ScaleArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.armor.WarriorArmor;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.CloakOfShadows;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.DriedRose;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.EtherealChains;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.HornOfPlenty;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.MasterThievesArmband;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.SandalsOfNature;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.TalismanOfForesight;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.UnstableSpellbook;
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
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfAccuracy;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfElements;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfEnergy;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfEvasion;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfForce;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfFuror;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfHaste;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfMight;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfSharpshooting;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfTenacity;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfWealth;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRage;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfTerror;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfBlastWave;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfCorrosion;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfCorruption;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfDisintegration;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfFireblast;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfFlock;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfFrost;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfLightning;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfLivingEarth;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfMagicMissile;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfPoison;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfPrismaticLight;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfRegrowth;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfTransfusion;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfWarding;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.axes.BattleAxe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.axes.Greataxe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.axes.HandAxe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.axes.HookedWaraxe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.axes.TwoSidedWaraxe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.axes.Waraxe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.blades.AssassinsBlade;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.blades.BroadBlade;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.blades.ButcherBlade;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.blades.DoubleBlade;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.blades.Hackblade;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.blades.RunicBlade;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.blades.SteelBlade;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.bows.GoldenBow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.bows.SmallBow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.canes.CaneBlade;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.canes.CaneSaw;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.canes.StuddedCane;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.chains.KnifeChain;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.chains.Kusarigama;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.chains.ManrikiKusari;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.chains.Nunchaku;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.clubs.BarbedStaff;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.clubs.Club;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.clubs.ClubSword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.clubs.Jutte;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.clubs.Kanabo;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.clubs.Mace;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.clubs.Quarterstaff;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.clubs.SpikedClub;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.clubs.SpikedStoneClub;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.Crossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.GoldenCrossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.IronCrossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.SmallCrossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.WoodenCrossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.curvedblades.Khopesh;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.curvedblades.PirateSabre;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.curvedblades.Sabre;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.curvedblades.Scimitar;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.fans.GunsenFan;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.fencing.DualFencingBlades;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.fencing.Rapier;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.flails.Flail;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.gloves.ClawGlove;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.gloves.Gauntlet;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.gloves.Gloves;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.gloves.Knuckleduster;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.gloves.TekkoKagi;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.gloves.Yawara;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.grates.CheeseGrater;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.halberds.Halberd;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.halberds.TripleHookedHalberd;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.hammers.Otsuchi;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.hammers.SteelWarhammer;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.hammers.WarHammer;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.harpoons.Harpoon;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.knifes.ClawKnife;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.knifes.Dagger;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.knifes.Dirk;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.knifes.HornKnife;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.knifes.ManjiSai;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.knifes.Sai;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.longswords.AdornedLongsword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.longswords.DoubleLongsword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.longswords.Longsword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.longswords.SteelLongsword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.mobweapons.gooweapons.GooWeapon;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.mobweapons.ratweapons.RatFangDagger;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.scythes.Scythe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.scythes.ScytheWheel;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.scythes.SpikedScythe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.shields.CrusaderKiteshield;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.shields.Greatshield;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.shields.KiteShield;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.shields.ParagonShield;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.shields.RoundShield;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.shields.Scyld;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.shields.SwordShield;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.shortswords.Shortsword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.shortswords.WornShortsword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.spears.BambooSpear;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.spears.BroadSpear;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.spears.Glaive;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.spears.Lance;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.spears.Naginata;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.spears.PendulumSpear;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.spears.RoundedSpear;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.spears.Sasumata;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.spears.Spear;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.staffs.MagesStaff;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.swords.Greatsword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.swords.Katana;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.swords.Sword;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.swords.SwordWheel;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.tridents.TridentSpear;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.whips.ChainWhip;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.whips.NailWhip;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.whips.Whip;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.whips.WhipSword;
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

public enum Catalog {
	
	WEAPONS,
	ARMOR,
	WANDS,
	RINGS,
	ARTIFACTS,
	POTIONS,
	SCROLLS;
	
	private LinkedHashMap<Class<? extends Item>, Boolean> seen = new LinkedHashMap<>();
	
	public Collection<Class<? extends Item>> items(){
		return seen.keySet();
	}
	
	public boolean allSeen(){
		for (Class<?extends Item> item : items()){
			if (!seen.get(item)){
				return false;
			}
		}
		return true;
	}
	
	static {
	    //TIER 1
        WEAPONS.seen.put( WornShortsword.class,             false);
        WEAPONS.seen.put( Gloves.class,                     false);
        WEAPONS.seen.put( Dagger.class,                     false);
        WEAPONS.seen.put( MagesStaff.class,                 false);
        WEAPONS.seen.put( StuddedCane.class,                false);
        WEAPONS.seen.put( CheeseGrater.class,               false);
        WEAPONS.seen.put( Otsuchi.class,                    false);
        WEAPONS.seen.put( Rapier.class,                     false);
        WEAPONS.seen.put( ClawKnife.class,                  false);
        WEAPONS.seen.put( HornKnife.class,                  false);
        WEAPONS.seen.put( Knuckleduster.class,              false);
        WEAPONS.seen.put( Yawara.class,                     false);
        WEAPONS.seen.put( SmallBow.class,                   false);
        WEAPONS.seen.put( Club.class,                       false);
        WEAPONS.seen.put( RatFangDagger.class,              false);

        //TIER 2
        WEAPONS.seen.put( Shortsword.class,                 false);
        WEAPONS.seen.put( HandAxe.class,                    false);
        WEAPONS.seen.put( Spear.class,                      false);
        WEAPONS.seen.put( Quarterstaff.class,               false);
        WEAPONS.seen.put( Dirk.class,                       false);
        WEAPONS.seen.put( CaneSaw.class,                    false);
        WEAPONS.seen.put( Nunchaku.class,                   false);
        WEAPONS.seen.put( NailWhip.class,                   false);
        WEAPONS.seen.put( ChainWhip.class,                  false);
        WEAPONS.seen.put( Scyld.class,                      false);
        WEAPONS.seen.put( DualFencingBlades.class,          false);
        WEAPONS.seen.put( Scythe.class,                     false);
        WEAPONS.seen.put( ClawGlove.class,                  false);
        WEAPONS.seen.put( WoodenCrossbow.class,             false);
        WEAPONS.seen.put( Khopesh.class,                    false);
        WEAPONS.seen.put( HookedWaraxe.class,               false);
        WEAPONS.seen.put( Jutte.class,                      false);
        WEAPONS.seen.put( SpikedStoneClub.class,            false);
        WEAPONS.seen.put( Naginata.class,                   false);
        WEAPONS.seen.put( Sasumata.class,                   false);

        //TIER 3
        WEAPONS.seen.put( Sword.class,                      false);
        WEAPONS.seen.put( Mace.class,                       false);
        WEAPONS.seen.put( Scimitar.class,                   false);
        WEAPONS.seen.put( RoundShield.class,                false);
        WEAPONS.seen.put( Sai.class,                        false);
        WEAPONS.seen.put( Whip.class,                       false);
        WEAPONS.seen.put( CaneBlade.class,                  false);
        WEAPONS.seen.put( KnifeChain.class,                 false);
        WEAPONS.seen.put( WhipSword.class,                  false);
        WEAPONS.seen.put( KiteShield.class,                 false);
        WEAPONS.seen.put( Halberd.class,                    false);
        WEAPONS.seen.put( ManjiSai.class,                   false);
        WEAPONS.seen.put( TekkoKagi.class,                  false);
        WEAPONS.seen.put( Harpoon.class,                    false);
        WEAPONS.seen.put( SmallCrossbow.class,              false);
        WEAPONS.seen.put( TwoSidedWaraxe.class,             false);
        WEAPONS.seen.put( Sabre.class,                      false);
        WEAPONS.seen.put( BarbedStaff.class,                false);
        WEAPONS.seen.put( Kanabo.class,                     false);
        WEAPONS.seen.put( GooWeapon.class,                  false);
        WEAPONS.seen.put( PendulumSpear.class,              false);
        WEAPONS.seen.put( DoubleBlade.class,                false);
        WEAPONS.seen.put( Hackblade.class,                  false);
        WEAPONS.seen.put( ButcherBlade.class,               false);

        //TIER 4
        WEAPONS.seen.put( Longsword.class,                  false);
        WEAPONS.seen.put( BattleAxe.class,                  false);
        WEAPONS.seen.put( Flail.class,                      false);
        WEAPONS.seen.put( RunicBlade.class,                 false);
        WEAPONS.seen.put( AssassinsBlade.class,             false);
        WEAPONS.seen.put( Crossbow.class,                   false);
        WEAPONS.seen.put( Kusarigama.class,                 false);
        WEAPONS.seen.put( TridentSpear.class,               false);
        WEAPONS.seen.put( Katana.class,                     false);
        WEAPONS.seen.put( ParagonShield.class,              false);
        WEAPONS.seen.put( CrusaderKiteshield.class,         false);
        WEAPONS.seen.put( SteelWarhammer.class,             false);
        WEAPONS.seen.put( ScytheWheel.class,                false);
        WEAPONS.seen.put( IronCrossbow.class,               false);
        WEAPONS.seen.put( AdornedLongsword.class,           false);
        WEAPONS.seen.put( DoubleLongsword.class,            false);
        WEAPONS.seen.put( PirateSabre.class,                false);
        WEAPONS.seen.put( ClubSword.class,                  false);
        WEAPONS.seen.put( SpikedClub.class,                 false);
        WEAPONS.seen.put( BambooSpear.class,                false);
        WEAPONS.seen.put( Lance.class,                      false);
        WEAPONS.seen.put( BroadBlade.class,                 false);
        WEAPONS.seen.put( SteelBlade.class,                 false);

        //TIER 5
        WEAPONS.seen.put( Greatsword.class,                 false);
        WEAPONS.seen.put( WarHammer.class,                  false);
        WEAPONS.seen.put( Glaive.class,                     false);
        WEAPONS.seen.put( Greataxe.class,                   false);
        WEAPONS.seen.put( Greatshield.class,                false);
        WEAPONS.seen.put( Gauntlet.class,                   false);
        WEAPONS.seen.put( ManrikiKusari.class,              false);
        WEAPONS.seen.put( GunsenFan.class,                  false);
        WEAPONS.seen.put( SwordWheel.class,                 false);
        WEAPONS.seen.put( SwordShield.class,                false);
        WEAPONS.seen.put( TripleHookedHalberd.class,        false);
        WEAPONS.seen.put( SpikedScythe.class,               false);
        WEAPONS.seen.put( GoldenBow.class,                  false);
        WEAPONS.seen.put( GoldenCrossbow.class,             false);
        WEAPONS.seen.put( SteelLongsword.class,             false);
        WEAPONS.seen.put( Waraxe.class,                     false);
        WEAPONS.seen.put( BroadSpear.class,                 false);
        WEAPONS.seen.put( RoundedSpear.class,               false);
	
		ARMOR.seen.put( ClothArmor.class,                   false);
		ARMOR.seen.put( LeatherArmor.class,                 false);
		ARMOR.seen.put( MailArmor.class,                    false);
		ARMOR.seen.put( ScaleArmor.class,                   false);
		ARMOR.seen.put( PlateArmor.class,                   false);
		ARMOR.seen.put( WarriorArmor.class,                 false);
		ARMOR.seen.put( MageArmor.class,                    false);
		ARMOR.seen.put( RogueArmor.class,                   false);
		ARMOR.seen.put( HuntressArmor.class,                false);
	
		WANDS.seen.put( WandOfMagicMissile.class,           false);
		WANDS.seen.put( WandOfLightning.class,              false);
		WANDS.seen.put( WandOfDisintegration.class,         false);
		WANDS.seen.put( WandOfFireblast.class,              false);
        WANDS.seen.put( WandOfFlock.class,                  false);
        WANDS.seen.put( WandOfPoison.class,                 false);
		WANDS.seen.put( WandOfCorrosion.class,              false);
		WANDS.seen.put( WandOfBlastWave.class,              false);
		WANDS.seen.put( WandOfLivingEarth.class,            false);
		WANDS.seen.put( WandOfFrost.class,                  false);
		WANDS.seen.put( WandOfPrismaticLight.class,         false);
		WANDS.seen.put( WandOfWarding.class,                false);
		WANDS.seen.put( WandOfTransfusion.class,            false);
		WANDS.seen.put( WandOfCorruption.class,             false);
		WANDS.seen.put( WandOfRegrowth.class,               false);
	
		RINGS.seen.put( RingOfAccuracy.class,               false);
		RINGS.seen.put( RingOfEnergy.class,                 false);
		RINGS.seen.put( RingOfElements.class,               false);
		RINGS.seen.put( RingOfEvasion.class,                false);
		RINGS.seen.put( RingOfForce.class,                  false);
		RINGS.seen.put( RingOfFuror.class,                  false);
		RINGS.seen.put( RingOfHaste.class,                  false);
		RINGS.seen.put( RingOfMight.class,                  false);
		RINGS.seen.put( RingOfSharpshooting.class,          false);
		RINGS.seen.put( RingOfTenacity.class,               false);
		RINGS.seen.put( RingOfWealth.class,                 false);
	
		ARTIFACTS.seen.put( AlchemistsToolkit.class,        false);
		//ARTIFACTS.seen.put( CapeOfThorns.class,             false);
		ARTIFACTS.seen.put( ChaliceOfBlood.class,           false);
		ARTIFACTS.seen.put( CloakOfShadows.class,           false);
		ARTIFACTS.seen.put( DriedRose.class,                false);
		ARTIFACTS.seen.put( EtherealChains.class,           false);
		ARTIFACTS.seen.put( HornOfPlenty.class,             false);
		//ARTIFACTS.seen.put( LloydsBeacon.class,             false);
		ARTIFACTS.seen.put( MasterThievesArmband.class,     false);
		ARTIFACTS.seen.put( SandalsOfNature.class,          false);
		ARTIFACTS.seen.put( TalismanOfForesight.class,      false);
		ARTIFACTS.seen.put( TimekeepersHourglass.class,     false);
		ARTIFACTS.seen.put( UnstableSpellbook.class,        false);
	
		POTIONS.seen.put( PotionOfHealing.class,            false);
		POTIONS.seen.put( PotionOfStrength.class,           false);
		POTIONS.seen.put( PotionOfLiquidFlame.class,        false);
		POTIONS.seen.put( PotionOfFrost.class,              false);
		POTIONS.seen.put( PotionOfToxicGas.class,           false);
		POTIONS.seen.put( PotionOfParalyticGas.class,       false);
		POTIONS.seen.put( PotionOfPurity.class,             false);
		POTIONS.seen.put( PotionOfLevitation.class,         false);
		POTIONS.seen.put( PotionOfMindVision.class,         false);
		POTIONS.seen.put( PotionOfInvisibility.class,       false);
		POTIONS.seen.put( PotionOfExperience.class,         false);
		POTIONS.seen.put( PotionOfHaste.class,              false);

		//new potions
		POTIONS.seen.put( PotionOfBall.class,               false);
        POTIONS.seen.put( PotionOfBanana.class,             false);
        POTIONS.seen.put( PotionOfBlessing.class,           false);
        POTIONS.seen.put( PotionOfButter.class,             false);
        POTIONS.seen.put( PotionOfChilli.class,             false);
        POTIONS.seen.put( PotionOfDew.class,                false);
        POTIONS.seen.put( PotionOfDigesting.class,          false);
        POTIONS.seen.put( PotionOfDirt.class,               false);
        POTIONS.seen.put( PotionOfEgg.class,                false);
        POTIONS.seen.put( PotionOfEye.class,                false);
        POTIONS.seen.put( PotionOfFirelightning.class,      false);
        POTIONS.seen.put( PotionOfFirestorm.class,          false);
        POTIONS.seen.put( PotionOfFlora.class,              false);
        POTIONS.seen.put( PotionOfGlowing.class,            false);
        POTIONS.seen.put( PotionOfGoo.class,                false);
        POTIONS.seen.put( PotionOfGrass.class,              false);
        POTIONS.seen.put( PotionOfHarvest.class,            false);
        POTIONS.seen.put( PotionOfHoney.class,              false);
        POTIONS.seen.put( PotionOfHunger.class,             false);
        POTIONS.seen.put( PotionOfHydrogenFire.class,       false);
        POTIONS.seen.put( PotionOfHypno.class,              false);
        POTIONS.seen.put( PotionOfIceStorm.class,           false);
        POTIONS.seen.put( PotionOfInfection.class,          false);
        POTIONS.seen.put( PotionOfKiwi.class,               false);
        POTIONS.seen.put( PotionOfLantern.class,            false);
        POTIONS.seen.put( PotionOfLightning.class,          false);
        POTIONS.seen.put( PotionOfLove.class,               false);
        POTIONS.seen.put( PotionOfMuscle.class,             false);
        POTIONS.seen.put( PotionOfParasites.class,          false);
        POTIONS.seen.put( PotionOfPeanuts.class,            false);
        POTIONS.seen.put( PotionOfPepper.class,             false);
        POTIONS.seen.put( PotionOfProtection.class,         false);
        POTIONS.seen.put( PotionOfRegrowth.class,           false);
        POTIONS.seen.put( PotionOfSecreting.class,          false);
        POTIONS.seen.put( PotionOfSeed.class,               false);
        POTIONS.seen.put( PotionOfShadows.class,            false);
        POTIONS.seen.put( PotionOfShield.class,             false);
        POTIONS.seen.put( PotionOfSlowness.class,           false);
        POTIONS.seen.put( PotionOfSmoke.class,              false);
        POTIONS.seen.put( PotionOfSnowstorm.class,          false);
        POTIONS.seen.put( PotionOfSoda.class,               false);
        POTIONS.seen.put( PotionOfSteam.class,              false);
        POTIONS.seen.put( PotionOfSun.class,                false);
        POTIONS.seen.put( PotionOfTeleporting.class,        false);
        POTIONS.seen.put( PotionOfTime.class,               false);
        POTIONS.seen.put( PotionOfTomatoSoup.class,         false);
        POTIONS.seen.put( PotionOfUltraviolett.class,       false);
        POTIONS.seen.put( PotionOfVine.class,               false);
        POTIONS.seen.put( PotionOfWater.class,              false);
        POTIONS.seen.put( PotionOfWine.class,               false);
        POTIONS.seen.put( PotionOfWithering.class,          false);
	
		SCROLLS.seen.put( ScrollOfIdentify.class,           false);
		SCROLLS.seen.put( ScrollOfUpgrade.class,            false);
		SCROLLS.seen.put( ScrollOfRemoveCurse.class,        false);
		SCROLLS.seen.put( ScrollOfMagicMapping.class,       false);
		SCROLLS.seen.put( ScrollOfTeleportation.class,      false);
		SCROLLS.seen.put( ScrollOfRecharging.class,         false);
		SCROLLS.seen.put( ScrollOfMirrorImage.class,        false);
		SCROLLS.seen.put( ScrollOfTerror.class,             false);
		SCROLLS.seen.put( ScrollOfLullaby.class,            false);
		SCROLLS.seen.put( ScrollOfRage.class,               false);
		SCROLLS.seen.put( ScrollOfRetribution.class,        false);
		SCROLLS.seen.put( ScrollOfTransmutation.class,      false);
	}
	
	public static LinkedHashMap<Catalog, Badges.Badge> catalogBadges = new LinkedHashMap<>();
	static {
		catalogBadges.put(WEAPONS, Badges.Badge.ALL_WEAPONS_IDENTIFIED);
		catalogBadges.put(ARMOR, Badges.Badge.ALL_ARMOR_IDENTIFIED);
		catalogBadges.put(WANDS, Badges.Badge.ALL_WANDS_IDENTIFIED);
		catalogBadges.put(RINGS, Badges.Badge.ALL_RINGS_IDENTIFIED);
		catalogBadges.put(ARTIFACTS, Badges.Badge.ALL_ARTIFACTS_IDENTIFIED);
		catalogBadges.put(POTIONS, Badges.Badge.ALL_POTIONS_IDENTIFIED);
		catalogBadges.put(SCROLLS, Badges.Badge.ALL_SCROLLS_IDENTIFIED);
	}
	
	public static boolean isSeen(Class<? extends Item> itemClass){
		for (Catalog cat : values()) {
			if (cat.seen.containsKey(itemClass)) {
				return cat.seen.get(itemClass);
			}
		}
		return false;
	}
	
	public static void setSeen(Class<? extends Item> itemClass){
		for (Catalog cat : values()) {
			if (cat.seen.containsKey(itemClass) && !cat.seen.get(itemClass)) {
				cat.seen.put(itemClass, true);
				Journal.saveNeeded = true;
			}
		}
		Badges.validateItemsIdentified();
	}
	
	private static final String CATALOGS = "catalogs";
	
	public static void store( Bundle bundle ){
		
		Badges.loadGlobal();
		
		ArrayList<String> seen = new ArrayList<>();
		
		//if we have identified all items of a set, we use the badge to keep track instead.
		if (!Badges.isUnlocked(Badges.Badge.ALL_ITEMS_IDENTIFIED)) {
			for (Catalog cat : values()) {
				if (!Badges.isUnlocked(catalogBadges.get(cat))) {
					for (Class<? extends Item> item : cat.items()) {
						if (cat.seen.get(item)) seen.add(item.getSimpleName());
					}
				}
			}
		}
		
		bundle.put( CATALOGS, seen.toArray(new String[0]) );
		
	}
	
	public static void restore( Bundle bundle ){
		
		Badges.loadGlobal();
		
		//logic for if we have all badges
		if (Badges.isUnlocked(Badges.Badge.ALL_ITEMS_IDENTIFIED)){
			for ( Catalog cat : values()){
				for (Class<? extends Item> item : cat.items()){
					cat.seen.put(item, true);
				}
			}
			return;
		}
		
		//catalog-specific badge logic
		for (Catalog cat : values()){
			if (Badges.isUnlocked(catalogBadges.get(cat))){
				for (Class<? extends Item> item : cat.items()){
					cat.seen.put(item, true);
				}
			}
		}
		
		//general save/load
		if (bundle.contains(CATALOGS)) {
			List<String> seen = Arrays.asList(bundle.getStringArray(CATALOGS));
			
			//TODO should adjust this to tie into the bundling system's class array
			for (Catalog cat : values()) {
				for (Class<? extends Item> item : cat.items()) {
					if (seen.contains(item.getSimpleName())) {
						cat.seen.put(item, true);
					}
				}
			}
		}
	}
	
}
