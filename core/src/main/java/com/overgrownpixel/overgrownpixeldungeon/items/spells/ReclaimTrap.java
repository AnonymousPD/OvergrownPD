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

package com.overgrownpixel.overgrownpixeldungeon.items.spells;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.OvergrownPixelDungeon;
import com.overgrownpixel.overgrownpixeldungeon.R;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.items.quest.MetalShard;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.overgrownpixel.overgrownpixeldungeon.levels.traps.Trap;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class ReclaimTrap extends TargetedSpell {
	
	{
		image = ItemSpriteSheet.RECLAIM_TRAP;
	}
	
	private Class<?extends Trap> storedTrap = null;
	
	@Override
	protected void affectTarget(Ballistica bolt, Hero hero) {
		if (storedTrap == null) {
			quantity++; //storing a trap doesn't consume the spell
			Trap t = Dungeon.level.traps.get(bolt.collisionPos);
			if (t != null && t.active && t.visible) {
				t.disarm();
				
				Sample.INSTANCE.play(Assets.SND_LIGHTNING);
				ScrollOfRecharging.charge(hero);
				storedTrap = t.getClass();
				
			} else {
				GLog.w(Messages.get(this, "no_trap"));
			}
		} else {
			
			try {
				Trap t = storedTrap.newInstance();
				storedTrap = null;
				
				t.pos = bolt.collisionPos;
				t.activate();
				
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
			}
		}
	}
	
	@Override
	public String desc() {
		String desc = super.desc();
		if (storedTrap != null){
			desc += "\n\n" + Messages.get(this, "desc_trap", Messages.get(storedTrap, "name"));
		}
		return desc;
	}
	
	@Override
	protected void onThrow(int cell) {
		storedTrap = null;
		super.onThrow(cell);
	}
	
	@Override
	public void doDrop(Hero hero) {
		storedTrap = null;
		super.doDrop(hero);
	}
	
	private static final ItemSprite.Glowing[] COLORS = new ItemSprite.Glowing[]{
			new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.reclaimtrapcolor1) ),
			new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.reclaimtrapcolor2) ),
			new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.reclaimtrapcolor3) ),
			new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.reclaimtrapcolor4) ),
			new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.reclaimtrapcolor5) ),
			new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.reclaimtrapcolor6) ),
			new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.reclaimtrapcolor7) ),
			new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.reclaimtrapcolor8) ),
			new ItemSprite.Glowing( Game.instance.getResources().getInteger(R.integer.reclaimtrapcolor9) )
	};
	
	@Override
	public ItemSprite.Glowing glowing() {
		if (storedTrap != null){
			try {
				return COLORS[storedTrap.newInstance().color];
			} catch (Exception e) {
				OvergrownPixelDungeon.reportException(e);
			}
		}
		return null;
	}
	
	@Override
	public int price() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((40 + 100) / 3f));
	}
	
	private static final String STORED_TRAP = "stored_trap";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(STORED_TRAP, storedTrap);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		storedTrap = bundle.getClass(STORED_TRAP);
	}
	
	public static class Recipe extends com.overgrownpixel.overgrownpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ScrollOfMagicMapping.class, MetalShard.class};
			inQuantity = new int[]{1, 1};
			
			cost = 6;
			
			output = ReclaimTrap.class;
			outQuantity = 3;
		}
		
	}
	
}
