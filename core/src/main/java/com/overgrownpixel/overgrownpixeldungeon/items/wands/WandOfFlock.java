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

package com.overgrownpixel.overgrownpixeldungeon.items.wands;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.R;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs.Sheep;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.MagicMissile;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.staffs.MagesStaff;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.CharSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class WandOfFlock extends DamageWand {

    {
        image = ItemSpriteSheet.WAND_FLOCK;

        collisionProperties = Ballistica.PROJECTILE;
    }

    @Override
    public int min(int lvl) {
        return 2 + lvl;
    }

    @Override
    public int max(int lvl) {
        return 4 + 2*lvl;
    }

    @Override
    protected void onZap(Ballistica bolt) {
        for (int i : PathFinder.NEIGHBOURS9){

            if (!Dungeon.level.solid[bolt.collisionPos + i]
                    && !Dungeon.level.pit[bolt.collisionPos + i]
                    && Actor.findChar(bolt.collisionPos + i) == null) {

                Sheep sheep = new Sheep();
                sheep.lifespan = Random.IntRange(min(level()), max(level()));
                sheep.pos = bolt.collisionPos + i;
                GameScene.add(sheep);
                Dungeon.level.press(sheep.pos, sheep);

                CellEmitter.get(sheep.pos).burst(Speck.factory(Speck.WOOL), 4);
            }
        }
        CellEmitter.get(bolt.collisionPos).burst(Speck.factory(Speck.WOOL), 4);
        Sample.INSTANCE.play(Assets.SND_PUFF);
    }

    @Override
    protected void fx(Ballistica bolt, Callback callback) {
        MagicMissile.boltFromChar(curUser.sprite.parent,
                MagicMissile.WOOL,
                curUser.sprite,
                bolt.collisionPos,
                callback);
        Sample.INSTANCE.play(Assets.SND_ZAP);
    }

    @Override
    public void onHit(MagesStaff staff, Char attacker, Char defender, int damage) {
        defender.sprite.showStatus( CharSprite.NEUTRAL, Messages.get(this, Random.element( Sheep.LINE_KEYS )) );
    }

    @Override
    public void staffFx(MagesStaff.StaffParticle particle) {
        particle.color( Game.instance.getResources().getInteger(R.integer.woolparticle1) );
        particle.am = 0.5f;
        particle.setLifespan(0.6f);
        particle.acc.set(0, -40);
        particle.setSize( 0f, 3f);
        particle.shuffleXY( 1.5f );
    }
}
