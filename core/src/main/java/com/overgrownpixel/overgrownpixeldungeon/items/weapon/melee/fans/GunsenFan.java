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

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.fans;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Bleeding;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Frost;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.effects.MagicMissile;
import com.overgrownpixel.overgrownpixeldungeon.effects.Pushing;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

public class GunsenFan extends MeleeWeapon {

	{
		image = ItemSpriteSheet.GUNSEN_FAN;

		tier = 5;
		DLY = 0.25f; //4x speed
	}

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        Buff.affect( defender, Bleeding.class ).set( damage );
        if(attacker instanceof Hero){
            for(Mob mob : ((Hero) attacker).visibleEnemiesList()){
                bolt(mob.pos, mob);
            }
        }
        return super.proc(attacker, defender, damage);
    }

    public void bolt(Integer target, final Mob mob){
        if (target != null) {

            final Ballistica shot = new Ballistica( curUser.pos, target, Ballistica.STOP_TERRAIN);

            fx(shot, new Callback() {
                public void call() {
                    onHit(shot, mob);
                }
            });
        }
    }

    protected void fx(Ballistica bolt, Callback callback) {
        MagicMissile.boltFromChar( curUser.sprite.parent, MagicMissile.WIND, curUser.sprite, bolt.collisionPos, callback);
    }

    protected void onHit(Ballistica bolt, Mob mob) {

        //presses all tiles in the AOE first
        for (int i : PathFinder.NEIGHBOURS9){
            Dungeon.level.press(bolt.collisionPos+i, Actor.findChar(bolt.collisionPos+i), true);
        }

        if (mob != null){

            if (mob.isAlive() && bolt.path.size() > bolt.dist+1) {
                Ballistica trajectory = new Ballistica(mob.pos, bolt.path.get(bolt.dist + 1), Ballistica.MAGIC_BOLT);
                int strength = 50 + level();
                throwChar(mob, trajectory, strength);
                Buff.affect(mob, Frost.class, Frost.duration(mob));
            }
        }

    }

    public static void throwChar(final Char ch, final Ballistica trajectory, int power){
        int dist = Math.min(trajectory.dist, power);

        if (ch.properties().contains(Char.Property.BOSS))
            dist /= 2;

        if (dist == 0 || ch.properties().contains(Char.Property.IMMOVABLE)) return;

        if (Actor.findChar(trajectory.path.get(dist)) != null){
            dist--;
        }

        final int newPos = trajectory.path.get(dist);

        if (newPos == ch.pos) return;

        final int initialpos = ch.pos;

        Actor.addDelayed(new Pushing(ch, ch.pos, newPos, new Callback() {
            public void call() {
                if (initialpos != ch.pos) {
                    //something caused movement before pushing resolved, cancel to be safe.
                    ch.sprite.place(ch.pos);
                    return;
                }
                ch.pos = newPos;
                Dungeon.level.press(ch.pos, ch, true);
                if (ch == Dungeon.hero){
                    Dungeon.observe();
                }
            }
        }), -1);
    }

    @Override
	public int max(int lvl) {
		return  tier+           //5 base damage
                lvl;            //+1 per upgrade
	}

}
