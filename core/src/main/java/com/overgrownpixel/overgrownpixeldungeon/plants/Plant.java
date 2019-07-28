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

package com.overgrownpixel.overgrownpixeldungeon.plants;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Challenges;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Haste;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Hunger;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.LivingPlant;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.Pushing;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.LeafParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.wands.WandOfRegrowth;
import com.overgrownpixel.overgrownpixeldungeon.levels.Level;
import com.overgrownpixel.overgrownpixeldungeon.levels.Terrain;
import com.overgrownpixel.overgrownpixeldungeon.levels.features.Door;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Plant implements Bundlable {

	public String plantName = Messages.get(this, "name");
	
	public int image;
	public int pos;
	public boolean playerplanted = false;

	public void trigger(){

		Char ch = Actor.findChar(pos);

		if (ch instanceof Hero){
			((Hero) ch).interrupt();
		}

        wither();

        if(!(this instanceof Rotberry) && !playerplanted){
            if(ch instanceof LivingPlant){
                //livingplants will trigger other living plants. Since livpla's will not step on other plants on purpose this only happens if they are pushed onto a plant.
                ch.sprite.emitter().start( Speck.factory( Speck.UP ), 0.2f, 3 );
                ch.HP = ch.HT;
                spawnLivingPlant(new LivingPlant().setPlantClass(this), ch);
            } else {
                //30% chance of triggering a living plant
                float chance = 0.3f;
                for(int i : PathFinder.NEIGHBOURS8){
                    if(Dungeon.level.map[pos+i] == Terrain.HIGH_GRASS ||
                            Dungeon.level.map[pos+i] == Terrain.WALL ||
                            Dungeon.level.map[pos+i] == Terrain.WALL_DECO){
                        //8x0.0375 = 0.3f
                        chance -= 0.0375f;
                    }
                    if(Dungeon.level.map[pos+i] == Terrain.WELL ||
                            Dungeon.level.map[pos+i] == Terrain.WATER ||
                            Dungeon.level.map[pos+i] == Terrain.ALCHEMY){
                        chance += 0.0375f;
                    }
                }
                //chances (int) in 100 or in other words 30%
                if(Random.Float() <= chance){
                    //spawn the living plant
                    spawnLivingPlant(new LivingPlant().setPlantClass(this), ch);
                } else {
                    if(ch != null){
                        if(ch.properties().contains(Char.Property.INORGANIC)){
                            spawnLivingPlant(new LivingPlant().setPlantClass(this), ch);
                        }
                        //if the character exits trigger the plant effect on the character
                        activate( ch );
                    } else {
                        //if the character doesn't exist 50% chance to activate a variation effect of the plant that doesn't need a ch
                        if(Random.Float() > 0.55f){
                            activate();
                        } else {
                            spawnLivingPlant(new LivingPlant().setPlantClass(this), null);
                        }
                    }
                }
            }
        } else {
            activate(ch);
        }
	}

    public void spawnLasher(int pos){
       /*
       //try and spawn a lasher. This is only reserved for when you have no idea what an activate() effect should be.
        if (Actor.findChar(pos) == null && ((Dungeon.level.passable[pos] || Dungeon.level.avoid[pos]) && !Dungeon.level.pit[pos])) {
            Lasher lasher = new Lasher();
            lasher.pos = pos;
            GameScene.add(lasher);
            Actor.addDelayed(new Pushing(lasher, pos, lasher.pos), 0.0f);
            lasher.move(lasher.pos);
        } else {
            //fuck it! Give the player a seed! They deserve it if they managed to pull this fucking shit off!
            Dungeon.level.drop(Generator.random(Generator.Category.SEED), pos).sprite.drop(pos);
        }
        */
    }

    public void spawnLivingPlant(LivingPlant livingPlant, Char activator) {
        //find the all tiles the living plant can be spawned at
        Collection arrayList = new ArrayList();
        for (int i : PathFinder.NEIGHBOURS8) {
            int ip = pos + i;
            //this if statement makes it impossible for livingplants to be spawned on (avoid) other plant terrain.
            if (Actor.findChar(ip) == null && ((Dungeon.level.passable[ip] || Dungeon.level.avoid[ip]) && !Dungeon.level.pit[ip])) {
                arrayList.add(Integer.valueOf(ip));
            }
        }

        //checks if there are any tiles
        if (!arrayList.isEmpty()) {
            livingPlant.state = livingPlant.HUNTING;
            livingPlant.pos = ((Integer) Random.element(arrayList)).intValue();
            GameScene.add(livingPlant);
            Actor.addDelayed(new Pushing(livingPlant, pos, livingPlant.pos), 0.0f);
            livingPlant.move(livingPlant.pos);
            if (Dungeon.level.map[livingPlant.pos] == 5) {
                Door.enter(livingPlant.pos);
            }
            if (Dungeon.level.heroFOV[pos]) {
                CellEmitter.get( pos ).burst( LeafParticle.GENERAL, 6 );
            }
            if (Dungeon.level.heroFOV[livingPlant.pos]) {
                CellEmitter.get( livingPlant.pos ).burst( LeafParticle.GENERAL, 6 );
            }
            if (Dungeon.level.map[livingPlant.pos] == Terrain.EMBERS
                    || Dungeon.level.map[livingPlant.pos] == Terrain.EMPTY_DECO
                    || Dungeon.level.map[livingPlant.pos] == Terrain.EMPTY
                    || Dungeon.level.map[livingPlant.pos] == Terrain.HIGH_GRASS){
                Dungeon.level.map[livingPlant.pos] = Terrain.GRASS;
            }
            if (Dungeon.level.plants.get(livingPlant.pos) != null){
                Dungeon.level.plants.get(livingPlant.pos).activate(livingPlant);
            }
            GameScene.updateMap(pos);
        } else {
            //if there aren't any tiles...
            if(activator instanceof LivingPlant){
                //if the activator was a fellow plant, it gets a speed boost
                Buff.prolong( activator, Haste.class, Haste.DURATION);
            } else {
                if(activator != null){
                    //if the activator is anything else just activate the effect on it
                    activate( activator );
                } else {
                    //this can only happen if there are no tiles and the plant was triggered by an item
                    activate();
                }
            }
        }
    }

    public abstract void attackProc( Char enemy, int damage );
	
	public abstract void activate( Char ch );

    public abstract void activate();

    public abstract Blob immunity();

    public void defaultProc(Char enemy, int damage){
        enemy.damage(damage, this);
        if (Dungeon.level.heroFOV[enemy.pos]) {
            CellEmitter.get( enemy.pos ).burst( LeafParticle.GENERAL, 6 );
        }
    }
	
	public void wither() {
		Dungeon.level.uproot( pos );

		if (Dungeon.level.heroFOV[pos]) {
			CellEmitter.get( pos ).burst( LeafParticle.GENERAL, 6 );
		}
		
	}

    public Plant.Seed getPlant(Plant plant){
        switch (plant.image){
            case 0:
                return new Rotberry.Seed();
            case 1:
                return new Firebloom.Seed();
            case 2:
                return new Swiftthistle.Seed();
            case 3:
                return new Sungrass.Seed();
            case 4:
                return new Icecap.Seed();
            case 5:
                return new Stormvine.Seed();
            case 6:
                return new Sorrowmoss.Seed();
            case 7:
                return new Dreamfoil.Seed();
            case 8:
                return new Earthroot.Seed();
            case 9:
                return new Starflower.Seed();
            case 10:
                return new Fadeleaf.Seed();
            case 11:
                return new Blindweed.Seed();
            case 12:
                return new BlandfruitBush.Seed();
            case 13:
                return new WandOfRegrowth.Dewcatcher.Seed();
            case 14:
                return new WandOfRegrowth.Seedpod.Seed();
            case 15:
                return new Apricobush.Seed();
            case 17:
                return new Butterlion.Seed();
            case 23:
                return new Chandaliertail.Seed();
            case 25:
                return new Blackholeflower.Seed();
            case 31:
                return new Chillisnapper.Seed();
            case 29:
                return new Crimsonpepper.Seed();
            case 16:
                return new Firefoxglove.Seed();
            case 26:
                return new Frostcorn.Seed();
            default:
                return null;
        }
    }
	
	private static final String POS	= "pos";
    private static final String PLAYERPLANTED = "playerplant";

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		pos = bundle.getInt( POS );
		playerplanted = bundle.getBoolean(PLAYERPLANTED);
	}

	@Override
	public void storeInBundle( Bundle bundle ) {
		bundle.put( POS, pos );
		bundle.put( PLAYERPLANTED, playerplanted);
	}
	
	public String desc() {
		return Messages.get(this, "desc");
	}
	
	public static class Seed extends Item {

		public static final String AC_PLANT	= "PLANT";
        public static final String AC_EAT	= "EAT";
		
		private static final float TIME_TO_PLANT = 1f;
		
		{
			stackable = true;
			defaultAction = AC_THROW;
		}

        public Emitter.Factory getPixelParticle(){
            return null;
        }

		public void onProc(Char attacker, Char defender, int damage){
            //also poison can not effect inorganic, acidic and fiery actors for obvious reasons
            if(!defender.properties().contains(Char.Property.INORGANIC)
                    && !defender.properties().contains(Char.Property.ACIDIC)
                    && !defender.properties().contains(Char.Property.FIERY)){
                if (Dungeon.level.heroFOV[defender.pos]) {
                    defender.sprite.burst(poisonEmitterClass().getColor(), damage);
                }
                procEffect(attacker, defender, damage);
            }
        }

        public void procEffect(Char attacker, Char defender, int damage){

        }
		
		protected Class<? extends Plant> plantClass;

        public Class<? extends Plant> getPlantClass() {
            return this.plantClass;
        }

        public PixelParticle poisonEmitterClass(){
            return null;
        }
		
		@Override
		public ArrayList<String> actions( Hero hero ) {
			ArrayList<String> actions = super.actions( hero );
			actions.add( AC_PLANT );
			actions.add( AC_EAT );
			return actions;
		}
		
		@Override
		protected void onThrow( int cell ) {
			if (Dungeon.level.map[cell] == Terrain.ALCHEMY
					|| Dungeon.level.pit[cell]
					|| Dungeon.level.traps.get(cell) != null
					|| Dungeon.isChallenged(Challenges.NO_HERBALISM)) {
				super.onThrow( cell );
			} else {
				Dungeon.level.plant( this, cell, true );
				if (Dungeon.hero.subClass == HeroSubClass.WARDEN) {
					for (int i : PathFinder.NEIGHBOURS8) {
						int c = Dungeon.level.map[cell + i];
						if ( c == Terrain.EMPTY || c == Terrain.EMPTY_DECO
								|| c == Terrain.EMBERS || c == Terrain.GRASS){
							Level.set(cell + i, Terrain.FURROWED_GRASS);
							GameScene.updateMap(cell + i);
							CellEmitter.get( cell + i ).burst( LeafParticle.LEVEL_SPECIFIC, 4 );
						}
					}
				}
			}
		}
		
		@Override
		public void execute( Hero hero, String action ) {

			super.execute (hero, action );

			if (action.equals( AC_PLANT )) {

                hero.spend( TIME_TO_PLANT );
                hero.busy();
                ((Seed)detach( hero.belongings.backpack )).onThrow( hero.pos );

                hero.sprite.operate( hero.pos );
            }

            if (action.equals( AC_EAT )) {

                hero.spend( TIME_TO_PLANT );
                hero.busy();
                detach(hero.belongings.backpack);

                hero.sprite.operate( hero.pos );

                eatEffect(hero);
            }
		}

		public void eatEffect(Hero hero){
            (hero.buff( Hunger.class )).satisfy( 30f );
        }
		
		public Plant couch( int pos, Level level, boolean playerplanted ) {
			try {
				if (level != null && level.heroFOV != null && level.heroFOV[pos]) {
					Sample.INSTANCE.play(Assets.SND_PLANT);
				}
				Plant plant = plantClass.newInstance();
				//plant.playerplanted = playerplanted;
				plant.pos = pos;

				return plant;
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
				return null;
			}
		}
		
		@Override
		public boolean isUpgradable() {
			return false;
		}
		
		@Override
		public boolean isIdentified() {
			return true;
		}
		
		@Override
		public int price() {
			return 10 * quantity;
		}

		@Override
		public String desc() {
			return Messages.get(plantClass, "desc");
		}

		@Override
		public String info() {
			return Messages.get( Seed.class, "info", desc() );
		}
		
		public static class PlaceHolder extends Seed {
			
			{
				image = ItemSpriteSheet.SEED_HOLDER;
			}
			
			@Override
			public boolean isSimilar(Item item) {
				return item instanceof Plant.Seed;
			}
			
			@Override
			public String info() {
				return "";
			}
		}
	}

	//Used to gain acces to static methods
	public static class PlaceholderPlant extends Plant{

        @Override
        public void attackProc(Char enemy, int damage) {}

        @Override
        public void activate(Char ch) {}

        @Override
        public void activate() {}

        @Override
        public Blob immunity() {
            return null;
        }
    }
}
