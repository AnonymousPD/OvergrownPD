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

package com.overgrownpixel.overgrownpixeldungeon.items.potions.exotic;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Invisibility;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs.NPC;
import com.overgrownpixel.overgrownpixeldungeon.effects.Beam;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.LeafParticle;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.tiles.DungeonTilemap;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class PotionOfStrung extends ExoticPotion {
	
	{
		initials = 37;
	}

    @Override
    public void apply(Hero hero) {
        setKnown();
        int mobsShot = 0;
        for(Mob mob : hero.visibleEnemiesList().toArray(new Mob[0])){
            if (mob != null) {

                final Ballistica shot = new Ballistica( curUser.pos, mob.pos, Ballistica.STOP_TERRAIN);
                int cell = shot.collisionPos;

                hero.sprite.zap(cell);

                Invisibility.dispel();

                mobsShot++;

                fx(shot, new Callback() {
                    public void call() {
                        onZap(shot);
                    }
                });
            }
        }

        if(mobsShot > 0){
            GLog.i(Messages.get(this, "mobs_effected", mobsShot));
        } else {
            GLog.i(Messages.get(this, "no_targets"));
        }
    }

    protected void onZap( Ballistica beam ) {

        int maxDistance = Math.min(Dungeon.hero.lvl*5, beam.dist);

        ArrayList<Char> chars = new ArrayList<>();

        int terrainPassed = 2;
        for (int c : beam.subPath(1, maxDistance)) {

            Char ch;
            if ((ch = Actor.findChar(c)) != null) {

                //we don't want to count passed terrain after the last enemy hit. That would be a lot of bonus levels.
                //terrainPassed starts at 2, equivalent of rounding up when /3 for integer arithmetic.
                terrainPassed = terrainPassed % 3;

                chars.add(ch);
            }

            if (Dungeon.level.solid[c])
                terrainPassed++;

            CellEmitter.center(c).burst(LeafParticle.GENERAL, Random.IntRange(1, 2));
        }

        for (Char ch : chars) {
            if(!(ch instanceof NPC) && !ch.isImmune(this.getClass())){
                ch.damage(Dungeon.hero.attackSkill, this);
                ch.sprite.centerEmitter().burst(LeafParticle.GENERAL, Random.IntRange(1, 2));
                ch.sprite.flash();
            }
        }
    }

    protected void fx( Ballistica beam, Callback callback ) {

        int cell = beam.path.get(Math.min(beam.dist, Dungeon.hero.lvl*5));
        curUser.sprite.parent.add(new Beam.VineRay(curUser.sprite.center(), DungeonTilemap.raisedTileCenterToWorld( cell )));
        callback.call();
    }
}
