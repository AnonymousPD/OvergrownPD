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
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.MagicImmune;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.Item;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.bows.GoldenBow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.bows.SmallBow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.Crossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.GoldenCrossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.IronCrossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.SmallCrossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.crossbows.WoodenCrossbow;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.plants.Plant;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndBag;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndOptions;

import java.util.ArrayList;

public class Dart extends MissileWeapon {

	{
		image = ItemSpriteSheet.DART;
		
		tier = 1;
		
		//infinite, even with penalties
		baseUses = 1000;
	}
	
	protected static final String AC_TIP = "TIP";
	
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_TIP );
		return actions;
	}
	
	@Override
	public void execute(Hero hero, String action) {
		if (action.equals(AC_TIP)){
			GameScene.selectItem(itemSelector, WndBag.Mode.SEED, "select a seed");
		}
		
		super.execute(hero, action);
	}
	
	@Override
	public int min(int lvl) {
		if (bowType instanceof Crossbow){
			return  4 +                 //4 base
                    bowType.level() + lvl;  //+1 per level or bow level
		} else if(bowType instanceof GoldenCrossbow){
            return  3 +                 //3 base
                    bowType.level() + lvl;  //+1 per level or bow level
        } else if(bowType instanceof IronCrossbow){
            return  5 +                 //3 base
                    bowType.level() + lvl;  //+1 per level or bow level
        } else if(bowType instanceof SmallCrossbow){
            return  5 +                 //3 base
                    bowType.level() + lvl;  //+1 per level or bow level
        } else if(bowType instanceof WoodenCrossbow){
            return  5 +                 //3 base
                    bowType.level() + lvl;  //+1 per level or bow level
        } else if(bowType instanceof GoldenBow){
            return  3 +                 //3 base
                    bowType.level() + lvl;  //+1 per level or bow level
        } else if(bowType instanceof SmallBow){
            return  2 +                 //3 base
                    bowType.level() + lvl;  //+1 per level or bow level
        } else {
			return  1 +     //1 base, down from 2
					lvl;    //scaling unchanged
		}
	}

	@Override
	public int max(int lvl) {
		if (bowType instanceof Crossbow){
			return  12 +                    //12 base
					3*bowType.level() + 2*lvl;  //+3 per bow level, +2 per level (default scaling +2)
		} else if(bowType instanceof GoldenCrossbow){
            return  14 +                    //14 base
                    3*bowType.level() + 2*lvl;  //+3 per bow level, +2 per level (default scaling +2)
        } else if(bowType instanceof IronCrossbow){
            return  13 +                    //13 base
                    3*bowType.level() + 2*lvl;  //+3 per bow level, +2 per level (default scaling +2)
        } else if(bowType instanceof SmallCrossbow){
            return  10 +                    //10 base
                    3*bowType.level() + 2*lvl;  //+3 per bow level, +2 per level (default scaling +2)
        } else if(bowType instanceof WoodenCrossbow){
            return  11 +                    //11 base
                    3*bowType.level() + 2*lvl;  //+3 per bow level, +2 per level (default scaling +2)
        } else if(bowType instanceof GoldenBow){
            return  8 +                     //8 base
                    3*bowType.level() + 2*lvl;  //+3 per bow level, +2 per level (default scaling +2)
        } else if(bowType instanceof SmallBow){
            return  6 +                     //6 base
                    3*bowType.level() + 2*lvl;  //+3 per bow level, +2 per level (default scaling +2)
        } else {
			return  2 +     //2 base, down from 5
					2*lvl;  //scaling unchanged
		}
	}
	
	private static MeleeWeapon bowType;
	
	private void updateCrossbow(){
		if (Dungeon.hero.belongings.weapon instanceof Crossbow){
            bowType = (Crossbow) Dungeon.hero.belongings.weapon;
		} else if(Dungeon.hero.belongings.weapon instanceof GoldenCrossbow){
            bowType = (GoldenCrossbow) Dungeon.hero.belongings.weapon;
        } else if(Dungeon.hero.belongings.weapon instanceof IronCrossbow){
            bowType = (IronCrossbow) Dungeon.hero.belongings.weapon;
        } else if(Dungeon.hero.belongings.weapon instanceof SmallCrossbow){
            bowType = (SmallCrossbow) Dungeon.hero.belongings.weapon;
        } else if(Dungeon.hero.belongings.weapon instanceof WoodenCrossbow){
            bowType = (WoodenCrossbow) Dungeon.hero.belongings.weapon;
        } else if(Dungeon.hero.belongings.weapon instanceof GoldenBow){
            bowType = (GoldenBow) Dungeon.hero.belongings.weapon;
        } else if(Dungeon.hero.belongings.weapon instanceof SmallBow){
            bowType = (SmallBow) Dungeon.hero.belongings.weapon;
        } else {
            bowType = null;
		}
	}
	
	@Override
	public boolean hasEnchant(Class<? extends Enchantment> type, Char owner) {
		if (bowType != null && bowType.hasEnchant(type, owner)){
			return true;
		} else {
			return super.hasEnchant(type, owner);
		}
	}
	
	@Override
	public int proc(Char attacker, Char defender, int damage) {
		if (bowType != null && bowType.enchantment != null && attacker.buff(MagicImmune.class) == null){
			level(bowType.level());
			damage = bowType.enchantment.proc(this, attacker, defender, damage);
			level(0);
		}
		return super.proc(attacker, defender, damage);
	}
	
	@Override
	protected void onThrow(int cell) {
		updateCrossbow();
		super.onThrow(cell);
	}
	
	@Override
	public String info() {
		updateCrossbow();
		return super.info();
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public int price() {
		return super.price()/2; //half normal value
	}
	
	private final WndBag.Listener itemSelector = new WndBag.Listener() {
		
		@Override
		public void onSelect(final Item item) {
			
			if (item == null) return;
			
			final int maxToTip = Math.min(curItem.quantity(), item.quantity()*2);
			final int maxSeedsToUse = (maxToTip+1)/2;
			
			final int singleSeedDarts;
			
			final String[] options;
			
			if (curItem.quantity() == 1){
				singleSeedDarts = 1;
				options = new String[]{
						Messages.get(Dart.class, "tip_one"),
						Messages.get(Dart.class, "tip_cancel")};
			} else {
				singleSeedDarts = 2;
				if (maxToTip <= 2){
					options = new String[]{
							Messages.get(Dart.class, "tip_two"),
							Messages.get(Dart.class, "tip_cancel")};
				} else {
					options = new String[]{
							Messages.get(Dart.class, "tip_all", maxToTip, maxSeedsToUse),
							Messages.get(Dart.class, "tip_two"),
							Messages.get(Dart.class, "tip_cancel")};
				}
			}
			
			TippedDart tipResult = TippedDart.getTipped((Plant.Seed) item, 1);
			
			GameScene.show(new WndOptions(Messages.get(Dart.class, "tip_title"),
					Messages.get(Dart.class, "tip_desc", tipResult.name()) + "\n\n" + tipResult.desc(),
					options){
				
				@Override
				protected void onSelect(int index) {
					super.onSelect(index);
					
					if (index == 0 && options.length == 3){
						if (item.quantity() <= maxSeedsToUse){
							item.detachAll( curUser.belongings.backpack );
						} else {
							item.quantity(item.quantity() - maxSeedsToUse);
						}
						
						if (maxToTip < curItem.quantity()){
							curItem.quantity(curItem.quantity() - maxToTip);
						} else {
							curItem.detachAll(curUser.belongings.backpack);
						}
						
						TippedDart newDart = TippedDart.getTipped((Plant.Seed) item, maxToTip);
						if (!newDart.collect()) Dungeon.level.drop(newDart, curUser.pos).sprite.drop();
						
						curUser.spend( 1f );
						curUser.busy();
						curUser.sprite.operate(curUser.pos);
						
					} else if ((index == 1 && options.length == 3) || (index == 0 && options.length == 2)){
						item.detach( curUser.belongings.backpack );
						
						if (curItem.quantity() <= singleSeedDarts){
							curItem.detachAll( curUser.belongings.backpack );
						} else {
							curItem.quantity(curItem.quantity() - singleSeedDarts);
						}
						
						TippedDart newDart = TippedDart.getTipped((Plant.Seed) item, singleSeedDarts);
						if (!newDart.collect()) Dungeon.level.drop(newDart, curUser.pos).sprite.drop();
						
						curUser.spend( 1f );
						curUser.busy();
						curUser.sprite.operate(curUser.pos);
					}
				}
			});
			
		}
		
	};
}
