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

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Blindness;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Shadow;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs.MirrorImage;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.ShadowbloomPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.Random;

public class Shadowbloom extends Plant {

	{
		image = 63;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        Buff.prolong(enemy, Shadow.class, Shadow.DURATION);
    }

    @Override
    public void activate(Char ch) {
        Buff.prolong(ch, Shadow.class, Shadow.DURATION);
    }

    @Override
    public void activate() {
        MirrorImage mob = new MirrorImage();
        mob.duplicate(Dungeon.hero);
        GameScene.add(mob);
        ScrollOfTeleportation.appear( mob, pos );
        GLog.p(Messages.get(Shadow.class, "mirrorimage"));
    }

    @Override
    public void spiceEffect(Char ch) {
        ch.sprite.burst(new ShadowbloomPoisonParticle().getColor(), 10);
        Buff.prolong(ch, Shadow.class, 2f);
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.NEWSEEDS_SHADOWBLOOM;

			plantClass = Shadowbloom.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {
            Buff.prolong( defender, Blindness.class, Random.Int( damage, damage*2 ) );
        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return ShadowbloomPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new ShadowbloomPoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}
