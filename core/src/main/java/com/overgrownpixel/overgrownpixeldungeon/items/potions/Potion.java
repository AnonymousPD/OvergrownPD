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

package com.overgrownpixel.overgrownpixeldungeon.items.potions;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Badges;
import com.overgrownpixel.overgrownpixeldungeon.Challenges;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.R;
import com.overgrownpixel.overgrownpixeldungeon.Statistics;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Fire;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Burning;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.HalomethaneBurning;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Ooze;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.effects.Splash;
import com.overgrownpixel.overgrownpixeldungeon.items.Generator;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.ItemStatusHandler;
import com.overgrownpixel.overgrownpixeldungeon.items.Recipe;
import com.overgrownpixel.overgrownpixeldungeon.items.bags.Bag;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.elixirs.ElixirOfHoneyedHealing;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.ExoticPotion;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.PotionOfCleansing;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.PotionOfCorrosiveGas;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.PotionOfShroudingFog;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.PotionOfSnapFreeze;
import com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic.PotionOfStormClouds;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfRegrowth;
import com.overgrownpixel.overgrownpixeldungeon.journal.Catalog;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.plants.Apricobush;
import com.overgrownpixel.overgrownpixeldungeon.plants.Ballcrop;
import com.overgrownpixel.overgrownpixeldungeon.plants.Bananabean;
import com.overgrownpixel.overgrownpixeldungeon.plants.Blackholeflower;
import com.overgrownpixel.overgrownpixeldungeon.plants.Blindweed;
import com.overgrownpixel.overgrownpixeldungeon.plants.Blueeyedsusan;
import com.overgrownpixel.overgrownpixeldungeon.plants.Butterlion;
import com.overgrownpixel.overgrownpixeldungeon.plants.Chandaliertail;
import com.overgrownpixel.overgrownpixeldungeon.plants.Chillisnapper;
import com.overgrownpixel.overgrownpixeldungeon.plants.Clitbalm;
import com.overgrownpixel.overgrownpixeldungeon.plants.Clockcypress;
import com.overgrownpixel.overgrownpixeldungeon.plants.Cocostuft;
import com.overgrownpixel.overgrownpixeldungeon.plants.Combflower;
import com.overgrownpixel.overgrownpixeldungeon.plants.Cornwheat;
import com.overgrownpixel.overgrownpixeldungeon.plants.Crimsoncrown;
import com.overgrownpixel.overgrownpixeldungeon.plants.Crimsonpepper;
import com.overgrownpixel.overgrownpixeldungeon.plants.Dirtdaisy;
import com.overgrownpixel.overgrownpixeldungeon.plants.Dreamfoil;
import com.overgrownpixel.overgrownpixeldungeon.plants.Earthroot;
import com.overgrownpixel.overgrownpixeldungeon.plants.Eggbloom;
import com.overgrownpixel.overgrownpixeldungeon.plants.Eyeeuonymus;
import com.overgrownpixel.overgrownpixeldungeon.plants.Fadeleaf;
import com.overgrownpixel.overgrownpixeldungeon.plants.Feelerfern;
import com.overgrownpixel.overgrownpixeldungeon.plants.Firebloom;
import com.overgrownpixel.overgrownpixeldungeon.plants.Firefoxglove;
import com.overgrownpixel.overgrownpixeldungeon.plants.Flowertree;
import com.overgrownpixel.overgrownpixeldungeon.plants.Frostcorn;
import com.overgrownpixel.overgrownpixeldungeon.plants.Gobgrape;
import com.overgrownpixel.overgrownpixeldungeon.plants.Goograss;
import com.overgrownpixel.overgrownpixeldungeon.plants.Grasslilly;
import com.overgrownpixel.overgrownpixeldungeon.plants.Grassvine;
import com.overgrownpixel.overgrownpixeldungeon.plants.Hypnohemp;
import com.overgrownpixel.overgrownpixeldungeon.plants.Icecap;
import com.overgrownpixel.overgrownpixeldungeon.plants.Kiwivetch;
import com.overgrownpixel.overgrownpixeldungeon.plants.Larvaleaf;
import com.overgrownpixel.overgrownpixeldungeon.plants.Lavenderlantern;
import com.overgrownpixel.overgrownpixeldungeon.plants.Lightninglily;
import com.overgrownpixel.overgrownpixeldungeon.plants.Musclemoss;
import com.overgrownpixel.overgrownpixeldungeon.plants.Nightshadeonion;
import com.overgrownpixel.overgrownpixeldungeon.plants.Parasiteshrub;
import com.overgrownpixel.overgrownpixeldungeon.plants.Peanutpetal;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.plants.Poppoplar;
import com.overgrownpixel.overgrownpixeldungeon.plants.Rose;
import com.overgrownpixel.overgrownpixeldungeon.plants.Rotberry;
import com.overgrownpixel.overgrownpixeldungeon.plants.Shadowbloom;
import com.overgrownpixel.overgrownpixeldungeon.plants.Snowhedge;
import com.overgrownpixel.overgrownpixeldungeon.plants.Sorrowmoss;
import com.overgrownpixel.overgrownpixeldungeon.plants.Sourpitcher;
import com.overgrownpixel.overgrownpixeldungeon.plants.Starflower;
import com.overgrownpixel.overgrownpixeldungeon.plants.Steamweed;
import com.overgrownpixel.overgrownpixeldungeon.plants.Stormvine;
import com.overgrownpixel.overgrownpixeldungeon.plants.Sunbloom;
import com.overgrownpixel.overgrownpixeldungeon.plants.Suncarnivore;
import com.overgrownpixel.overgrownpixeldungeon.plants.Sungrass;
import com.overgrownpixel.overgrownpixeldungeon.plants.Swiftthistle;
import com.overgrownpixel.overgrownpixeldungeon.plants.Tankcabbage;
import com.overgrownpixel.overgrownpixeldungeon.plants.Tomatobush;
import com.overgrownpixel.overgrownpixeldungeon.plants.Venusflytrap;
import com.overgrownpixel.overgrownpixeldungeon.plants.Waterweed;
import com.overgrownpixel.overgrownpixeldungeon.plants.Willowcane;
import com.overgrownpixel.overgrownpixeldungeon.plants.Witherfennel;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndBag;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndItem;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndOptions;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Potion extends Item {

	public static final String AC_DRINK = "DRINK";
	
	//used internally for potions that can be drunk or thrown
	public static final String AC_CHOOSE = "CHOOSE";

	private static final float TIME_TO_DRINK = 1f;

	protected Integer initials;

	private static final Class<?>[] potions = {
			PotionOfHealing.class,  //1
			PotionOfExperience.class,  //2
			PotionOfToxicGas.class,  //3
			PotionOfLiquidFlame.class,  //4
			PotionOfStrength.class,  //5
			PotionOfParalyticGas.class,  //6
			PotionOfLevitation.class,  //7
			PotionOfMindVision.class,  //8
			PotionOfPurity.class,  //9
			PotionOfInvisibility.class,  //10
			PotionOfHaste.class,  //11
			PotionOfFrost.class,  //12
            PotionOfHunger.class,  //13
            PotionOfBall.class,  //14
            PotionOfBanana.class,  //15
            PotionOfBlessing.class,   //16
            PotionOfTeleporting.class,  //17
            PotionOfButter.class,   //18
            PotionOfGlowing.class,   //19
            PotionOfChilli.class,   //20
            PotionOfLove.class,   //21
            PotionOfTime.class,  //22
            PotionOfProtection.class,   //23
            PotionOfHoney.class,   //24
            PotionOfHarvest.class,   //25
            PotionOfFirelightning.class,   //26
            PotionOfPepper.class,  //27
            PotionOfDirt.class,   //28
            PotionOfEgg.class,   //29
            PotionOfEye.class,   //30
            PotionOfRegrowth.class,  //31
            PotionOfFirestorm.class,  //32
            PotionOfFlora.class,  //33
            PotionOfIceStorm.class,  //34
            PotionOfWine.class,  //35
            PotionOfGoo.class,  //36
            PotionOfGrass.class,  //37
            PotionOfVine.class,  //38
            PotionOfHypno.class,  //39
            PotionOfKiwi.class,  //40
            PotionOfInfection.class,  //41
            PotionOfLantern.class,  //42
            PotionOfLightning.class,  //43
            PotionOfMuscle.class,  //44
            PotionOfSmoke.class,  //45
            PotionOfParasites.class,  //46
            PotionOfPeanuts.class,  //47
            PotionOfSoda.class,  //48
            PotionOfShield.class,  //49
            PotionOfShadows.class,  //50
            PotionOfSnowstorm.class,  //51
            PotionOfDigesting.class,  //52
            PotionOfSteam.class,  //53
            PotionOfSun.class,  //54
            PotionOfUltraviolett.class,  //55
            PotionOfHydrogenFire.class,  //56
            PotionOfTomatoSoup.class,  //57
            PotionOfSecreting.class,  //59
            PotionOfWater.class,  //60
            PotionOfSlowness.class,  //61
            PotionOfWithering.class,  //62
            PotionOfSeed.class,  //63
            PotionOfDew.class  //64
	};

	private static final HashMap<String, Integer> colors = new HashMap<String, Integer>() {
		{
			put("crimson",ItemSpriteSheet.POTION_CRIMSON);  //1
			put("amber",ItemSpriteSheet.POTION_AMBER);  //2
			put("golden",ItemSpriteSheet.POTION_GOLDEN);  //3
			put("jade",ItemSpriteSheet.POTION_JADE);  //4
			put("turquoise",ItemSpriteSheet.POTION_TURQUOISE);  //5
			put("azure",ItemSpriteSheet.POTION_AZURE);  //6
			put("indigo",ItemSpriteSheet.POTION_INDIGO);  //7
			put("magenta",ItemSpriteSheet.POTION_MAGENTA);  //8
			put("bistre",ItemSpriteSheet.POTION_BISTRE);  //9
			put("charcoal",ItemSpriteSheet.POTION_CHARCOAL);  //10
			put("silver",ItemSpriteSheet.POTION_SILVER);  //11
			put("ivory",ItemSpriteSheet.POTION_IVORY);  //12
            put("poisongreen",ItemSpriteSheet.POTION_POISON_GREEN);  //13
            put("snowwhite",ItemSpriteSheet.POTION_SNOW_WHITE);  //14
            put("muddygreen",ItemSpriteSheet.POTION_MUDDY_GREEN);  //15
            put("muddyyellow",ItemSpriteSheet.POTION_MUDDY_YELLOW);  //16
            put("honey",ItemSpriteSheet.POTION_HONEY);  //17
            put("bloody",ItemSpriteSheet.POTION_BLOODY);  //18
            put("orange",ItemSpriteSheet.POTION_ORANGE);  //19
            put("violett",ItemSpriteSheet.POTION_VIOLETT);  //20
            put("yellow",ItemSpriteSheet.POTION_YELLOW);  //21
            put("white",ItemSpriteSheet.POTION_WHITE);  //22
            put("brown",ItemSpriteSheet.POTION_BROWN);  //23
            put("brightblue",ItemSpriteSheet.POTION_BRIGHT_BLUE);  //24
            put("rainbow",ItemSpriteSheet.POTION_RAINBOW);  //25
            put("brightorange",ItemSpriteSheet.POTION_BRIGHT_ORANGE);  //26
            put("darkblue",ItemSpriteSheet.POTION_DARK_BLUE);  //27
            put("black",ItemSpriteSheet.POTION_BLACK);  //28
            put("yelloworange",ItemSpriteSheet.POTION_YELLOW_ORANGE);  //29
            put("grassgreen",ItemSpriteSheet.POTION_GRASS_GREEN);  //30
            put("skyblue",ItemSpriteSheet.POTION_SKY_BLUE);  //31
            put("greenblue",ItemSpriteSheet.POTION_GREEN_BLUE);  //32
            put("blue",ItemSpriteSheet.POTION_BLUE);  //33
            put("flatblue",ItemSpriteSheet.POTION_FLAT_BLUE);  //34
            put("parasitic",ItemSpriteSheet.POTION_PARASITIC);  //35
            put("diarrhoea",ItemSpriteSheet.POTION_DIARRHOEA);  //36
            put("punch",ItemSpriteSheet.POTION_PUNCH);  //37
            put("beige",ItemSpriteSheet.POTION_BEIGE);  //38
            put("bloodypoop",ItemSpriteSheet.POTION_BLOODY_POOP);  //39
            put("waterblue",ItemSpriteSheet.POTION_WATER_BLUE);  //40
            put("brightgreen",ItemSpriteSheet.POTION_BRIGHT_GREEN);  //41
            put("indigopurple",ItemSpriteSheet.POTION_INDIGO_PURPLE);  //42
            put("limegreen",ItemSpriteSheet.POTION_LIME_GREEN);  //43
            put("rose",ItemSpriteSheet.POTION_ROSE);  //44
            put("brightpurple",ItemSpriteSheet.POTION_BRIGHT_PURPLE);  //45
            put("darkrose",ItemSpriteSheet.POTION_DARK_ROSE);  //46
            put("darkpurple",ItemSpriteSheet.POTION_DARK_PURPLE);  //47
            put("lightningblue",ItemSpriteSheet.POTION_LIGHTNING_BLUE);  //48
            put("vinered",ItemSpriteSheet.POTION_VINE_RED);  //49
            put("purewhite",ItemSpriteSheet.POTION_PURE_WHITE);  //50
            put("lightlimegreen",ItemSpriteSheet.POTION_LIGHT_LIME_GREEN);  //51
            put("palepurple",ItemSpriteSheet.POTION_PALE_PURPLE);  //52
            put("lightviolett",ItemSpriteSheet.POTION_LIGHT_VIOLETT);  //53
            put("blackwhite",ItemSpriteSheet.POTION_BLACK_WHITE);  //54
            put("whiteblack",ItemSpriteSheet.POTION_WHITE_BLACK);  //55
            put("paleviolett",ItemSpriteSheet.POTION_PALE_VIOLETT);  //56
            put("browngreen",ItemSpriteSheet.POTION_BROWN_GREEN);  //57
            put("darkbrowngreen",ItemSpriteSheet.POTION_DARK_BROWN_GREEN);  //58
            put("mossygreen",ItemSpriteSheet.POTION_MOSSY_GREEN);  //59
            put("purpleviolett",ItemSpriteSheet.POTION_PURPLE_VIOLETT);  //60
            put("lightbluegreen",ItemSpriteSheet.POTION_LIGHT_BLUE_GREEN);  //61
            put("lightrose",ItemSpriteSheet.POTION_LIGHT_ROSE);  //62
            put("yellowpurple",ItemSpriteSheet.POTION_YELLOW_PURPLE);  //63
            put("cornyellow",ItemSpriteSheet.POTION_CORN_YELLOW);  //64
		}
	};
	
	private static final HashSet<Class<?extends Potion>> mustThrowPots = new HashSet<>();
	static{
		mustThrowPots.add(PotionOfToxicGas.class);
		mustThrowPots.add(PotionOfLiquidFlame.class);
		mustThrowPots.add(PotionOfParalyticGas.class);
		mustThrowPots.add(PotionOfFrost.class);
        mustThrowPots.add(PotionOfButter.class);
        mustThrowPots.add(PotionOfDigesting.class);
        mustThrowPots.add(PotionOfDirt.class);
		
		//exotic
		mustThrowPots.add(PotionOfCorrosiveGas.class);
		mustThrowPots.add(PotionOfSnapFreeze.class);
		mustThrowPots.add(PotionOfShroudingFog.class);
		mustThrowPots.add(PotionOfStormClouds.class);
		
		//also all brews, hardcoded
	}
	
	private static final HashSet<Class<?extends Potion>> canThrowPots = new HashSet<>();
	static{
		canThrowPots.add(AlchemicalCatalyst.class);
		
		canThrowPots.add(PotionOfPurity.class);
		canThrowPots.add(PotionOfLevitation.class);
        canThrowPots.add(PotionOfBall.class);
        canThrowPots.add(PotionOfChilli.class);
        canThrowPots.add(PotionOfDew.class);
		
		//exotic
		canThrowPots.add(PotionOfCleansing.class);
		
		//elixirs
		canThrowPots.add(ElixirOfHoneyedHealing.class);
	}
	
	protected static ItemStatusHandler<Potion> handler;
	
	protected String color;
	
	{
		stackable = true;
		defaultAction = AC_DRINK;
	}
	
	@SuppressWarnings("unchecked")
	public static void initColors() {
		handler = new ItemStatusHandler<>( (Class<? extends Potion>[])potions, colors );
	}
	
	public static void save( Bundle bundle ) {
		handler.save( bundle );
	}

	public static void saveSelectively( Bundle bundle, ArrayList<Item> items ) {
		ArrayList<Class<?extends Item>> classes = new ArrayList<>();
		for (Item i : items){
			if (i instanceof ExoticPotion){
				if (!classes.contains(ExoticPotion.exoToReg.get(i.getClass()))){
					classes.add(ExoticPotion.exoToReg.get(i.getClass()));
				}
			} else if (i instanceof Potion){
				if (!classes.contains(i.getClass())){
					classes.add(i.getClass());
				}
			}
		}
		handler.saveClassesSelectively( bundle, classes );
	}
	
	@SuppressWarnings("unchecked")
	public static void restore( Bundle bundle ) {
		handler = new ItemStatusHandler<>( (Class<? extends Potion>[])potions, colors, bundle );
	}
	
	public Potion() {
		super();
		reset();
	}
	
	//anonymous potions are always IDed, do not affect ID status,
	//and their sprite is replaced by a placeholder if they are not known,
	//useful for items that appear in UIs, or which are only spawned for their effects
	protected boolean anonymous = false;
	public void anonymize(){
		if (!isKnown()) image = ItemSpriteSheet.POTION_HOLDER;
		anonymous = true;
	}

	@Override
	public void reset(){
		super.reset();
		if (handler != null && handler.contains(this)) {
			image = handler.image(this);
			color = handler.label(this);
		}
		setAction();
	}
	
	@Override
	public boolean collect( Bag container ) {
		if (super.collect( container )){
			setAction();
			return true;
		} else {
			return false;
		}
	}
	
	public void setAction(){
		if (isKnown() && mustThrowPots.contains(this.getClass())) {
			defaultAction = AC_THROW;
		} else if (isKnown() &&canThrowPots.contains(this.getClass())){
			defaultAction = AC_CHOOSE;
		} else {
			defaultAction = AC_DRINK;
		}
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_DRINK );
		return actions;
	}
	
	@Override
	public void execute( final Hero hero, String action ) {

		super.execute( hero, action );
		
		if (action.equals( AC_CHOOSE )){
			
			GameScene.show(new WndItem(null, this, true) );
			
		} else if (action.equals( AC_DRINK )) {
			
			if (isKnown() && mustThrowPots.contains(getClass())) {
				
					GameScene.show(
						new WndOptions( Messages.get(Potion.class, "harmful"),
								Messages.get(Potion.class, "sure_drink"),
								Messages.get(Potion.class, "yes"), Messages.get(Potion.class, "no") ) {
							@Override
							protected void onSelect(int index) {
								if (index == 0) {
									drink( hero );
								}
							};
						}
					);
					
				} else {
					drink( hero );
				}
			
		}
	}
	
	@Override
	public void doThrow( final Hero hero ) {

		if (isKnown()
				&& !mustThrowPots.contains(this.getClass())
				&& !canThrowPots.contains(this.getClass())) {
		
			GameScene.show(
				new WndOptions( Messages.get(Potion.class, "beneficial"),
						Messages.get(Potion.class, "sure_throw"),
						Messages.get(Potion.class, "yes"), Messages.get(Potion.class, "no") ) {
					@Override
					protected void onSelect(int index) {
						if (index == 0) {
							Potion.super.doThrow( hero );
						}
					};
				}
			);
			
		} else {
			super.doThrow( hero );
		}
	}
	
	protected void drink( Hero hero ) {
		
		detach( hero.belongings.backpack );
		
		hero.spend( TIME_TO_DRINK );
		hero.busy();
		apply( hero );
		
		Sample.INSTANCE.play( Assets.SND_DRINK );
		
		hero.sprite.operate( hero.pos );
	}
	
	@Override
	protected void onThrow( int cell ) {
		if (Dungeon.level.map[cell] == Terrain.WELL || Dungeon.level.pit[cell]) {
			
			super.onThrow( cell );
			
		} else  {

			Dungeon.level.press( cell, null, true );
			shatter( cell );
			
		}
	}
	
	public void apply( Hero hero ) {
		shatter( hero.pos );
	}
	
	public void shatter( int cell ) {
		if (Dungeon.level.heroFOV[cell]) {
			GLog.i( Messages.get(Potion.class, "shatter") );
			Sample.INSTANCE.play( Assets.SND_SHATTER );
			splash( cell );
		}
	}

	@Override
	public void cast( final Hero user, int dst ) {
			super.cast(user, dst);
	}
	
	public boolean isKnown() {
		return anonymous || (handler != null && handler.isKnown( this ));
	}
	
	public void setKnown() {
		if (!anonymous) {
			if (!isKnown()) {
				handler.know(this);
				updateQuickslot();
				Potion p = Dungeon.hero.belongings.getItem(getClass());
				if (p != null)  p.setAction();
				if (ExoticPotion.regToExo.get(getClass()) != null) {
					p = Dungeon.hero.belongings.getItem(ExoticPotion.regToExo.get(getClass()));
					if (p != null) p.setAction();
				}
			}
			
			if (Dungeon.hero.isAlive()) {
				Catalog.setSeen(getClass());
			}
		}
	}
	
	@Override
	public Item identify() {

		setKnown();
		return super.identify();
	}
	
	@Override
	public String name() {
		return isKnown() ? super.name() : Messages.get(this, color);
	}
	
	@Override
	public String info() {
		return isKnown() ? desc() : Messages.get(this, "unknown_desc");
	}

	public Integer initials(){
		return isKnown() ? initials : null;
	}
	
	@Override
	public boolean isIdentified() {
		return isKnown();
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	public static HashSet<Class<? extends Potion>> getKnown() {
		return handler.known();
	}
	
	public static HashSet<Class<? extends Potion>> getUnknown() {
		return handler.unknown();
	}
	
	public static boolean allKnown() {
		return handler.known().size() == potions.length;
	}
	
	protected int splashColor(){
		return anonymous ? Game.instance.getResources().getInteger(R.integer.potionsplash) : ItemSprite.pick( image, 5, 9 );
	}
	
	protected void splash( int cell ) {

		Fire fire = (Fire)Dungeon.level.blobs.get( Fire.class );
		if (fire != null)
			fire.clear( cell );

		final int color = splashColor();

		Char ch = Actor.findChar(cell);
		if (ch != null) {
			Buff.detach(ch, Burning.class);
            Buff.detach(ch, HalomethaneBurning.class);
			Buff.detach(ch, Ooze.class);
			Splash.at( ch.sprite.center(), color, 5 );
		} else {
			Splash.at( cell, color, 5 );
		}
	}
	
	@Override
	public int price() {
		return 30 * quantity;
	}
	
	public static class PlaceHolder extends Potion {
		
		{
			image = ItemSpriteSheet.POTION_HOLDER;
		}
		
		@Override
		public boolean isSimilar(Item item) {
			return ExoticPotion.regToExo.containsKey(item.getClass())
					|| ExoticPotion.regToExo.containsValue(item.getClass());
		}
		
		@Override
		public String info() {
			return "";
		}
	}
	
	public static class SeedToPotion extends Recipe {
		
		public static HashMap<Class<?extends Plant.Seed>, Class<?extends Potion>> types = new HashMap<>();
		static {
			types.put(Blindweed.Seed.class,         PotionOfInvisibility.class); //1
			types.put(Dreamfoil.Seed.class,         PotionOfPurity.class); //2
			types.put(Earthroot.Seed.class,         PotionOfParalyticGas.class); //3
			types.put(Fadeleaf.Seed.class,          PotionOfMindVision.class); //4
			types.put(Firebloom.Seed.class,         PotionOfLiquidFlame.class); //5
			types.put(Icecap.Seed.class,            PotionOfFrost.class); //6
			types.put(Rotberry.Seed.class,          PotionOfStrength.class); //7
			types.put(Sorrowmoss.Seed.class,        PotionOfToxicGas.class); //8
			types.put(Starflower.Seed.class,        PotionOfExperience.class); //9
			types.put(Stormvine.Seed.class,         PotionOfLevitation.class); //10
			types.put(Sungrass.Seed.class,          PotionOfHealing.class); //11
			types.put(Swiftthistle.Seed.class,      PotionOfHaste.class); //12
            types.put(Apricobush.Seed.class,        PotionOfHunger.class); //13
            types.put(Ballcrop.Seed.class,          PotionOfBall.class); //14
            types.put(Bananabean.Seed.class,        PotionOfBanana.class); //15
            types.put(Blueeyedsusan.Seed.class,     PotionOfBlessing.class); //16
            types.put(Blackholeflower.Seed.class,   PotionOfTeleporting.class); //17
            types.put(Butterlion.Seed.class,        PotionOfButter.class); //18
            types.put(Chandaliertail.Seed.class,    PotionOfGlowing.class); //19
            types.put(Chillisnapper.Seed.class,     PotionOfChilli.class); //20
            types.put(Clitbalm.Seed.class,          PotionOfLove.class); //21
            types.put(Clockcypress.Seed.class,      PotionOfTime.class); //22
            types.put(Cocostuft.Seed.class,         PotionOfProtection.class); //23
            types.put(Combflower.Seed.class,        PotionOfHoney.class); //24
            types.put(Cornwheat.Seed.class,         PotionOfHarvest.class); //25
            types.put(Crimsoncrown.Seed.class,      PotionOfFirelightning.class); //26
            types.put(Crimsonpepper.Seed.class,     PotionOfPepper.class); //27
            types.put(Dirtdaisy.Seed.class,         PotionOfDirt.class); //28
            types.put(Eggbloom.Seed.class,          PotionOfEgg.class); //29
            types.put(Eyeeuonymus.Seed.class,       PotionOfEye.class); //30
            types.put(Feelerfern.Seed.class,        PotionOfRegrowth.class); //31
            types.put(Firefoxglove.Seed.class,      PotionOfFirestorm.class); //32
            types.put(Flowertree.Seed.class,        PotionOfFlora.class); //33
            types.put(Frostcorn.Seed.class,         PotionOfIceStorm.class); //34
            types.put(Gobgrape.Seed.class,          PotionOfWine.class); //35
            types.put(Goograss.Seed.class,          PotionOfGoo.class); //36
            types.put(Grasslilly.Seed.class,        PotionOfGrass.class); //37
            types.put(Grassvine.Seed.class,         PotionOfVine.class); //38
            types.put(Hypnohemp.Seed.class,         PotionOfHypno.class); //39
            types.put(Kiwivetch.Seed.class,         PotionOfKiwi.class); //40
            types.put(Larvaleaf.Seed.class,         PotionOfInfection.class); //41
            types.put(Lavenderlantern.Seed.class,   PotionOfLantern.class); //42
            types.put(Lightninglily.Seed.class,     PotionOfLightning.class); //43
            types.put(Musclemoss.Seed.class,        PotionOfMuscle.class); //43
            types.put(Nightshadeonion.Seed.class,   PotionOfSmoke.class); //44
            types.put(Parasiteshrub.Seed.class,     PotionOfParasites.class); //45
            types.put(Peanutpetal.Seed.class,       PotionOfPeanuts.class); //46
            types.put(Poppoplar.Seed.class,         PotionOfSoda.class); //47
            types.put(Rose.Seed.class,              PotionOfShield.class); //48
            types.put(Shadowbloom.Seed.class,       PotionOfShadows.class); //49
            types.put(Snowhedge.Seed.class,         PotionOfSnowstorm.class); //50
            types.put(Sourpitcher.Seed.class,       PotionOfDigesting.class); //51
            types.put(Steamweed.Seed.class,         PotionOfSteam.class); //52
            types.put(Sunbloom.Seed.class,          PotionOfSun.class); //53
            types.put(Suncarnivore.Seed.class,      PotionOfUltraviolett.class); //54
            types.put(Tankcabbage.Seed.class,       PotionOfHydrogenFire.class); //55
            types.put(Tomatobush.Seed.class,        PotionOfTomatoSoup.class); //56
            types.put(Venusflytrap.Seed.class,      PotionOfSecreting.class); //57
            types.put(Waterweed.Seed.class,         PotionOfWater.class); //58
            types.put(Willowcane.Seed.class,        PotionOfSlowness.class); //59
            types.put(Witherfennel.Seed.class,      PotionOfWithering.class); //60
            types.put(WandOfRegrowth.Seedpod.Seed.class,      PotionOfSeed.class); //61
            types.put(WandOfRegrowth.Dewcatcher.Seed.class,   PotionOfDew.class); //62
		}
		
		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 3) {
				return false;
			}
			
			for (Item ingredient : ingredients){
				if (!(ingredient instanceof Plant.Seed
						&& ingredient.quantity() >= 1
						&& types.containsKey(ingredient.getClass()))){
					return false;
				}
			}
			return true;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 0;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;
			
			for (Item ingredient : ingredients){
				ingredient.quantity(ingredient.quantity() - 1);
			}
			
			ArrayList<Class<?extends Plant.Seed>> seeds = new ArrayList<>();
			for (Item i : ingredients) {
				if (!seeds.contains(i.getClass())) {
					seeds.add((Class<? extends Plant.Seed>) i.getClass());
				}
			}
			
			Item result;
			
			if ( (seeds.size() == 2 && Random.Int(4) == 0)
					|| (seeds.size() == 3 && Random.Int(2) == 0)) {
				
				result = Generator.random( Generator.Category.POTION );
				
			} else {
				
				Class<? extends Potion> itemClass = types.get(Random.element(ingredients).getClass());
				try {
					result = itemClass.newInstance();
				} catch (Exception e) {
					OvergrownPixelDungeon.reportException(e);
					result = Generator.random( Generator.Category.POTION );
				}
				
			}
			
			while (result instanceof PotionOfHealing
					&& (Dungeon.isChallenged(Challenges.NO_HEALING)
					|| Random.Int(10) < Dungeon.LimitedDrops.COOKING_HP.count)) {
				result = Generator.random(Generator.Category.POTION);
			}
			
			if (result instanceof PotionOfHealing) {
				Dungeon.LimitedDrops.COOKING_HP.count++;
			}
			
			Statistics.potionsCooked++;
			Badges.validatePotionsCooked();
			
			return result;
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			return new WndBag.Placeholder(ItemSpriteSheet.POTION_HOLDER){
				{
					name = Messages.get(SeedToPotion.class, "name");
				}
				
				@Override
				public String info() {
					return "";
				}
			};
		}
	}
}
