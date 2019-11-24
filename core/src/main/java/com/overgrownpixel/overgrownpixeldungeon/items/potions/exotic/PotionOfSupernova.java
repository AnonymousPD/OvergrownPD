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

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Mob;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs.NPC;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.BlastParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.SmokeParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.Heap;
import com.overgrownpixel.overgrownpixeldungeon.items.bombs.Bomb;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class PotionOfSupernova extends ExoticPotion {
	
	{
		initials = 53;
	}

    @Override
    public void apply(Hero hero) {
        setKnown();
        int mobsEffected = 0;
        for(Mob mob : hero.visibleEnemiesList().toArray(new Mob[0])){
            explode(mob.pos, hero);
        }
        explode(hero.pos, hero);
        if(mobsEffected > 0){
            GLog.i(Messages.get(this, "mobs_effected", mobsEffected));
        } else {
            GLog.i(Messages.get(this, "no_targets"));
        }
    }

    public void explode(int cell, Char immuneChar){

        Sample.INSTANCE.play( Assets.SND_BLAST );

        ArrayList<Char> affected = new ArrayList<>();

        if (Dungeon.level.heroFOV[cell]) {
            CellEmitter.center(cell).burst(BlastParticle.FACTORY, 30);
        }

        boolean terrainAffected = false;
        for (int n : PathFinder.NEIGHBOURS9) {
            int c = cell + n;
            if (c >= 0 && c < Dungeon.level.length()) {
                if (Dungeon.level.heroFOV[c]) {
                    CellEmitter.get(c).burst(SmokeParticle.FACTORY, 4);
                }

                if (Dungeon.level.flamable[c]) {
                    Dungeon.level.destroy(c);
                    GameScene.updateMap(c);
                    terrainAffected = true;
                }

                //destroys items / triggers bombs caught in the blast.
                Heap heap = Dungeon.level.heaps.get(c);
                if (heap != null)
                    heap.explode();

                Char ch = Actor.findChar(c);
                if (ch != null) {
                    affected.add(ch);
                }
            }
        }

        for (Char ch : affected){
            if(!(ch instanceof NPC) && !ch.isImmune(this.getClass())){
                //those not at the center of the blast take damage less consistently.
                int minDamage = ch.pos == cell ? Dungeon.depth + 5 : 1;
                int maxDamage = 10 + Dungeon.depth * 2;

                int dmg = Random.NormalIntRange(minDamage, maxDamage) - ch.drRoll();
                if (dmg > 0 && !ch.equals(immuneChar)) {
                    ch.damage(dmg, this);
                }

                if (ch == Dungeon.hero && !ch.isAlive()) {
                    Dungeon.fail(Bomb.class);
                }
            }
        }

        if (terrainAffected) {
            Dungeon.observe();
        }
    }
	
}
