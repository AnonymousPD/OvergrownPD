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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon;

import android.graphics.Color;

import com.overgrownpixel.overgrownpixeldungeon.Badges;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.MagicImmune;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.KindOfWeapon;
import com.overgrownpixel.overgrownpixeldungeon.items.rings.RingOfFuror;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Annoying;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Displacing;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Exhausting;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Fragile;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Friendly;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Polarized;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Sacrificial;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.curses.Wayward;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Absorbing;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Blazing;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Blocking;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Blooming;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Chilling;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Corrupting;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Dazzling;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Disintegrating;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Eating;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Elastic;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Eldritch;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Explosion;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Grim;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Hitting;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Kinetic;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Lucky;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Precise;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Projecting;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Shocking;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Stunning;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Swift;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Teleporting;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Unstable;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Vampiric;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Venomous;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Vorpal;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Whirlwind;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSprite;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.ColorMath;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;

abstract public class Weapon extends KindOfWeapon {

    public static final String AC_DISINTEGRATE  = "DISINTEGRATE";
    public static final String AC_TELEPORT      = "TELEPORT";

	public float    ACC = 1f;	// Accuracy modifier
	public float	DLY	= 1f;	// Speed modifier
	public int      RCH = 1;    // Reach modifier (only applies to melee hits)

	public enum Augment {
		SPEED   (0.7f, 0.6667f),
		DAMAGE  (1.5f, 1.6667f),
		NONE	(1.0f, 1.0000f);

		private float damageFactor;
		private float delayFactor;

		Augment(float dmg, float dly){
			damageFactor = dmg;
			delayFactor = dly;
		}

		public int damageFactor(int dmg){
			return Math.round(dmg * damageFactor);
		}

		public float delayFactor(float dly){
			return dly * delayFactor;
		}
	}
	
	public Augment augment = Augment.NONE;

    public boolean hasTeleport = false;
    public boolean hasDisintegrate = false;
	
	private static final int USES_TO_ID = 20;
	private int usesLeftToID = USES_TO_ID;
	private float availableUsesToID = USES_TO_ID/2f;
	
	public Enchantment enchantment;
	public boolean curseInfusionBonus = false;

    @Override
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );

        if(this.hasEnchant(Disintegrating.class, hero) && this.isEquipped(hero) && isIdentified() && hasDisintegrate){
            actions.add( AC_DISINTEGRATE );
        }
        if(this.hasEnchant(Teleporting.class, hero) && this.isEquipped(hero) && isIdentified() && hasTeleport){
            actions.add( AC_TELEPORT );
        }
        return actions;
    }

    @Override
    public void execute( Hero hero, String action ) {

        super.execute( hero, action );

        if (action.equals( AC_DISINTEGRATE )) {
            new Disintegrating().disintegrate(hero, this);
        }

        if (action.equals( AC_TELEPORT )) {
            new Teleporting().teleport(hero, this);
        }
    }
	
	@Override
	public int proc( Char attacker, Char defender, int damage ) {
		
		if (enchantment != null && attacker.buff(MagicImmune.class) == null) {
			damage = enchantment.proc( this, attacker, defender, damage );
		}
		
		if (!levelKnown && attacker == Dungeon.hero && availableUsesToID >= 1) {
			availableUsesToID--;
			usesLeftToID--;
			if (usesLeftToID <= 0) {
				identify();
				GLog.p( Messages.get(Weapon.class, "identify") );
				Badges.validateItemLevelAquired( this );
			}
		}

		return damage;
	}
	
	public void onHeroGainExp( float levelPercent, Hero hero ){
		if (!levelKnown && isEquipped(hero) && availableUsesToID <= USES_TO_ID/2f) {
			//gains enough uses to ID over 0.5 levels
			availableUsesToID = Math.min(USES_TO_ID/2f, availableUsesToID + levelPercent * USES_TO_ID);
		}
	}
	
	private static final String USES_LEFT_TO_ID = "uses_left_to_id";
	private static final String AVAILABLE_USES  = "available_uses";
	private static final String ENCHANTMENT	    = "enchantment";
	private static final String CURSE_INFUSION_BONUS = "curse_infusion_bonus";
	private static final String AUGMENT	        = "augment";
    private static final String HAS_TELEPORT	= "hasteleport";
    private static final String HAS_DISINTEGRATE= "hasdisintegrate";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( USES_LEFT_TO_ID, usesLeftToID );
		bundle.put( AVAILABLE_USES, availableUsesToID );
		bundle.put( ENCHANTMENT, enchantment );
		bundle.put( CURSE_INFUSION_BONUS, curseInfusionBonus );
		bundle.put( AUGMENT, augment );
        bundle.put( HAS_TELEPORT, hasTeleport );
        bundle.put( HAS_DISINTEGRATE, hasDisintegrate );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		usesLeftToID = bundle.getInt( USES_LEFT_TO_ID );
		availableUsesToID = bundle.getInt( AVAILABLE_USES );
		enchantment = (Enchantment)bundle.get( ENCHANTMENT );
		curseInfusionBonus = bundle.getBoolean( CURSE_INFUSION_BONUS );
        hasTeleport = bundle.getBoolean( HAS_TELEPORT );
        hasDisintegrate = bundle.getBoolean( HAS_DISINTEGRATE );
		augment = bundle.getEnum(AUGMENT, Augment.class);
	}
	
	@Override
	public void reset() {
		super.reset();
		usesLeftToID = USES_TO_ID;
		availableUsesToID = USES_TO_ID/2f;
	}
	
	@Override
	public float accuracyFactor( Char owner ) {
		
		int encumbrance = 0;
		
		if( owner instanceof Hero ){
			encumbrance = STRReq() - ((Hero)owner).STR();
		}

		if (hasEnchant(Wayward.class, owner))
			encumbrance = Math.max(2, encumbrance+2);

		float ACC = this.ACC;

		return encumbrance > 0 ? (float)(ACC / Math.pow( 1.5, encumbrance )) : ACC;
	}
	
	@Override
	public float speedFactor( Char owner ) {

		int encumbrance = 0;
		if (owner instanceof Hero) {
			encumbrance = STRReq() - ((Hero)owner).STR();
		}

		float DLY = augment.delayFactor(this.DLY);

		DLY *= RingOfFuror.attackDelayMultiplier(owner);

		return (encumbrance > 0 ? (float)(DLY * Math.pow( 1.2, encumbrance )) : DLY);
	}

	@Override
	public int reachFactor(Char owner) {
		return hasEnchant(Projecting.class, owner) ? RCH+1 : RCH;
	}

	public int STRReq(){
		return STRReq(level());
	}

	public abstract int STRReq(int lvl);
	
	@Override
	public int level() {
		return super.level() + (curseInfusionBonus ? 1 : 0);
	}
	
	@Override
	public Item upgrade() {
		return upgrade(false);
	}
	
	public Item upgrade(boolean enchant ) {

		if (enchant){
			if (enchantment == null || hasCurseEnchant()){
				enchant(Enchantment.random());
			}
		} else {
			if (hasCurseEnchant()){
				if (Random.Int(3) == 0) enchant(null);
			} else if (level() >= 4 && Random.Float(10) < Math.pow(2, level()-4)){
				enchant(null);
			}
		}
		
		cursed = false;
		
		return super.upgrade();
	}
	
	@Override
	public String name() {
		return enchantment != null && (cursedKnown || !enchantment.curse()) ? enchantment.name( super.name() ) : super.name();
	}
	
	@Override
	public Item random() {
		//+0: 75% (3/4)
		//+1: 20% (4/20)
		//+2: 5%  (1/20)
		int n = 0;
		if (Random.Int(4) == 0) {
			n++;
			if (Random.Int(5) == 0) {
				n++;
			}
		}
		level(n);
		
		//30% chance to be cursed
		//10% chance to be enchanted
		float effectRoll = Random.Float();
		if (effectRoll < 0.3f) {
			enchant(Enchantment.randomCurse());
			cursed = true;
		} else if (effectRoll >= 0.9f){
			enchant();
		}

		return this;
	}
	
	public Weapon enchant( Enchantment ench ) {
		if (ench == null || !ench.curse()) curseInfusionBonus = false;
		enchantment = ench;
		updateQuickslot();
		return this;
	}

	public Weapon enchant() {

		Class<? extends Enchantment> oldEnchantment = enchantment != null ? enchantment.getClass() : null;
		Enchantment ench = Enchantment.random( oldEnchantment );

		return enchant( ench );
	}

	public boolean hasEnchant(Class<?extends Enchantment> type, Char owner) {
		return enchantment != null && enchantment.getClass() == type && owner.buff(MagicImmune.class) == null;
	}
	
	//these are not used to process specific enchant effects, so magic immune doesn't affect them
	public boolean hasGoodEnchant(){
		return enchantment != null && !enchantment.curse();
	}

	public boolean hasCurseEnchant(){
		return enchantment != null && enchantment.curse();
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return enchantment != null && (cursedKnown || !enchantment.curse()) ? enchantment.glowing() : null;
	}

	public static abstract class Enchantment implements Bundlable {
		
		private static final Class<?>[] common = new Class<?>[]{
				Blazing.class, Chilling.class, Kinetic.class,
                Shocking.class, Absorbing.class, Venomous.class};
		
		private static final Class<?>[] uncommon = new Class<?>[]{
				Blocking.class, Blooming.class, Elastic.class,
				Lucky.class, Projecting.class, Unstable.class,
                Swift.class, Explosion.class, Hitting.class,
                Eldritch.class, Dazzling.class, Stunning.class,
                Eating.class};
		
		private static final Class<?>[] rare = new Class<?>[]{
				Corrupting.class, Grim.class, Vampiric.class,
                Whirlwind.class, Vorpal.class, Precise.class,
                Disintegrating.class, Teleporting.class};
		
		private static final float[] typeChances = new float[]{
				50, //12.5% each
				40, //6.67% each
				10  //3.33% each
		};
		
		private static final Class<?>[] curses = new Class<?>[]{
				Annoying.class, Displacing.class, Exhausting.class, Fragile.class,
				Sacrificial.class, Wayward.class, Polarized.class, Friendly.class
		};
		
			
		public abstract int proc( Weapon weapon, Char attacker, Char defender, int damage );

		public String name() {
			if (!curse())
				return name( Messages.get(this, "enchant"));
			else
				return name( Messages.get(Item.class, "curse"));
		}

		public String name( String weaponName ) {
			return Messages.get(this, "name", weaponName);
		}

		public String desc() {
			return Messages.get(this, "desc");
		}

		public boolean curse() {
			return false;
		}

		@Override
		public void restoreFromBundle( Bundle bundle ) {
		}

		@Override
		public void storeInBundle( Bundle bundle ) {
		}
		
		public abstract ItemSprite.Glowing glowing();
		
		@SuppressWarnings("unchecked")
		public static Enchantment random( Class<? extends Enchantment> ... toIgnore ) {
			switch(Random.chances(typeChances)){
				case 0: default:
					return randomCommon( toIgnore );
				case 1:
					return randomUncommon( toIgnore );
				case 2:
					return randomRare( toIgnore );
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Enchantment randomCommon( Class<? extends Enchantment> ... toIgnore ) {
			try {
				ArrayList<Class<?>> enchants = new ArrayList<>(Arrays.asList(common));
				enchants.removeAll(Arrays.asList(toIgnore));
				if (enchants.isEmpty()) {
					return random();
				} else {
					return (Enchantment) Random.element(enchants).newInstance();
				}
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
				return null;
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Enchantment randomUncommon( Class<? extends Enchantment> ... toIgnore ) {
			try {
				ArrayList<Class<?>> enchants = new ArrayList<>(Arrays.asList(uncommon));
				enchants.removeAll(Arrays.asList(toIgnore));
				if (enchants.isEmpty()) {
					return random();
				} else {
					return (Enchantment) Random.element(enchants).newInstance();
				}
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
				return null;
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Enchantment randomRare( Class<? extends Enchantment> ... toIgnore ) {
			try {
				ArrayList<Class<?>> enchants = new ArrayList<>(Arrays.asList(rare));
				enchants.removeAll(Arrays.asList(toIgnore));
				if (enchants.isEmpty()) {
					return random();
				} else {
					return (Enchantment) Random.element(enchants).newInstance();
				}
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
				return null;
			}
		}

		@SuppressWarnings("unchecked")
		public static Enchantment randomCurse( Class<? extends Enchantment> ... toIgnore ){
			try {
				ArrayList<Class<?>> enchants = new ArrayList<>(Arrays.asList(curses));
				enchants.removeAll(Arrays.asList(toIgnore));
				if (enchants.isEmpty()) {
					return random();
				} else {
					return (Enchantment) Random.element(enchants).newInstance();
				}
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
				return null;
			}
		}

        public void affectTarget(Ballistica shot, Hero curUser) {}
		
	}
}
