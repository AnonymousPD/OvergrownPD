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

package com.overgrownpixel.overgrownpixeldungeon.actors.mobs;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Corruption;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Roots;
import com.overgrownpixel.overgrownpixeldungeon.items.food.Blandfruit;
import com.overgrownpixel.overgrownpixeldungeon.plants.BlandfruitBush;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.sprites.CharSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.mobs.LivingPlantSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.mobs.LivingPlantSpriteDefault;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.HashSet;

public class LivingPlant extends Mob {

	{

		spriteClass = LivingPlantSpriteDefault.class;
		
		HP = HT = 8;
		defenseSkill = 2;

		EXP = 0;

        properties.add(Property.PLANT);

        becomePlant = false;
        immunities.add(Corruption.class);
	}

	public LivingPlant(){
	    super();
        HP = HT = 8 + Dungeon.depth * 2;
        defenseSkill = 2 + Dungeon.depth * 2;
    }

    public Plant plantClass;

    //mainly used in conjunction with for-loops changing this value directly will ensure results the next time the plant act()'s
    public boolean becomePlant;

    private final String PLANTCLASS = "plantclass";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(PLANTCLASS, plantClass);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        plantClass = (Plant) bundle.get(PLANTCLASS);
    }

    @Override
    public CharSprite sprite() {
        CharSprite sprite = null;
        try {
            if(plantClass.image == 0){
                sprite = new LivingPlantSprite(64);
            } else {
                sprite = new LivingPlantSprite(plantClass.image);
            }
        } catch (Exception e) {
            OvergrownPixelDungeon.reportException(e);
        }
        return sprite;
    }

    //used to set which effects depending on its source plant the living plant should have
    public LivingPlant setPlantClass(Plant plantClass){
        this.plantClass = plantClass;
        name += (" "+plantClass.plantName);
        setPlantImmunities(this.plantClass);
        return this;
    }

    public void setPlantImmunities(Plant plantClass){
        if(plantClass.immunity() != null){
            immunities.add(plantClass.immunity().getClass());
        }
    }

    //sends livingplants sleeping
    public void goToSleep(LivingPlant livingPlant){
        //using the inherited method directly as to not trigger its normal die() effects
        super.die(livingPlant);
        Dungeon.level.plant(livingPlant.plantClass.getPlant(livingPlant.plantClass), pos, false);
    }

    @Override
    public void die(Object cause) {
        if(buff(Roots.class) == null){
            if(Random.Float() <= 0.75f){
                Dungeon.level.drop(plantClass.getPlant(plantClass), pos).sprite.drop();
            }
        } else {
            Dungeon.level.drop(plantClass.getPlant(plantClass), pos).sprite.drop();
        }
        if(plantClass instanceof BlandfruitBush){
            Dungeon.level.drop(new Blandfruit(), pos);
        }
        sprite.burst(plantClass.getPlant(plantClass).poisonEmitterClass().getColor(), 10);
        super.die(cause);
    }

    @Override
    public int attackProc(Char enemy, int damage) {
        if(!enemy.flying){
            if(Random.Float() <= 0.3f){
                enemy.sprite.burst(plantClass.getPlant(plantClass).poisonEmitterClass().getColor(), damage);
                plantClass.attackProc(enemy, damage);
            }
        }
        return super.attackProc(enemy, damage);
    }

    @Override
    public void damage(int dmg, Object src) {
        super.damage(dmg, src);
        sprite.burst(plantClass.getPlant(plantClass).poisonEmitterClass().getColor(), dmg);
    }

    @Override
	public int damageRoll() {
		return Random.NormalIntRange( Dungeon.depth, Dungeon.depth+3 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return Dungeon.depth;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, Dungeon.depth);
	}

    @Override
    public String description() {
        return super.description() +"\n"+plantClass.desc();
    }

    @Override
    protected Char chooseEnemy() {

        //try to find a new enemy in these circumstances
        if (enemy == null || !enemy.isAlive() || state == WANDERING
                || Dungeon.level.distance(enemy.pos, pos) > 2){

            //find all mobs near the living plant
            HashSet<Char> enemies = new HashSet<>();
            for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
                if (!(mob == this)
                        && Dungeon.level.distance(mob.pos, pos) <= 5
                        && mob.alignment != Alignment.NEUTRAL
                        && !(alignment == Alignment.ALLY && mob.alignment == Alignment.ALLY)) {
                    enemies.add(mob);
                }

            }

            if(Dungeon.level.distance(Dungeon.hero.pos, pos) <= 5){
                enemies.add(Dungeon.hero);
            }

            //if the hero cannot be found and their are no mobs to have as enemies the plant will go back to sleep
            if(enemies.isEmpty()){
                goToSleep(this);
            }

            if(!enemies.isEmpty()){
                Char mob = Random.element(enemies);
                if(mob != null){
                    if(Dungeon.level.distance(mob.pos, pos) >= Dungeon.level.distance(Dungeon.hero.pos, pos)){
                        if(alignment != Alignment.ALLY){
                            return Dungeon.hero;
                        } else {
                            return mob;
                        }
                    } else {
                        return mob;
                    }
                }
            }
            return Dungeon.hero;

        } else {
            return enemy;
        }
    }

    @Override
    protected boolean act() {
        //if plants grow roots they become plants again
        if(buff(Roots.class) != null){
            goToSleep(this);
            return true;
        }
        if(becomePlant){
            goToSleep(this);
            return true;
        }
        return super.act();
    }
}
