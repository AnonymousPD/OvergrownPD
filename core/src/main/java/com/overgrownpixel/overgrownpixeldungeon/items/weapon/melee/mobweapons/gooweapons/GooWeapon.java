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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.mobweapons.gooweapons;

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Ooze;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.items.TomeOfMastery;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.scenes.PixelScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.ui.RedButton;
import com.overgrownpixel.overgrownpixeldungeon.ui.RenderedTextMultiline;
import com.overgrownpixel.overgrownpixeldungeon.ui.Window;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.overgrownpixel.overgrownpixeldungeon.windows.IconTitle;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class GooWeapon extends MeleeWeapon {

    private static final String AC_TRANSMUTE = "TRANSMUTE";

	{
        weaponType = 0;

	    tier = 3;
	}

    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions( hero );
        if(isIdentified() && isEquipped(hero)){
            actions.add( AC_TRANSMUTE );
        }
        return actions;
    }

    @Override
    public void execute( Hero hero, String action ) {

        super.execute( hero, action );

        if (action.equals(AC_TRANSMUTE)) {
            if(!this.cursed){
                GameScene.show(new WndTransmute(this));
            } else {
                int form;
                do {
                    form = Random.Int(0, 4);
                    transMute(form);
                } while (weaponType == form);
            }
        }
    }

    int weaponType;
    private static final String WEAPONTYPE = "weaponType";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(WEAPONTYPE, weaponType);
        switchImage(weaponType);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        weaponType = bundle.getInt(WEAPONTYPE);
        switchImage(weaponType);
    }

    @Override
	public int max(int lvl) {
        if(weaponType == 2){        //axe
            return  6*(tier+1) +    //24 base
                    lvl*(tier+2);   //+5 per upgrade
        } else {
            return  5*(tier+1) +    //20 base
                    lvl*(tier+1);   //scaling unchanged
        }
	}

    @Override
    public int defenseFactor( Char owner ) {
        if(weaponType == 4){        //shield
            return 10+3*level();    //10 extra defence, plus 3 per level;
        } else {
            return super.defenseFactor(owner);
        }
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        if(weaponType == 0){                            // sword
            Buff.affect(defender, Ooze.class).set(10f); // caustic ooze
        }
        return super.proc(attacker, defender, damage);
    }

    private void transMute(int form){
        if(weaponType == form){
            GLog.n(Messages.get(this, "sameweapon"));
            return;
        }
        weaponType = form;
        switch (form){
            case 0:
                image = ItemSpriteSheet.GOO_SWORD;
                GLog.p(Messages.get(this, "transmutedto0"));
                RCH = 1;
                ACC = 1f;
                break;
            case 1:
                image = ItemSpriteSheet.GOO_HAMMER;
                GLog.p(Messages.get(this, "transmutedto1"));
                RCH = 1;
                ACC = 1.5f; //+50% accuracy
                break;
            case 2:
                image = ItemSpriteSheet.GOO_AXE;
                GLog.p(Messages.get(this, "transmutedto2"));
                RCH = 1;
                ACC = 1f;
                break;
            case 3:
                image = ItemSpriteSheet.GOO_SPEAR;
                GLog.p(Messages.get(this, "transmutedto3"));
                RCH = 2;
                ACC = 1f;
                break;
            case 4:
                image = ItemSpriteSheet.GOO_SHIELD;
                GLog.p(Messages.get(this, "transmutedto4"));
                RCH = 1;
                ACC = 1f;
                break;
        }
    }

    private void switchImage(int weaponType){
        switch (weaponType){
            case 0:
                image = ItemSpriteSheet.GOO_SWORD;
                RCH = 1;
                ACC = 1f;
                break;
            case 1:
                image = ItemSpriteSheet.GOO_HAMMER;
                RCH = 1;
                ACC = 1.5f; //+50% accuracy
                break;
            case 2:
                image = ItemSpriteSheet.GOO_AXE;
                RCH = 1;
                ACC = 1f;
                break;
            case 3:
                image = ItemSpriteSheet.GOO_SPEAR;
                RCH = 2;
                ACC = 1f;
                break;
            case 4:
                image = ItemSpriteSheet.GOO_SHIELD;
                RCH = 1;
                ACC = 1f;
                break;
        }
    }

    private class WndTransmute extends Window {

        private static final int WIDTH = 120;
        private static final int BTN_HEIGHT = 18;
        private static final float GAP = 2;

        public WndTransmute(final GooWeapon gooWeapon) {

            super();

            IconTitle titlebar = new IconTitle();
            titlebar.icon(new ItemSprite(gooWeapon.image(), null));
            titlebar.label(gooWeapon.name());
            titlebar.setRect(0, 0, WIDTH, 0);
            add(titlebar);

            RenderedTextMultiline txt = PixelScene.renderMultiline(6);
            txt.text(Messages.get(GooWeapon.class, "transmutetext"), WIDTH);
            txt.setPos(titlebar.left(), titlebar.bottom() + GAP);
            add(txt);

            RedButton btnWeapon0 = new RedButton(Messages.get(GooWeapon.class, "weapon0")) {
                @Override
                protected void onClick() {
                    hide();
                    transMute(0);
                }
            };
            btnWeapon0.setRect(0, txt.bottom() + GAP, WIDTH, BTN_HEIGHT);
            add(btnWeapon0);

            RedButton btnWeapon1 = new RedButton(Messages.get(GooWeapon.class, "weapon1")) {
                @Override
                protected void onClick() {
                    hide();
                    transMute(1);
                }
            };
            btnWeapon1.setRect(0, btnWeapon0.bottom() + GAP, WIDTH, BTN_HEIGHT);
            add(btnWeapon1);

            RedButton btnWeapon2 = new RedButton(Messages.get(GooWeapon.class, "weapon2")) {
                @Override
                protected void onClick() {
                    hide();
                    transMute(2);
                }
            };
            btnWeapon2.setRect(0, btnWeapon1.bottom() + GAP, WIDTH, BTN_HEIGHT);
            add(btnWeapon2);

            RedButton btnWeapon3 = new RedButton(Messages.get(GooWeapon.class, "weapon3")) {
                @Override
                protected void onClick() {
                    hide();
                    transMute(3);
                }
            };
            btnWeapon3.setRect(0, btnWeapon2.bottom() + GAP, WIDTH, BTN_HEIGHT);
            add(btnWeapon3);

            RedButton btnWeapon4 = new RedButton(Messages.get(GooWeapon.class, "weapon4")) {
                @Override
                protected void onClick() {
                    hide();
                    transMute(4);
                }
            };
            btnWeapon4.setRect(0, btnWeapon3.bottom() + GAP, WIDTH, BTN_HEIGHT);
            add(btnWeapon4);


            RedButton btnCancel = new RedButton(Messages.get(GooWeapon.class, "cancel")) {
                @Override
                protected void onClick() {
                    hide();
                }
            };
            btnCancel.setRect(0, btnWeapon4.bottom() + GAP, WIDTH, BTN_HEIGHT);
            add(btnCancel);

            resize(WIDTH, (int) btnCancel.bottom());
        }
    }

}
