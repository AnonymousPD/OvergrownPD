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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.harpoons;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.effects.Chains;
import com.overgrownpixel.overgrownpixeldungeon.effects.Pushing;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.EtherealChains;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.utils.Callback;

public class Harpoon extends MeleeWeapon {
	
	{
		image = ItemSpriteSheet.HARPOON;

		tier = 3;
	}

    @Override
    public int max(int lvl) {
        return  3*(tier+1) +    //12 base, down from 20
                lvl*(tier+1);   //scaling unchanged
    }

    @Override
    public int proc(Char attacker, Char defender, int damage) {

	    final Ballistica chain = new Ballistica(curUser.pos, defender.pos, Ballistica.STOP_TARGET);

	    if (Actor.findChar( chain.collisionPos ) != null) {
            harpoonEnemy(chain, curUser, Actor.findChar(chain.collisionPos));
        }

	    return super.proc(attacker, defender, damage);
    }

    //pulls an enemy to a position along the harpoons's path, as close to the hero as possible
    private void harpoonEnemy(Ballistica chain, final Hero hero, final Char enemy ){

        if (enemy.properties().contains(Char.Property.IMMOVABLE)) {
            GLog.w( Messages.get(EtherealChains.class, "cant_pull") );
            return;
        }

        int bestPos = -1;
        for (int i : chain.subPath(1, chain.dist)){
            //prefer to the earliest point on the path
            if (!Dungeon.level.solid[i] && Actor.findChar(i) == null){
                bestPos = i;
                break;
            }
        }

        if (bestPos == -1) {
            GLog.i(Messages.get(EtherealChains.class, "does_nothing"));
            return;
        }

        final int pulledPos = bestPos;

        hero.busy();
        hero.sprite.parent.add(new Chains(hero.sprite.center(), enemy.sprite.center(), new Callback() {
            public void call() {
                Actor.add(new Pushing(enemy, enemy.pos, pulledPos, new Callback() {
                    public void call() {
                        Dungeon.level.press(pulledPos, enemy, true);
                    }
                }));
                enemy.pos = pulledPos;
                Dungeon.observe();
                GameScene.updateFog();
                hero.spendAndNext(1f);
            }
        }));
    }
}
