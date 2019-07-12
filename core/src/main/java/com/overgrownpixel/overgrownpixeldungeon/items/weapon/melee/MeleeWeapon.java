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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.Weapon;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.axes.HookedWaraxe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.blades.AssassinsBlade;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.bows.GoldenBow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.bows.SmallBow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.chains.KnifeChain;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.chains.Kusarigama;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.chains.ManrikiKusari;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.chains.Nunchaku;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.clubs.BarbedStaff;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.clubs.SpikedClub;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.clubs.SpikedStoneClub;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.Crossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.GoldenCrossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.IronCrossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.SmallCrossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.WoodenCrossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.fans.GunsenFan;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.gloves.ClawGlove;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.gloves.TekkoKagi;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.gloves.Yawara;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.grates.CheeseGrater;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.harpoons.Harpoon;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.knifes.ClawKnife;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.knifes.Dagger;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.knifes.Dirk;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.knifes.HornKnife;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.scythes.SpikedScythe;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.spears.Sasumata;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.whips.ChainWhip;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.whips.NailWhip;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndBag;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MeleeWeapon extends Weapon {

    public static final String AC_POSION		= "POISON";

    protected static final float TIME_TO_POISON	= 1f;
	
	public int tier;

    public Plant.Seed seed;

    public int poison_turns = 0;

    {
        defaultAction = AC_POSION;
    }

	@Override
	public int min(int lvl) {
		return  tier +  //base
				lvl;    //level scaling
	}

	@Override
	public int max(int lvl) {
		return  5*(tier+1) +    //base
				lvl*(tier+1);   //level scaling
	}

	public int STRReq(int lvl){
		lvl = Math.max(0, lvl);
		//strength req decreases at +1,+3,+6,+10,etc.
		return (8 + tier * 2) - (int)(Math.sqrt(8 * lvl + 1) - 1)/2;
	}
	
	@Override
	public int damageRoll(Char owner) {
		int damage = augment.damageFactor(super.damageRoll( owner ));

		if (owner instanceof Hero) {
			int exStr = ((Hero)owner).STR() - STRReq();
			if (exStr > 0) {
				damage += Random.IntRange( 0, exStr );
			}
		}
		
		return damage;
	}

    @Override
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        if(seed == null && isIdentified()){
            actions.add( AC_POSION );
        }
        return actions;
    }

    @Override
    public void execute( Hero hero, String action ) {

        super.execute( hero, action );

        if (action.equals( AC_POSION )) {
            GameScene.selectItem( itemSelector, mode, inventoryTitle );
        }
    }

    protected WndBag.Mode mode = WndBag.Mode.SEED;

    protected String inventoryTitle = Messages.get(this, "inv_title");

    protected static WndBag.Listener itemSelector = new WndBag.Listener() {
        @Override
        public void onSelect( Item item ) {

            if (item != null) {
                if(curItem instanceof Weapon && item instanceof Plant.Seed){
                    try {
                        ((MeleeWeapon) curItem).seed = (Plant.Seed) item.getClass().newInstance().quantity(1);
                        ((MeleeWeapon) curItem).setPoisonTurns(5);
                        item.detach(curUser.belongings.backpack);
                        curUser.spend( TIME_TO_POISON );
                        curUser.busy();
                        (curUser.sprite).operate(curUser.pos);
                        GLog.i(Messages.get(MeleeWeapon.class, "poisoned", curItem.name(), item.name()));
                    } catch (Exception e) {
                        OvergrownPixelDungeon.reportException(e);
                    }
                }
            }
        }
    };

    public void setPoisonTurns(int turns){
        this.poison_turns = turns+this.level();
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        if(this.seed != null && this.poison_turns > 0){
            this.seed.onProc(attacker, defender, damage);
            this.poison_turns--;
            if(this.poison_turns <= 0){
                this.seed = null;
                GLog.i(Messages.get(this, "wears_off", this.name()));
            }
        }
        return super.proc(attacker, defender, damage);
    }

    @Override
	public String info() {

		String info = desc();

		if (levelKnown) {
			info += "\n\n" + Messages.get(MeleeWeapon.class, "stats_known", tier, augment.damageFactor(min()), augment.damageFactor(max()), STRReq());
			if (STRReq() > Dungeon.hero.STR()) {
				info += " " + Messages.get(Weapon.class, "too_heavy");
			} else if (Dungeon.hero.STR() > STRReq()){
				info += " " + Messages.get(Weapon.class, "excess_str", Dungeon.hero.STR() - STRReq());
			}
		} else {
			info += "\n\n" + Messages.get(MeleeWeapon.class, "stats_unknown", tier, min(0), max(0), STRReq(0));
			if (STRReq(0) > Dungeon.hero.STR()) {
				info += " " + Messages.get(MeleeWeapon.class, "probably_too_heavy");
			}
		}

		if(ACC != 1f && levelKnown) info += "\n" + getAccPercent(ACC);
        if(DLY != 1f && levelKnown) info += "\n" + getDlyPercent(DLY);
        if(RCH != 1 && levelKnown) info += "\n" + Messages.get(this, "reach_info", RCH);
        if(defenseFactor(curUser) != 0) info += "\n" + Messages.get(this, "defense_info", defenseFactor(curUser));

        if(levelKnown){
            if(this instanceof AssassinsBlade ||
                    this instanceof Dagger ||
                    this instanceof Dirk ||
                    this instanceof Yawara){
                info += "\n" + Messages.get(this, "suprise_attack_info");
            }
            if(this instanceof CheeseGrater ||
                    this instanceof BarbedStaff ||
                    this instanceof ClawGlove ||
                    this instanceof ClawKnife ||
                    this instanceof GunsenFan ||
                    this instanceof HookedWaraxe ||
                    this instanceof KnifeChain ||
                    this instanceof Kusarigama ||
                    this instanceof NailWhip ||
                    this instanceof SpikedClub ||
                    this instanceof SpikedScythe ||
                    this instanceof SpikedStoneClub ||
                    this instanceof TekkoKagi){
                info += "\n" + Messages.get(this, "bleeding_proc_info");
            }
            if(this instanceof ChainWhip ||
                    this instanceof HornKnife ||
                    this instanceof Kusarigama ||
                    this instanceof ManrikiKusari ||
                    this instanceof Nunchaku ||
                    this instanceof Sasumata){
                info += "\n" + Messages.get(this, "cripple_proc_info");
            }
            if(this instanceof Harpoon){
                info += "\n" + Messages.get(this, "pulling_proc_info");
            }
            if(this instanceof GunsenFan){
                info += "\n" + Messages.get(this, "pushing_proc_info");
            }
            if(this instanceof SmallBow ||
                    this instanceof GoldenBow ||
                    this instanceof Crossbow ||
                    this instanceof GoldenCrossbow ||
                    this instanceof IronCrossbow ||
                    this instanceof SmallCrossbow ||
                    this instanceof WoodenCrossbow){
                info += "\n" + Messages.get(this, "dart_throwing_info");
            }
        }


		switch (augment) {
			case SPEED:
				info += "\n\n" + Messages.get(Weapon.class, "faster");
				break;
			case DAMAGE:
				info += "\n\n" + Messages.get(Weapon.class, "stronger");
				break;
			case NONE:
		}

		if (enchantment != null && (cursedKnown || !enchantment.curse())){
			info += "\n\n" + Messages.get(Weapon.class, "enchanted", enchantment.name());
			info += " " + Messages.get(enchantment, "desc");
		}

		if (cursed && isEquipped( Dungeon.hero )) {
			info += "\n\n" + Messages.get(Weapon.class, "cursed_worn");
		} else if (cursedKnown && cursed) {
			info += "\n\n" + Messages.get(Weapon.class, "cursed");
		} else if (!isIdentified() && cursedKnown){
			info += "\n\n" + Messages.get(Weapon.class, "not_cursed");
		}

		if(levelKnown && this.seed != null) info += "\n\n" + Messages.get(this, "poison_desc", this.seed, this.poison_turns);
		
		return info;
	}

	public String getAccPercent(float f){
	    NumberFormat format = NumberFormat.getPercentInstance(Locale.US);
	    if(f > 1f){
	        float newf = f-1f;
	        String percentage = format.format(newf);
	       return Messages.get(MeleeWeapon.class, "more_acc_info", percentage);
        } else {
	        float newf = 1f-f;
            String percentage = format.format(newf);
            return Messages.get(MeleeWeapon.class, "less_acc_info", percentage);
        }
    }

    public String getDlyPercent(float f){
        NumberFormat format = NumberFormat.getPercentInstance(Locale.US);
        if(f > 1f){
            float newf = f-1f;
            String percentage = format.format(newf);
            return  Messages.get(MeleeWeapon.class, "slower_dly_info", percentage);
        } else {
            float newf = 1f-f;
            String percentage = format.format(newf);
            return Messages.get(MeleeWeapon.class, "faster_dly_info", percentage);
        }
    }

    @Override
    public Emitter emitter() {
        if (this.seed == null) {
            return null;
        }
        Emitter emitter = new Emitter();
        emitter.pos(12.5f, 3.0f);
        emitter.fillTarget = false;
        emitter.pour(seed.getPixelParticle(), 1.0f);
        return emitter;
    }

    private static final String SEED		    = "seed";
    private static final String POISON_TURNS	= "poisonturns";

    @Override
    public void storeInBundle( Bundle bundle ) {
        super.storeInBundle( bundle );
        bundle.put( SEED, seed );
        bundle.put( POISON_TURNS, poison_turns );
    }

    @Override
    public void restoreFromBundle( Bundle bundle ) {
        super.restoreFromBundle( bundle );
        seed = (Plant.Seed) bundle.get( SEED );
        poison_turns = bundle.getInt(POISON_TURNS);
    }
	
	@Override
	public int price() {
		int price = 20 * tier;
		if (hasGoodEnchant()) {
			price *= 1.5;
		}
		if (cursedKnown && (cursed || hasCurseEnchant())) {
			price /= 2;
		}
		if (levelKnown && level() > 0) {
			price *= (level() + 1);
		}
		if (price < 1) {
			price = 1;
		}
		return price;
	}

}
