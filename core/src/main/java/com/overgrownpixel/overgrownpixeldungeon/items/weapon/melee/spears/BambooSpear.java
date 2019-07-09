/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2016 Evan Debenham
 *
 * Lovecraft Pixel Dungeon
 * Copyright (C) 2016-2017 Leon Horn
 *
 * Plugin Pixel Dungeon
 * Copyright (C) 2017 Leon Horn
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This Program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without eben the implied warranty of
 * GNU General Public License for more details.
 *
 * You should have have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses>
 */

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.spears;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class BambooSpear extends MeleeWeapon {

    private static final String AC_BREAK = "BREAK";

    private static final float dly = 10;

    private final BambooSpear spear = this;

    private float growLevel = 0;

    private Growth growth;
    {
        image = ItemSpriteSheet.BAMBOO_SPEAR;

        tier = 4;

    }

    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions =  super.actions(hero);
        if (growLevel>=dly)
            actions.add(AC_BREAK);
        return actions;
    }

    @Override
    public void execute(Hero hero, String action) {
        super.execute(hero, action);
        if (action.equals(AC_BREAK)){
            if (growLevel>=dly){
                growLevel-=dly;
                GLog.i(Messages.get(this,"broken"));
                curUser.spendAndNext(2);
            }
        }
    }

    public BambooSpear(){
        super();
        growth = new Growth();
    }

    private boolean act(){
        if (this.isEquipped(Dungeon.hero)){
            grow(Random.Float(0,0.1f));
        }
        growth.spend(Actor.TICK);
        return true;
    }

    public BambooSpear grow(float num){
        final float limit = 70;
        growLevel+= num;
        growLevel=Math.min(growLevel,limit);
//		DLY=(float)(1+Math.pow(Math.sqrt(1.5),reachFactor(Dungeon.hero)-1));
        DLY=Math.max((float)(Math.sqrt(reachFactor(Dungeon.hero)*1.5f)),1);
        return this;
    }

    @Override
    public int reachFactor(Char owner) {
        return super.reachFactor(owner)+(int)(growLevel/dly);
    }

    private static final String GROWTH = "growth";
    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(GROWTH,growLevel);
    }

    @Override
    public boolean doEquip(Hero hero) {
        if( super.doEquip(hero)) {
            growth=new Growth();
            growth.attachTo(hero);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean doUnequip(Hero hero, boolean collect, boolean single) {
        if(super.doUnequip(hero, collect, single)){
            growth.detach();
            return true;
        }
        return false;
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        growLevel=bundle.getFloat(GROWTH);
    }

    @Override
    public Item random() {
        super.random();
        int length = Random.chances(new float[]{3,0.75f,0.429f,0.3f,0.231f,0.188f});
        grow(length*dly);
        tier=Random.NormalIntRange(2,5);
        return grow(length*dly);
    }

    @Override
    public void activate(Char ch) {
        super.activate(ch);
        growth=new Growth();
        growth.attachTo(ch);
    }

    private class Growth extends Buff {
        @Override
        public boolean act() {
            if (growth!=this){
                detach();
                return true;
            }
            //System.out.println(growLevel);
            return spear.act();
        }

        @Override
        public void spend(float time) {
            super.spend(time);
        }
    }

}
