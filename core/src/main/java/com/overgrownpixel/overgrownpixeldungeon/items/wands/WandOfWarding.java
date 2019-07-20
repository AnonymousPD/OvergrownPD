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
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.npcs.NPC;
import com.overgrownpixel.overgrownpixeldungeon.effects.MagicMissile;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.staffs.MagesStaff;
import com.overgrownpixel.overgrownpixeldungeon.mechanics.Ballistica;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.CharSprite;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.overgrownpixel.overgrownpixeldungeon.sprites.mobs.WardSprite;
import com.overgrownpixel.overgrownpixeldungeon.ui.QuickSlotButton;
import com.overgrownpixel.overgrownpixeldungeon.utils.GLog;
import com.overgrownpixel.overgrownpixeldungeon.windows.WndOptions;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class WandOfWarding extends DamageWand {

    {
        collisionProperties = Ballistica.STOP_TARGET | Ballistica.STOP_TERRAIN;

        image = ItemSpriteSheet.WAND_WARDING;
    }

    @Override
    public int min(int lvl) {
        return 0;
    }

    @Override
    public int max(int lvl) {
        return 0;
    }

    @Override
    protected void onZap(Ballistica bolt) {

        int currentWardEnergy = 0;
        for (Char ch : Actor.chars()) {
            if (ch instanceof Ward) {
                currentWardEnergy += ((Ward) ch).tier + 1;
            }
        }

        int maxWardEnergy = 0;
        for (Buff buff : curUser.buffs()) {
            if (buff instanceof Wand.Charger) {
                if (((Charger) buff).wand() instanceof WandOfWarding) {
                    maxWardEnergy += 3 + ((Charger) buff).wand().level();
                }
            }
        }

        Char ch = Actor.findChar(bolt.collisionPos);
        if (ch != null) {
            if (ch instanceof Ward) {
                if (currentWardEnergy < maxWardEnergy) {
                    ((Ward) ch).upgrade(level());
                } else {
                    if (((Ward) ch).tier <= 3) {
                        GLog.w(Messages.get(this, "no_more_wards"));
                    } else {
                        ((Ward) ch).wandHeal(level());
                    }
                }
                ch.sprite.emitter().burst(MagicMissile.WardParticle.UP, ((Ward) ch).tier);
            } else {
                GLog.w(Messages.get(this, "bad_location"));
            }
        } else if (canPlaceWard(bolt.collisionPos)) {
            if ((currentWardEnergy + 2) <= maxWardEnergy) {
                Ward ward = new Ward();
                ward.pos = bolt.collisionPos;
                ward.wandLevel = level();
                GameScene.add(ward, 1f);
                Dungeon.level.press(ward.pos, ward);
                ward.sprite.emitter().burst(MagicMissile.WardParticle.UP, ward.tier);
                QuickSlotButton.target(ward);
            } else {
                GLog.w(Messages.get(this, "no_more_wards"));
            }
        } else {
            GLog.w(Messages.get(this, "bad_location"));
        }
    }

    @Override
    protected void fx(Ballistica bolt, Callback callback) {
        MagicMissile.boltFromChar(curUser.sprite.parent,
                MagicMissile.WARD,
                curUser.sprite,
                bolt.collisionPos,
                callback);
        Sample.INSTANCE.play(Assets.SND_ZAP);
    }

    @Override
    public void onHit(MagesStaff staff, Char attacker, Char defender, int damage) {

        int level = Math.max(0, staff.level());

        // lvl 0 - 20%
        // lvl 1 - 33%
        // lvl 2 - 43%
        if (Random.Int(level + 5) >= 4) {
            for (Char ch : Actor.chars()) {
                if (ch instanceof Ward) {
                    ((Ward) ch).wandHeal(staff.level());
                    ch.sprite.emitter().burst(MagicMissile.WardParticle.UP, ((Ward) ch).tier);
                }
            }
        }
    }

    @Override
    public void staffFx(MagesStaff.StaffParticle particle) {
        particle.color(Game.instance.getResources().getInteger(R.integer.magicmissile7));
        particle.am = 0.3f;
        particle.setLifespan(3f);
        particle.speed.polar(Random.Float(PointF.PI2), 0.3f);
        particle.setSize(1f, 2f);
        particle.radiateXY(2.5f);
    }

    public static boolean canPlaceWard(int pos) {

        for (int i : PathFinder.CIRCLE8) {
            if (Actor.findChar(pos + i) instanceof Ward) {
                return false;
            }
        }

        return true;

    }

    @Override
    public String statsDesc() {
        if (levelKnown)
            return Messages.get(this, "stats_desc", level() + 3);
        else
            return Messages.get(this, "stats_desc", 3);
    }

    public static class Ward extends NPC {

        private int tier = 1;
        private int wandLevel = 1;

        private int totalZaps = 0;

        {
            spriteClass = WardSprite.class;

            alignment = Alignment.ALLY;

            properties.add(Property.IMMOVABLE);

            viewDistance = 3;
            state = WANDERING;

            name = Messages.get(this, "name_" + tier);
        }

        public void upgrade(int wandLevel) {
            if (this.wandLevel < wandLevel) {
                this.wandLevel = wandLevel;
            }

            wandHeal(0);

            switch (tier) {
                case 1:
                case 2:
                default:
                    break; //do nothing
                case 3:
                    HP = HT = 30;
                    break;
                case 4:
                    HT = 48;
                    HP = Math.round(48 * (HP / 30f));
                    break;
                case 5:
                    HT = 70;
                    HP = Math.round(70 * (HP / 48f));
                    break;
            }

            if (tier < 6) {
                tier++;
                viewDistance++;
                name = Messages.get(this, "name_" + tier);
                updateSpriteState();
            }
        }

        private void wandHeal(int wandLevel) {
            if (this.wandLevel < wandLevel) {
                this.wandLevel = wandLevel;
            }

            switch (tier) {
                default:
                    break;
                case 4:
                    HP = Math.min(HT, HP + 6);
                    break;
                case 5:
                    HP = Math.min(HT, HP + 8);
                    break;
                case 6:
                    HP = Math.min(HT, HP + 12);
                    break;
            }
        }

        @Override
        public int defenseSkill(Char enemy) {
            if (tier > 3) {
                defenseSkill = 4 + Dungeon.depth;
            }
            return super.defenseSkill(enemy);
        }

        @Override
        public int drRoll() {
            if (tier > 3) {
                return Math.round(Random.NormalIntRange(0, 3 + Dungeon.depth / 2) / (7f - tier));
            } else {
                return 0;
            }
        }

        @Override
        protected float attackDelay() {
            switch (tier) {
                case 1:
                case 2:
                default:
                    return 2f;
                case 3:
                case 4:
                    return 1.5f;
                case 5:
                case 6:
                    return 1f;
            }
        }

        @Override
        protected boolean canAttack(Char enemy) {
            return new Ballistica(pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos;
        }

        @Override
        protected boolean doAttack(Char enemy) {
            boolean visible = fieldOfView[pos] || fieldOfView[enemy.pos];
            if (visible) {
                sprite.zap(enemy.pos);
            } else {
                zap();
            }

            return !visible;
        }

        private void zap() {
            spend(1f);

            //always hits
            int dmg = Random.NormalIntRange(2 + wandLevel, 8 + 4 * wandLevel);
            enemy.damage(dmg, WandOfWarding.class);
            if (enemy.isAlive()) {
                Wand.processSoulMark(enemy, wandLevel, 1);
            }

            if (!enemy.isAlive() && enemy == Dungeon.hero) {
                Dungeon.fail(getClass());
            }

            totalZaps++;
            switch (tier) {
                case 1:
                default:
                    if (totalZaps >= tier) {
                        die(this);
                    }
                    break;
                case 2:
                case 3:
                    if (totalZaps > tier) {
                        die(this);
                    }
                    break;
                case 4:
                    damage(5, this);
                    break;
                case 5:
                    damage(6, this);
                    break;
                case 6:
                    damage(7, this);
                    break;
            }
        }

        public void onZapComplete() {
            zap();
            next();
        }

        @Override
        protected boolean getCloser(int target) {
            return false;
        }

        @Override
        protected boolean getFurther(int target) {
            return false;
        }

        @Override
        public CharSprite sprite() {
            WardSprite sprite = (WardSprite) super.sprite();
            sprite.updateTier(tier);
            return sprite;
        }

        @Override
        public void updateSpriteState() {
            super.updateSpriteState();
            ((WardSprite) sprite).updateTier(tier);
        }

        @Override
        public void destroy() {
            super.destroy();
            Dungeon.observe();
        }

        @Override
        public boolean interact() {
            GameScene.show(new WndOptions(Messages.get(this, "dismiss_title"),
                    Messages.get(this, "dismiss_body"),
                    Messages.get(this, "dismiss_confirm"),
                    Messages.get(this, "dismiss_cancel")) {
                @Override
                protected void onSelect(int index) {
                    if (index == 0) {
                        die(null);
                        Dungeon.observe();
                    }
                }
            });
            return true;
        }

        @Override
        public String description() {
            return Messages.get(this, "desc_" + tier, 2 + wandLevel, 8 + 4 * wandLevel);
        }

        private static final String TIER = "tier";
        private static final String WAND_LEVEL = "wand_level";
        private static final String TOTAL_ZAPS = "total_zaps";

        @Override
        public void storeInBundle(Bundle bundle) {
            super.storeInBundle(bundle);
            bundle.put(TIER, tier);
            bundle.put(WAND_LEVEL, wandLevel);
            bundle.put(TOTAL_ZAPS, totalZaps);
        }

        @Override
        public void restoreFromBundle(Bundle bundle) {
            super.restoreFromBundle(bundle);
            tier = bundle.getInt(TIER);
            viewDistance = 2 + tier;
            name = Messages.get(this, "name_" + tier);
            wandLevel = bundle.getInt(WAND_LEVEL);
            totalZaps = bundle.getInt(TOTAL_ZAPS);
        }

        {
            properties.add(Property.IMMOVABLE);
        }
    }
}
