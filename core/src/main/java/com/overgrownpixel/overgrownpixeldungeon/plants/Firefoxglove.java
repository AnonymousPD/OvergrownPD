/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Overgrown Pixel Dungeon
 * Copyright (C) 2016-2019 Anon
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

package com.overgrownpixel.overgrownpixeldungeon.plants;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Fire;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.TrailOfFire;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.Hero;
import com.overgrownpixel.overgrownpixeldungeon.actors.hero.HeroSubClass;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.LivingPlant;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.BlastParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.FlameParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.SmokeParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.FirefoxglovePoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.Heap;
import com.overgrownpixel.overgrownpixeldungeon.items.bombs.Bomb;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.BArray;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Firefoxglove extends Plant {

	{
		image = 16;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        if(enemy instanceof Hero && ((Hero) enemy).subClass == HeroSubClass.WARDEN){
            Buff.prolong(enemy, TrailOfFire.class, TrailOfFire.DURATION);
        } else {
            new Firebomb().explode(enemy.pos);
        }
    }

    @Override
    public void activate(Char ch) {
	    if(ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
            Buff.prolong(ch, TrailOfFire.class, TrailOfFire.DURATION);
        } else {
            new Firebomb().explode(ch.pos);
        }
    }

    @Override
    public void activate() {
        new Firebomb().explode(pos);
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new FirefoxglovePoisonParticle().getColor(), 10);
        Buff.prolong(ch, TrailOfFire.class, 2f);
    }

    @Override
    public Blob immunity() {
        return new Fire();
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.NEWSEEDS_FIREFOXGLOVE;

			plantClass = Firefoxglove.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            new Firebomb().explode(defender.pos);
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return FirefoxglovePoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new FirefoxglovePoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}

	public static class Firebomb extends Bomb {

	    @Override
        public void explode(int cell) {
            //We're blowing up, so no need for a fuse anymore.
            this.fuse = null;

            Sample.INSTANCE.play( Assets.SND_BLAST );

            if (explodesDestructively()) {

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
                    //those not at the center of the blast take damage less consistently.
                    int minDamage = ch.pos == cell ? Dungeon.depth + 5 : 1;
                    int maxDamage = 10 + Dungeon.depth * 2;

                    int dmg = Random.NormalIntRange(minDamage, maxDamage) - ch.drRoll();
                    if (dmg > 0) {
                        if(!(ch instanceof LivingPlant)){
                            ch.damage(dmg, this);
                        }
                    }

                    if (ch == Dungeon.hero && !ch.isAlive()) {
                        Dungeon.fail(Bomb.class);
                    }
                }

                if (terrainAffected) {
                    Dungeon.observe();
                }
            }

            PathFinder.buildDistanceMap( cell, BArray.not( Dungeon.level.solid, null ), 2 );
            for (int i = 0; i < PathFinder.distance.length; i++) {
                if (PathFinder.distance[i] < Integer.MAX_VALUE) {
                    if (Dungeon.level.pit[i])
                        GameScene.add(Blob.seed(i, 2, Fire.class));
                    else
                        GameScene.add(Blob.seed(i, 10, Fire.class));
                    CellEmitter.get(i).burst(FlameParticle.FACTORY, 5);
                }
            }
            Sample.INSTANCE.play(Assets.SND_BURNING);
        }
    }
}
