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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.darts;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.PinCushion;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.items.Generator;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfRegrowth;
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
import com.overgrownpixel.overgrownpixeldungeon.windows.WndOptions;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class TippedDart extends Dart {
	
	{
		tier = 2;
		
		//so that slightly more than 1.5x durability is needed for 2 uses
		baseUses = 0.65f;
	}
	
	private static final String AC_CLEAN = "CLEAN";
	
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		actions.remove( AC_TIP );
		actions.add( AC_CLEAN );
		return actions;
	}
	
	@Override
	public void execute(final Hero hero, String action) {
		if (action.equals( AC_CLEAN )){
			
			GameScene.show(new WndOptions(Messages.get(this, "clean_title"),
					Messages.get(this, "clean_desc"),
					Messages.get(this, "clean_all"),
					Messages.get(this, "clean_one"),
					Messages.get(this, "cancel")){
				@Override
				protected void onSelect(int index) {
					if (index == 0){
						detachAll(hero.belongings.backpack);
						new Dart().quantity(quantity).collect();
						
						hero.spend( 1f );
						hero.busy();
						hero.sprite.operate(hero.pos);
					} else if (index == 1){
						detach(hero.belongings.backpack);
						if (!new Dart().collect()) Dungeon.level.drop(new Dart(), hero.pos).sprite.drop();
						
						hero.spend( 1f );
						hero.busy();
						hero.sprite.operate(hero.pos);
					}
				}
			});
			
		}
		super.execute(hero, action);
	}
	
	//exact same damage as regular darts, despite being higher tier.
	
	@Override
	protected void rangedHit(Char enemy, int cell) {
		super.rangedHit( enemy, cell);
		
		//need to spawn a dart
		if (durability <= 0){
			//attempt to stick the dart to the enemy, just drop it if we can't.
			Dart d = new Dart();
			if (enemy.isAlive() && sticky) {
				PinCushion p = Buff.affect(enemy, PinCushion.class);
				if (p.target == enemy){
					p.stick(d);
					return;
				}
			}
			Dungeon.level.drop( d, enemy.pos ).sprite.drop();
		}
	}
	
	@Override
	protected float durabilityPerUse() {
		float use = super.durabilityPerUse();
		
		if (Dungeon.hero.subClass == HeroSubClass.WARDEN){
			use /= 2f;
		}
		
		return use;
	}
	
	@Override
	public int price() {
		//value of regular dart plus half of the seed
		return 8 * quantity;
	}
	
	private static HashMap<Class<?extends Plant.Seed>, Class<?extends TippedDart>> types = new HashMap<>();
	static {
		types.put(Blindweed.Seed.class,                 BlindingDart.class); //1
		types.put(Dreamfoil.Seed.class,                 SleepDart.class); //2
		types.put(Earthroot.Seed.class,                 ParalyticDart.class); //3
		types.put(Fadeleaf.Seed.class,                  DisplacingDart.class); //4
		types.put(Firebloom.Seed.class,                 IncendiaryDart.class); //5
		types.put(Icecap.Seed.class,                    ChillingDart.class); //6
		types.put(Rotberry.Seed.class,                  RotDart.class); //7
		types.put(Sorrowmoss.Seed.class,                PoisonDart.class); //8
		types.put(Starflower.Seed.class,                HolyDart.class); //9
		types.put(Stormvine.Seed.class,                 ShockingDart.class); //10
		types.put(Sungrass.Seed.class,                  HealingDart.class); //11
		types.put(Swiftthistle.Seed.class,              AdrenalineDart.class); //12
        types.put(Apricobush.Seed.class,                HealthDart.class); //13
        types.put(Blackholeflower.Seed.class,           TeleportingDart.class); //14
        types.put(WandOfRegrowth.Dewcatcher.Seed.class, DewDart.class); //15
        types.put(WandOfRegrowth.Seedpod.Seed.class,    SeedChaosDart.class); //16
        types.put(Butterlion.Seed.class,                EarthquakeDart.class); //17
        types.put(Chandaliertail.Seed.class,            TrackingDart.class); //18
        types.put(Chillisnapper.Seed.class,             HeatDart.class); //19
        types.put(Crimsonpepper.Seed.class,             SpicyDart.class); //20
        types.put(Firefoxglove.Seed.class,              FirefoxDart.class); //21
        types.put(Frostcorn.Seed.class,                 FreezingDart.class); //22
        types.put(Grasslilly.Seed.class,                ChaosDart.class); //23
        types.put(Kiwivetch.Seed.class,                 RootingDart.class); //24
        types.put(Musclemoss.Seed.class,                PushingDart.class); //25
        types.put(Nightshadeonion.Seed.class,           SmokingDart.class); //26
        types.put(Parasiteshrub.Seed.class,             ParasiticDart.class); //27
        types.put(Peanutpetal.Seed.class,               PeanutMarkDart.class); //28
        types.put(Rose.Seed.class,                      RoseDart.class); //29
        types.put(Snowhedge.Seed.class,                 SnowstormDart.class); //30
        types.put(Steamweed.Seed.class,                 StormDart.class); //31
        types.put(Sunbloom.Seed.class,                  SunDart.class); //32
        types.put(Tomatobush.Seed.class,                TomatoDart.class); //33
        types.put(Venusflytrap.Seed.class,              ConfusingDart.class); //34
        types.put(Willowcane.Seed.class,                SlownessDart.class); //35
        types.put(Witherfennel.Seed.class,              WitherDart.class); //36
        types.put(Suncarnivore.Seed.class,              CorruptionDart.class); //37
        types.put(Ballcrop.Seed.class,                  BallDart.class); //38
        types.put(Bananabean.Seed.class,                BananaDart.class); //39
        types.put(Blueeyedsusan.Seed.class,             SusanDart.class); //40
        types.put(Clitbalm.Seed.class,                  ClitDart.class); //41
        types.put(Clockcypress.Seed.class,              ClockDart.class); //42
        types.put(Cocostuft.Seed.class,                 CocoDart.class); //43
        types.put(Combflower.Seed.class,                HoneyDart.class); //44
        types.put(Cornwheat.Seed.class,                 CornDart.class); //45
        types.put(Crimsoncrown.Seed.class,              CrownDart.class); //46
        types.put(Dirtdaisy.Seed.class,                 DirtDart.class); //47
        types.put(Eggbloom.Seed.class,                  EggDart.class); //48
        types.put(Eyeeuonymus.Seed.class,               EyeDart.class); //49
        types.put(Feelerfern.Seed.class,                FeelerDart.class); //50
        types.put(Flowertree.Seed.class,                TreeDart.class); //51
        types.put(Gobgrape.Seed.class,                  GrapeDart.class); //52
        types.put(Goograss.Seed.class,                  GooDart.class); //53
        types.put(Grassvine.Seed.class,                 GrassDart.class); //54
        types.put(Hypnohemp.Seed.class,                 HypnoDart.class); //55
        types.put(Lavenderlantern.Seed.class,           LanternDart.class); //56
        types.put(Poppoplar.Seed.class,                 PopDart.class); //57
        types.put(Lightninglily.Seed.class,             LightningDart.class); //58
        types.put(Sourpitcher.Seed.class,               SourDart.class); //59
        types.put(Shadowbloom.Seed.class,               ShadowDart.class); //60
        types.put(Larvaleaf.Seed.class,                 BeetleDart.class); //61
        types.put(Tankcabbage.Seed.class,               TankDart.class); //62
        types.put(Waterweed.Seed.class,                 WaterDart.class); //63
	}
	
	public static TippedDart getTipped( Plant.Seed s, int quantity ){
		try {
			return (TippedDart) types.get(s.getClass()).newInstance().quantity(quantity);
		} catch (Exception e){
			OvergrownPixelDungeon.reportException(e);
			return null;
		}
	}
	
	public static TippedDart randomTipped( int quantity ){
		Plant.Seed s;
		do{
			s = (Plant.Seed) Generator.random(Generator.Category.ALLSEEDS);
		} while (!types.containsKey(s.getClass()));
		
		return getTipped(s, quantity );
		
	}
	
}
