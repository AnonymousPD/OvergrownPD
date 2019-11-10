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

package com.overgrownpixel.overgrownpixeldungeon.sprites.items;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;

public class ItemSpriteSheet {

	private static final int WIDTH = 16;

	public static TextureFilm film = new TextureFilm( Assets.ITEMS, 16, 16 );

	private static int xy(int x, int y){
		x -= 1; y -= 1;
		return x + WIDTH*y;
	}

	private static final int PLACEHOLDERS   =                               xy(1, 1);   //16 slots
	//SOMETHING is the default item sprite at position 0. May show up ingame if there are bugs.
	public static final int SOMETHING       = PLACEHOLDERS+0;
	public static final int WEAPON_HOLDER   = PLACEHOLDERS+1;
	public static final int ARMOR_HOLDER    = PLACEHOLDERS+2;
	public static final int MISSILE_HOLDER  = PLACEHOLDERS+3;
	public static final int WAND_HOLDER     = PLACEHOLDERS+4;
	public static final int RING_HOLDER     = PLACEHOLDERS+5;
	public static final int ARTIFACT_HOLDER = PLACEHOLDERS+6;
	public static final int FOOD_HOLDER     = PLACEHOLDERS+7;
	public static final int BOMB_HOLDER     = PLACEHOLDERS+8;
	public static final int POTION_HOLDER   = PLACEHOLDERS+9;
	public static final int SCROLL_HOLDER   = PLACEHOLDERS+11;
	public static final int SEED_HOLDER     = PLACEHOLDERS+10;
	public static final int STONE_HOLDER    = PLACEHOLDERS+12;
	public static final int CATA_HOLDER     = PLACEHOLDERS+13;
	public static final int ELIXIR_HOLDER   = PLACEHOLDERS+14;
	public static final int SPELL_HOLDER    = PLACEHOLDERS+15;
	static{
		assignItemRect(SOMETHING,       8,  13);
		assignItemRect(WEAPON_HOLDER,   14, 14);
		assignItemRect(ARMOR_HOLDER,    14, 12);
		assignItemRect(MISSILE_HOLDER,  15, 15);
		assignItemRect(WAND_HOLDER,     14, 14);
		assignItemRect(RING_HOLDER,     8,  10);
		assignItemRect(ARTIFACT_HOLDER, 15, 15);
		assignItemRect(FOOD_HOLDER,     15, 11);
		assignItemRect(BOMB_HOLDER,     10, 13);
		assignItemRect(POTION_HOLDER,   12, 14);
		assignItemRect(SEED_HOLDER,     10, 10);
		assignItemRect(SCROLL_HOLDER,   15, 14);
		assignItemRect(STONE_HOLDER,    14, 12);
		assignItemRect(CATA_HOLDER,     6,  15);
		assignItemRect(ELIXIR_HOLDER,   12, 14);
		assignItemRect(SPELL_HOLDER,    8,  16);
	}

	private static final int UNCOLLECTIBLE  =                               xy(1, 2);   //16 slots
	public static final int GOLD            = UNCOLLECTIBLE+0;
	public static final int DEWDROP         = UNCOLLECTIBLE+1;
	public static final int PETAL           = UNCOLLECTIBLE+2;
	public static final int SANDBAG         = UNCOLLECTIBLE+3;
	public static final int SPIRIT_ARROW    = UNCOLLECTIBLE+4;
	
	public static final int GUIDE_PAGE      = UNCOLLECTIBLE+6;
	public static final int ALCH_PAGE       = UNCOLLECTIBLE+7;
	static{
		assignItemRect(GOLD,        15, 13);
		assignItemRect(DEWDROP,     10, 10);
		assignItemRect(PETAL,       8,  8);
		assignItemRect(SANDBAG,     10, 10);
		assignItemRect(SPIRIT_ARROW,11, 11);
		
		assignItemRect(GUIDE_PAGE,  10, 11);
		assignItemRect(ALCH_PAGE,   10, 11);
	}

	private static final int CONTAINERS     =                               xy(1, 3);   //16 slots
	public static final int BONES           = CONTAINERS+0;
	public static final int REMAINS         = CONTAINERS+1;
	public static final int TOMB            = CONTAINERS+2;
	public static final int GRAVE           = CONTAINERS+3;
	public static final int CHEST           = CONTAINERS+4;
	public static final int LOCKED_CHEST    = CONTAINERS+5;
	public static final int CRYSTAL_CHEST   = CONTAINERS+6;
	public static final int EBONY_CHEST     = CONTAINERS+7;
	static{
		assignItemRect(BONES,           14, 11);
		assignItemRect(REMAINS,         14, 11);
		assignItemRect(TOMB,            14, 15);
		assignItemRect(GRAVE,           14, 15);
		assignItemRect(CHEST,           16, 14);
		assignItemRect(LOCKED_CHEST,    16, 14);
		assignItemRect(CRYSTAL_CHEST,   16, 14);
		assignItemRect(EBONY_CHEST,     16, 14);
	}

	private static final int SINGLE_USE     =                               xy(1, 4);   //16 slots
	public static final int ANKH            = SINGLE_USE+0;
	public static final int STYLUS          = SINGLE_USE+1;
	
	public static final int SEAL            = SINGLE_USE+3;
	public static final int TORCH           = SINGLE_USE+4;
	public static final int BEACON          = SINGLE_USE+5;

	public static final int HONEYPOT        = SINGLE_USE+7;
	public static final int SHATTPOT        = SINGLE_USE+8;
	public static final int IRON_KEY        = SINGLE_USE+9;
	public static final int GOLDEN_KEY      = SINGLE_USE+10;
	public static final int CRYSTAL_KEY     = SINGLE_USE+11;
	public static final int SKELETON_KEY    = SINGLE_USE+12;
	public static final int MASTERY         = SINGLE_USE+13;
	public static final int KIT             = SINGLE_USE+14;
	public static final int AMULET          = SINGLE_USE+15;
	static{
		assignItemRect(ANKH,            10, 16);
		assignItemRect(STYLUS,          12, 13);
		
		assignItemRect(SEAL,            9,  15);
		assignItemRect(TORCH,           12, 15);
		assignItemRect(BEACON,          16, 15);
		
		assignItemRect(HONEYPOT,        14, 12);
		assignItemRect(SHATTPOT,        14, 12);
		assignItemRect(IRON_KEY,        8,  14);
		assignItemRect(GOLDEN_KEY,      8,  14);
		assignItemRect(CRYSTAL_KEY,     8,  14);
		assignItemRect(SKELETON_KEY,    8,  14);
		assignItemRect(MASTERY,         13, 16);
		assignItemRect(KIT,             16, 15);
		assignItemRect(AMULET,          16, 16);
	}
	
	private static final int BOMBS          =                               xy(1, 5);   //16 slots
	public static final int BOMB            = BOMBS+0;
	public static final int DBL_BOMB        = BOMBS+1;
	public static final int FIRE_BOMB       = BOMBS+2;
	public static final int FROST_BOMB      = BOMBS+3;
	public static final int REGROWTH_BOMB   = BOMBS+4;
	public static final int FLASHBANG       = BOMBS+5;
	public static final int SHOCK_BOMB      = BOMBS+6;
	public static final int HOLY_BOMB       = BOMBS+7;
	public static final int WOOLY_BOMB      = BOMBS+8;
	public static final int NOISEMAKER      = BOMBS+9;
	public static final int ARCANE_BOMB     = BOMBS+10;
	public static final int SHRAPNEL_BOMB   = BOMBS+11;
	
	static{
		assignItemRect(BOMB,            10, 13);
		assignItemRect(DBL_BOMB,        14, 13);
		assignItemRect(FIRE_BOMB,       13, 12);
		assignItemRect(FROST_BOMB,      13, 12);
		assignItemRect(REGROWTH_BOMB,   13, 12);
		assignItemRect(FLASHBANG,       13, 12);
		assignItemRect(SHOCK_BOMB,      10, 13);
		assignItemRect(HOLY_BOMB,       10, 13);
		assignItemRect(WOOLY_BOMB,      10, 13);
		assignItemRect(NOISEMAKER,      10, 13);
		assignItemRect(ARCANE_BOMB,     10, 13);
		assignItemRect(SHRAPNEL_BOMB,   10, 13);
	}

    private static final int WEP_TIER0     =                               xy(1, 6);   //16 slots
    public static final int MANRIKI_KUSARI = WEP_TIER0+0;
    public static final int GUNSEN_FAN     = WEP_TIER0+1;
    public static final int KANABO         = WEP_TIER0+2;
    public static final int YAWARA         = WEP_TIER0+3;
    public static final int OTSUCHI        = WEP_TIER0+4;
    public static final int JUTTE          = WEP_TIER0+5;
    public static final int SASUMATA       = WEP_TIER0+6;
    public static final int DOUBLE_BLADE   = WEP_TIER0+7;
    public static final int NAGINATA       = WEP_TIER0+8;
    public static final int SCYLD          = WEP_TIER0+9;
    public static final int LANCE          = WEP_TIER0+10;
    public static final int MANJI_SAI      = WEP_TIER0+11;
    public static final int RITUAL_KNIFE   = WEP_TIER0+12;
    public static final int BARBED_STAFF   = WEP_TIER0+13;
    public static final int PIRATE_SABRE   = WEP_TIER0+14;
    public static final int HARPOON        = WEP_TIER0+15;
    static {
        assignItemRect(MANRIKI_KUSARI,  15, 14);
        assignItemRect(GUNSEN_FAN,      15, 15);
        assignItemRect(KANABO,          15, 15);
        assignItemRect(YAWARA,          11, 11);
        assignItemRect(OTSUCHI,         15, 15);
        assignItemRect(JUTTE,           16, 16);
        assignItemRect(SASUMATA,        16, 16);
        assignItemRect(DOUBLE_BLADE,    15, 15);
        assignItemRect(NAGINATA,        16, 14);
        assignItemRect(SCYLD,           13, 16);
        assignItemRect(LANCE,           16, 16);
        assignItemRect(MANJI_SAI,       11, 12);
        assignItemRect(RITUAL_KNIFE,    13, 13);
        assignItemRect(BARBED_STAFF,    16, 16);
        assignItemRect(PIRATE_SABRE,    16, 16);
        assignItemRect(HARPOON,         14, 14);
    }
	
	                                                                                    //16 free slots

	private static final int WEP_TIER1      =                               xy(1, 7);   //8 slots
	public static final int WORN_SHORTSWORD = WEP_TIER1+0;
	public static final int RAPIER          = WEP_TIER1+1;
	public static final int GLOVES          = WEP_TIER1+2;
	public static final int KNUCKLEDUSTER   = WEP_TIER1+3;
	public static final int DAGGER          = WEP_TIER1+4;
	public static final int MAGES_STAFF     = WEP_TIER1+5;
    public static final int NUNCHAKU        = WEP_TIER1+6;
    public static final int KATANA          = WEP_TIER1+7;
	static{
		assignItemRect(WORN_SHORTSWORD, 13, 13);
        assignItemRect(RAPIER,          16, 16);
		assignItemRect(GLOVES,          12, 16);
        assignItemRect(KNUCKLEDUSTER,   15, 10);
		assignItemRect(DAGGER,          12, 13);
		assignItemRect(MAGES_STAFF,     15, 16);
        assignItemRect(NUNCHAKU,        14, 15);
        assignItemRect(KATANA,          15, 16);
	}

	private static final int WEP_TIER2      =                               xy(9, 7);   //8 slots
	public static final int SHORTSWORD      = WEP_TIER2+0;
	public static final int HAND_AXE        = WEP_TIER2+1;
	public static final int SPEAR           = WEP_TIER2+2;
	public static final int QUARTERSTAFF    = WEP_TIER2+3;
	public static final int DIRK            = WEP_TIER2+4;
    public static final int KITE_SHIELD     = WEP_TIER2+5;
    public static final int KHOPESH         = WEP_TIER2+6;
    public static final int SABRE           = WEP_TIER2+7;
	static{
		assignItemRect(SHORTSWORD,      13, 13);
		assignItemRect(HAND_AXE,        12, 14);
		assignItemRect(SPEAR,           16, 16);
		assignItemRect(QUARTERSTAFF,    16, 16);
		assignItemRect(DIRK,            13, 14);
        assignItemRect(KITE_SHIELD,     13, 15);
        assignItemRect(KHOPESH,         16, 15);
        assignItemRect(SABRE,           15, 16);
	}

	private static final int WEP_TIER3      =                               xy(1, 8);   //8 slots
	public static final int SWORD           = WEP_TIER3+0;
	public static final int MACE            = WEP_TIER3+1;
	public static final int SCIMITAR        = WEP_TIER3+2;
	public static final int ROUND_SHIELD    = WEP_TIER3+3;
	public static final int SAI             = WEP_TIER3+4;
	public static final int WHIP            = WEP_TIER3+5;
    public static final int KUSARIGAMA      = WEP_TIER3+6;
    public static final int SCYTHE          = WEP_TIER3+7;
	static{
		assignItemRect(SWORD,           14, 14);
		assignItemRect(MACE,            15, 15);
		assignItemRect(SCIMITAR,        13, 16);
		assignItemRect(ROUND_SHIELD,    16, 16);
		assignItemRect(SAI,             16, 16);
		assignItemRect(WHIP,            14, 14);
        assignItemRect(KUSARIGAMA,      13, 14);
        assignItemRect(SCYTHE,          13, 15);
	}

	private static final int WEP_TIER4      =                               xy(9, 8);   //8 slots
	public static final int LONGSWORD       = WEP_TIER4+0;
	public static final int BATTLE_AXE      = WEP_TIER4+1;
	public static final int FLAIL           = WEP_TIER4+2;
	public static final int RUNIC_BLADE     = WEP_TIER4+3;
	public static final int ASSASSINS_BLADE = WEP_TIER4+4;
	public static final int CROSSBOW        = WEP_TIER4+5;
    public static final int CLUB            = WEP_TIER4+6;
    public static final int TEKKO_KAGI      = WEP_TIER4+7;
	static{
		assignItemRect(LONGSWORD,       15, 15);
		assignItemRect(BATTLE_AXE,      16, 16);
		assignItemRect(FLAIL,           14, 14);
		assignItemRect(RUNIC_BLADE,     14, 14);
		assignItemRect(ASSASSINS_BLADE, 14, 15);
		assignItemRect(CROSSBOW,        15, 15);
        assignItemRect(CLUB,            16, 16);
        assignItemRect(TEKKO_KAGI,      16, 16);
	}

	private static final int WEP_TIER5      =                               xy(1, 9);   //8 slots
    public static final int GREATSWORD      = WEP_TIER5+0;
    public static final int WAR_HAMMER      = WEP_TIER5+1;
    public static final int GLAIVE          = WEP_TIER5+2;
    public static final int GREATAXE        = WEP_TIER5+3;
    public static final int GREATSHIELD     = WEP_TIER5+4;
    public static final int GAUNTLETS       = WEP_TIER5+5;
    public static final int HALBERD         = WEP_TIER5+6;
    static{
        assignItemRect(GREATSWORD,  16, 16);
        assignItemRect(WAR_HAMMER,  16, 16);
        assignItemRect(GLAIVE,      16, 16);
        assignItemRect(GREATAXE,    12, 16);
        assignItemRect(GREATSHIELD, 12, 16);
        assignItemRect(GAUNTLETS,   13, 15);
        assignItemRect(HALBERD,     16, 16);
    }

    private static final int WEP_TIER_MAGIC =                               xy(8, 9);   //8 slots
    public static final int SLIME_SWORD         = WEP_TIER_MAGIC+0;
    public static final int EMERALD_SWORD       = WEP_TIER_MAGIC+1;
    public static final int BRIMSTORM_SWORD     = WEP_TIER_MAGIC+2;
    public static final int ACIDIC_SWORD        = WEP_TIER_MAGIC+3;
    public static final int SAPHIRE_SWORD       = WEP_TIER_MAGIC+4;
    public static final int OBSIDIAN_SWORD      = WEP_TIER_MAGIC+5;
    public static final int EMERALD_BROADSWORD  = WEP_TIER_MAGIC+6;
    public static final int PIKACHURICHU_SWORD  = WEP_TIER_MAGIC+7;
    public static final int SLIMESTONE_SWORD    = WEP_TIER_MAGIC+8;
    static{
        assignItemRect(SLIME_SWORD,         16, 16);
        assignItemRect(EMERALD_SWORD,       16, 16);
        assignItemRect(BRIMSTORM_SWORD,     16, 16);
        assignItemRect(ACIDIC_SWORD,        16, 16);
        assignItemRect(SAPHIRE_SWORD,       16, 16);
        assignItemRect(OBSIDIAN_SWORD,      16, 16);
        assignItemRect(EMERALD_BROADSWORD,  16, 16);
        assignItemRect(PIKACHURICHU_SWORD,  16, 16);
        assignItemRect(SLIMESTONE_SWORD,    16, 16);
    }

    private static final int WEP_TIER_MAGIC2 =                               xy(1, 10);   //16 slots
    public static final int RUBY_SHORTSWORD         = WEP_TIER_MAGIC2+0;
    public static final int BALROG_WHIP             = WEP_TIER_MAGIC2+1;
    public static final int SAPHIRE_BROADSWORD      = WEP_TIER_MAGIC2+2;
    public static final int AMETHYST_SWORD          = WEP_TIER_MAGIC2+3;
    public static final int IOLITE_SWORD            = WEP_TIER_MAGIC2+4;
    public static final int ICE_KNUCKLEDUSTER       = WEP_TIER_MAGIC2+5;
    public static final int EMERALD_SABRE           = WEP_TIER_MAGIC2+6;
    public static final int AQUAMARINE_BLADE        = WEP_TIER_MAGIC2+7;
    public static final int RIB_SWORD               = WEP_TIER_MAGIC2+8;
    public static final int RAINBOW_BLADE           = WEP_TIER_MAGIC2+9;
    public static final int EMERALD_TRIDENT         = WEP_TIER_MAGIC2+10;
    public static final int EMERALD_CLUB            = WEP_TIER_MAGIC2+11;
    public static final int TANZANITE_BLADE         = WEP_TIER_MAGIC2+12;
    public static final int RUBY_CLUB               = WEP_TIER_MAGIC2+13;
    public static final int ALEXANDRITE_SWORD       = WEP_TIER_MAGIC2+14;
    public static final int DOUBLEGEM_BLADE         = WEP_TIER_MAGIC2+15;
    static{
        assignItemRect(RUBY_SHORTSWORD,         13, 13);
        assignItemRect(BALROG_WHIP,             16, 16);
        assignItemRect(SAPHIRE_BROADSWORD,      16, 16);
        assignItemRect(AMETHYST_SWORD,          16, 16);
        assignItemRect(IOLITE_SWORD,            15, 16);
        assignItemRect(ICE_KNUCKLEDUSTER,       15, 13);
        assignItemRect(EMERALD_SABRE,           15, 16);
        assignItemRect(AQUAMARINE_BLADE,        16, 16);
        assignItemRect(RIB_SWORD,               16, 16);
        assignItemRect(RAINBOW_BLADE,           16, 16);
        assignItemRect(EMERALD_TRIDENT,         15, 16);
        assignItemRect(EMERALD_CLUB,            16, 16);
        assignItemRect(TANZANITE_BLADE,         16, 16);
        assignItemRect(RUBY_CLUB,               16, 16);
        assignItemRect(ALEXANDRITE_SWORD,       15, 15);
        assignItemRect(DOUBLEGEM_BLADE,         16, 16);
    }

    private static final int WEP_TIER6 =                               xy(1, 11);   //16 slots
    public static final int RUBY_GLOVE                    = WEP_TIER6+0;
    public static final int IOLITE_GLOVE                  = WEP_TIER6+1;
    public static final int EMERALD_GLOVE                 = WEP_TIER6+2;
    public static final int EMERALD_GAUNTLET              = WEP_TIER6+3;
    public static final int IOLITE_GAUNTLET               = WEP_TIER6+4;
    public static final int YELLOW_GREEN_SAPHIRE_SHIELD   = WEP_TIER6+5;
    public static final int IOLITE_ROUNDSHIELD            = WEP_TIER6+6;
    public static final int EMERALD_ROUNDSHIELD           = WEP_TIER6+7;
    public static final int CRUSADER_KITESHIELD           = WEP_TIER6+8;
    public static final int PARAGON_SHIELD                = WEP_TIER6+9;
    public static final int IOLITE_WARHAMMER              = WEP_TIER6+10;
    public static final int EMERALD_WARHAMMER             = WEP_TIER6+11;
    public static final int STEEL_WARHAMMER               = WEP_TIER6+12;
    public static final int ZIRCON_WARAXE                 = WEP_TIER6+13;
    public static final int WARAXE                        = WEP_TIER6+14;
    public static final int TWOSIDED_WARAXE               = WEP_TIER6+15;
    static {
        assignItemRect(RUBY_GLOVE,                          12, 16);
        assignItemRect(IOLITE_GLOVE,                        12, 16);
        assignItemRect(EMERALD_GLOVE,                       12, 16);
        assignItemRect(EMERALD_GAUNTLET,                    13, 15);
        assignItemRect(IOLITE_GAUNTLET,                     13, 15);
        assignItemRect(YELLOW_GREEN_SAPHIRE_SHIELD,         12, 16);
        assignItemRect(IOLITE_ROUNDSHIELD,                  16, 16);
        assignItemRect(EMERALD_ROUNDSHIELD,                 16, 16);
        assignItemRect(CRUSADER_KITESHIELD,                 13, 16);
        assignItemRect(PARAGON_SHIELD,                      13, 16);
        assignItemRect(IOLITE_WARHAMMER,                    15, 15);
        assignItemRect(EMERALD_WARHAMMER,                   15, 15);
        assignItemRect(STEEL_WARHAMMER,                     16, 16);
        assignItemRect(ZIRCON_WARAXE,                       12, 16);
        assignItemRect(WARAXE,                              12, 16);
        assignItemRect(TWOSIDED_WARAXE,                     16, 16);
    }

    private static final int WEP_TIER7 =                               xy(1, 12);   //16 slots
    public static final int KNIFE_CHAIN                   = WEP_TIER7+0;
    public static final int NAIL_WHIP                     = WEP_TIER7+1;
    public static final int CHAIN_WHIP                    = WEP_TIER7+2;
    public static final int WHIP_SWORD                    = WEP_TIER7+3;
    public static final int BUTCHER_BLADE                 = WEP_TIER7+4;
    public static final int ADONRED_LONGSWORD             = WEP_TIER7+5;
    public static final int SWORD_SHIELD                  = WEP_TIER7+6;
    public static final int BROAD_SPEAR                   = WEP_TIER7+7;
    public static final int BROAD_BLADE                   = WEP_TIER7+8;
    public static final int ROUNDED_SPEAR                 = WEP_TIER7+9;
    public static final int SPIKED_CLUB                   = WEP_TIER7+10;
    public static final int SPIKED_SCYTHE                 = WEP_TIER7+11;
    public static final int CANE_BLADE                    = WEP_TIER7+12;
    public static final int CANE_SAW                      = WEP_TIER7+13;
    public static final int STEEL_BLADE                   = WEP_TIER7+14;
    public static final int HOOKED_WARAXE                 = WEP_TIER7+15;
    static {
        assignItemRect(KNIFE_CHAIN,                          16, 16);
        assignItemRect(NAIL_WHIP,                            16, 15);
        assignItemRect(CHAIN_WHIP,                           16, 15);
        assignItemRect(WHIP_SWORD,                           16, 15);
        assignItemRect(BUTCHER_BLADE,                        15, 13);
        assignItemRect(ADONRED_LONGSWORD,                    16, 16);
        assignItemRect(SWORD_SHIELD,                         15, 15);
        assignItemRect(BROAD_SPEAR,                          16, 16);
        assignItemRect(BROAD_BLADE,                          14, 16);
        assignItemRect(ROUNDED_SPEAR,                        15, 16);
        assignItemRect(ROUNDED_SPEAR,                        15, 16);
        assignItemRect(SPIKED_CLUB,                          16, 16);
        assignItemRect(SPIKED_SCYTHE,                        16, 16);
        assignItemRect(CANE_BLADE,                           15, 16);
        assignItemRect(CANE_SAW,                             16, 16);
        assignItemRect(STEEL_BLADE,                          16, 16);
        assignItemRect(HOOKED_WARAXE,                        16, 16);
    }

    private static final int WEP_TIER8 =                               xy(1, 13);   //16 slots
    public static final int CLAW_GLOVE                   = WEP_TIER8+0;
    public static final int CLUB_SWORD                   = WEP_TIER8+1;
    public static final int DOUBLE_LONGSWORD             = WEP_TIER8+2;
    public static final int DUAL_FENCING_BLADES          = WEP_TIER8+3;
    public static final int HACKBLADE                    = WEP_TIER8+4;
    public static final int CHEESE_GRATER                = WEP_TIER8+5;
    public static final int TRIPLE_HOOKED_HALBERD        = WEP_TIER8+6;
    public static final int HORN_KNIFE                   = WEP_TIER8+7;
    public static final int CLAW_KNIFE                   = WEP_TIER8+8;
    public static final int SPIKED_STONE_CLUB            = WEP_TIER8+9;
    public static final int PENDULUM_SPEAR               = WEP_TIER8+10;
    public static final int IRON_CROSSBOW                = WEP_TIER8+11;
    public static final int GOLDEN_CROSSBOW              = WEP_TIER8+12;
    public static final int SMALL_CROSSBOW               = WEP_TIER8+13;
    public static final int SMALL_BOW                    = WEP_TIER8+14;
    public static final int STEEL_LONGSWORD              = WEP_TIER8+15;
    static {
        assignItemRect(CLAW_GLOVE,                          16, 14);
        assignItemRect(CLUB_SWORD,                          16, 16);
        assignItemRect(DOUBLE_LONGSWORD,                    16, 16);
        assignItemRect(DUAL_FENCING_BLADES,                 16, 16);
        assignItemRect(HACKBLADE,                           16, 15);
        assignItemRect(CHEESE_GRATER,                       16, 16);
        assignItemRect(TRIPLE_HOOKED_HALBERD,               16, 16);
        assignItemRect(HORN_KNIFE,                          10, 16);
        assignItemRect(CLAW_KNIFE,                          16, 15);
        assignItemRect(SPIKED_STONE_CLUB,                   16, 16);
        assignItemRect(PENDULUM_SPEAR,                      16, 16);
        assignItemRect(IRON_CROSSBOW,                       16, 16);
        assignItemRect(GOLDEN_CROSSBOW,                     16, 16);
        assignItemRect(SMALL_CROSSBOW,                      14, 14);
        assignItemRect(SMALL_BOW,                           14, 14);
        assignItemRect(STEEL_LONGSWORD,                     16, 16);
    }

    private static final int WEP_TIER9 =                               xy(1, 14);   //16 slots
    public static final int WOODEN_CROSSBOW                   = WEP_TIER9+0;
    public static final int SWORD_WHEEL                       = WEP_TIER9+1;
    public static final int GOLDEN_BOW                        = WEP_TIER9+2;
    public static final int BAMBOO_SPEAR                      = WEP_TIER9+3;
    public static final int SCYTHE_WHEEL                      = WEP_TIER9+4;
    public static final int MUSCLE_SWORD                      = WEP_TIER9+5;
    public static final int SORROW_DIRK                       = WEP_TIER9+6;
    public static final int ROOT_SAI                          = WEP_TIER9+7;
    public static final int STORM_AXE                         = WEP_TIER9+8;
    public static final int VINE_WHIP                         = WEP_TIER9+9;
    public static final int GRASS_LASHER                      = WEP_TIER9+10;
    public static final int RAT_FANG_DAGGER                   = WEP_TIER9+11;
    public static final int TRIDENT_SPEAR                     = WEP_TIER9+12;
    public static final int STORM_KHOPESH                     = WEP_TIER9+13;
    public static final int SWIFTDENT                         = WEP_TIER9+14;
    public static final int GOO_HAMMER                        = WEP_TIER9+15;
    static {
        assignItemRect(WOODEN_CROSSBOW,                     14, 14);
        assignItemRect(SWORD_WHEEL,                         16, 16);
        assignItemRect(GOLDEN_BOW,                          15, 15);
        assignItemRect(BAMBOO_SPEAR,                        16, 16);
        assignItemRect(SCYTHE_WHEEL,                        16, 16);
        assignItemRect(MUSCLE_SWORD,                        15, 15);
        assignItemRect(SORROW_DIRK,                         13, 14);
        assignItemRect(ROOT_SAI,                            16, 16);
        assignItemRect(STORM_AXE,                           16, 16);
        assignItemRect(VINE_WHIP,                           14, 15);
        assignItemRect(GRASS_LASHER,                        16, 16);
        assignItemRect(RAT_FANG_DAGGER,                     11, 14);
        assignItemRect(TRIDENT_SPEAR,                       15, 16);
        assignItemRect(STORM_KHOPESH,                       13, 16);
        assignItemRect(SWIFTDENT,                           15, 16);
        assignItemRect(GOO_HAMMER,                          16, 16);
    }

    private static final int WEP_TIER10 =                               xy(1, 15);   //16 slots
    public static final int GOO_SWORD                           = WEP_TIER10+0;
    public static final int GOO_AXE                             = WEP_TIER10+1;
    public static final int GOO_SPEAR                           = WEP_TIER10+2;
    public static final int GOO_SHIELD                          = WEP_TIER10+3;
    public static final int FIREBLOOM_MACE                      = WEP_TIER10+4;
    public static final int SUN_WHIP                            = WEP_TIER10+5;
    public static final int FLOWER_SHIELD                       = WEP_TIER10+6;
    public static final int DREAM_SCYTHE                        = WEP_TIER10+7;
    public static final int SORROW_HELBARD                      = WEP_TIER10+8;
    public static final int ICE_FLAIL                           = WEP_TIER10+9;
    public static final int STUDDED_CANE                        = WEP_TIER10+10;
    public static final int SEED_CLUB                           = WEP_TIER10+11;
    public static final int GRASS_WHIP                          = WEP_TIER10+12;
    public static final int DEW_CANE                            = WEP_TIER10+13;
    public static final int BLIND_HAMMER                        = WEP_TIER10+14;
    public static final int ROT_SPEAR                           = WEP_TIER10+15;
    static {
        assignItemRect(GOO_SWORD,                               15, 15);
        assignItemRect(GOO_AXE,                                 16, 16);
        assignItemRect(GOO_SPEAR,                               16, 16);
        assignItemRect(GOO_SHIELD,                              13, 16);
        assignItemRect(FIREBLOOM_MACE,                          16, 16);
        assignItemRect(SUN_WHIP,                                16, 15);
        assignItemRect(FLOWER_SHIELD,                           16, 16);
        assignItemRect(DREAM_SCYTHE,                            13, 15);
        assignItemRect(SORROW_HELBARD,                          16, 16);
        assignItemRect(ICE_FLAIL,                               14, 14);
        assignItemRect(STUDDED_CANE,                            15, 16);
        assignItemRect(SEED_CLUB,                               16, 16);
        assignItemRect(GRASS_WHIP,                              16, 15);
        assignItemRect(DEW_CANE,                                16, 16);
        assignItemRect(BLIND_HAMMER,                            16, 15);
        assignItemRect(ROT_SPEAR,                               16, 16);
    }

	                                                                                    //8 free slots

	private static final int MISSILE_WEP    =                               xy(1, 17);  //16 slots. 3 per tier + boomerang
	public static final int SPIRIT_BOW      = MISSILE_WEP+0;
	
	public static final int DART            = MISSILE_WEP+1;
	public static final int THROWING_KNIFE  = MISSILE_WEP+2;
	public static final int THROWING_STONE  = MISSILE_WEP+3;
	
	public static final int FISHING_SPEAR   = MISSILE_WEP+4;
	public static final int SHURIKEN        = MISSILE_WEP+5;
	public static final int THROWING_CLUB   = MISSILE_WEP+6;
	
	public static final int THROWING_SPEAR  = MISSILE_WEP+7;
	public static final int BOLAS           = MISSILE_WEP+8;
	public static final int KUNAI           = MISSILE_WEP+9;
	
	public static final int JAVELIN         = MISSILE_WEP+10;
	public static final int TOMAHAWK        = MISSILE_WEP+11;
	public static final int BOOMERANG       = MISSILE_WEP+12;
	
	public static final int TRIDENT         = MISSILE_WEP+13;
	public static final int THROWING_HAMMER = MISSILE_WEP+14;
	public static final int FORCE_CUBE      = MISSILE_WEP+15;
	
	static{
		assignItemRect(SPIRIT_BOW,      16, 16);
		
		assignItemRect(DART,            15, 15);
		assignItemRect(THROWING_KNIFE,  12, 13);
		assignItemRect(THROWING_STONE,  12, 10);
		
		assignItemRect(FISHING_SPEAR,   11, 11);
		assignItemRect(SHURIKEN,        12, 12);
		assignItemRect(THROWING_CLUB,   12, 12);
		
		assignItemRect(THROWING_SPEAR,  13, 13);
		assignItemRect(BOLAS,           15, 14);
		assignItemRect(KUNAI,           15, 15);
		
		assignItemRect(JAVELIN,         16, 16);
		assignItemRect(TOMAHAWK,        13, 13);
		assignItemRect(BOOMERANG,       14, 14);
		
		assignItemRect(TRIDENT,         16, 16);
		assignItemRect(THROWING_HAMMER, 12, 12);
		assignItemRect(FORCE_CUBE,      11, 12);
	}
	
	public static final int TIPPED_DARTS    =                               xy(1, 18);  //16 slots
	public static final int ROT_DART        = TIPPED_DARTS+0;
	public static final int INCENDIARY_DART = TIPPED_DARTS+1;
	public static final int ADRENALINE_DART = TIPPED_DARTS+2;
	public static final int HEALING_DART    = TIPPED_DARTS+3;
	public static final int CHILLING_DART   = TIPPED_DARTS+4;
	public static final int SHOCKING_DART   = TIPPED_DARTS+5;
	public static final int POISON_DART     = TIPPED_DARTS+6;
	public static final int SLEEP_DART      = TIPPED_DARTS+7;
	public static final int PARALYTIC_DART  = TIPPED_DARTS+8;
	public static final int HOLY_DART       = TIPPED_DARTS+9;
	public static final int DISPLACING_DART = TIPPED_DARTS+10;
	public static final int BLINDING_DART   = TIPPED_DARTS+11;
    public static final int DART_HONEY      = TIPPED_DARTS+12;
    public static final int DART_BALL       = TIPPED_DARTS+13;
    public static final int DART_COCO       = TIPPED_DARTS+14;
    public static final int DART_BEETLE     = TIPPED_DARTS+15;
	static {
		for (int i = TIPPED_DARTS; i < TIPPED_DARTS+16; i++)
			assignItemRect(i, 16, 16);
	}

    public static final int TIPPED_DARTS2    =                               xy(1, 19);  //16 slots
    public static final int SUN_DART        = TIPPED_DARTS2+0;
    public static final int TOMATO_DART     = TIPPED_DARTS2+1;
    public static final int FIREFOX_DART    = TIPPED_DARTS2+2;
    public static final int PUSHING_DART    = TIPPED_DARTS2+3;
    public static final int DART_EARTHQUAKE = TIPPED_DARTS2+4;
    public static final int DART_DISEASE    = TIPPED_DARTS2+5;
    public static final int DART_STORM      = TIPPED_DARTS2+6;
    public static final int DART_DEW        = TIPPED_DARTS2+7;
    public static final int DART_SEEDCHAOS  = TIPPED_DARTS2+8;
    public static final int DART_TRACKING   = TIPPED_DARTS2+9;
    public static final int DART_SMOKING    = TIPPED_DARTS2+10;
    public static final int DART_TELEPORTING= TIPPED_DARTS2+11;
    public static final int DART_EGG        = TIPPED_DARTS2+12;
    public static final int DART_SUSAN      = TIPPED_DARTS2+13;
    public static final int DART_FEELER     = TIPPED_DARTS2+14;
    public static final int DART_TANK       = TIPPED_DARTS2+15;
    static {
        for (int i = TIPPED_DARTS2; i < TIPPED_DARTS2+16; i++)
            assignItemRect(i, 16, 16);
    }

    public static final int TIPPED_DARTS3    =                               xy(1, 20);  //16 slots
    public static final int DART_FREEZING   = TIPPED_DARTS3+0;
    public static final int DART_SLOWNESS   = TIPPED_DARTS3+1;
    public static final int DART_PARASITIC  = TIPPED_DARTS3+2;
    public static final int DART_SPICY      = TIPPED_DARTS3+3;
    public static final int DART_HEALTH     = TIPPED_DARTS3+4;
    public static final int DART_WITHER     = TIPPED_DARTS3+5;
    public static final int DART_HEAT       = TIPPED_DARTS3+6;
    public static final int DART_WATER      = TIPPED_DARTS3+7;
    public static final int DART_CHAOS      = TIPPED_DARTS3+8;
    public static final int DART_PEANUTMARK = TIPPED_DARTS3+9;
    public static final int DART_ROOTING    = TIPPED_DARTS3+10;
    public static final int DART_WRAITH     = TIPPED_DARTS3+11;
    public static final int DART_CONFUSING  = TIPPED_DARTS3+12;
    public static final int DART_CORRUPTION = TIPPED_DARTS3+13;
    public static final int DART_WHEAT      = TIPPED_DARTS3+14;
    static {
        for (int i = TIPPED_DARTS3; i < TIPPED_DARTS3+16; i++)
            assignItemRect(i, 16, 16);
    }

    public static final int TIPPED_DARTS4    =                               xy(1, 21);  //16 slots
    public static final int DART_LIGHTNING   = TIPPED_DARTS4+0;
    public static final int DART_GOO         = TIPPED_DARTS4+1;
    public static final int DART_POP         = TIPPED_DARTS4+2;
    public static final int DART_VETCH       = TIPPED_DARTS4+3;
    public static final int DART_CLIT        = TIPPED_DARTS4+4;
    public static final int DART_HYPNO       = TIPPED_DARTS4+5;
    public static final int DART_CLOCK       = TIPPED_DARTS4+6;
    public static final int DART_EYE         = TIPPED_DARTS4+7;
    public static final int DART_GRAPE       = TIPPED_DARTS4+8;
    public static final int DART_BANANA      = TIPPED_DARTS4+9;
    public static final int DART_DIRT        = TIPPED_DARTS4+10;
    public static final int DART_GRASS       = TIPPED_DARTS4+11;
    public static final int DART_LAMP        = TIPPED_DARTS4+12;
    public static final int DART_TREE        = TIPPED_DARTS4+13;
    public static final int DART_CROWN       = TIPPED_DARTS4+14;
    public static final int DART_SHADOW      = TIPPED_DARTS4+15;
    static {
        for (int i = TIPPED_DARTS4; i < TIPPED_DARTS4+16; i++)
            assignItemRect(i, 16, 16);
    }
	
	private static final int ARMOR          =                               xy(1, 22);  //16 slots
	public static final int ARMOR_CLOTH     = ARMOR+0;
	public static final int ARMOR_LEATHER   = ARMOR+1;
	public static final int ARMOR_MAIL      = ARMOR+2;
	public static final int ARMOR_SCALE     = ARMOR+3;
	public static final int ARMOR_PLATE     = ARMOR+4;
	public static final int ARMOR_WARRIOR   = ARMOR+5;
	public static final int ARMOR_MAGE      = ARMOR+6;
	public static final int ARMOR_ROGUE     = ARMOR+7;
	public static final int ARMOR_HUNTRESS  = ARMOR+8;
	static{
		assignItemRect(ARMOR_CLOTH,     15, 12);
		assignItemRect(ARMOR_LEATHER,   14, 13);
		assignItemRect(ARMOR_MAIL,      14, 12);
		assignItemRect(ARMOR_SCALE,     14, 11);
		assignItemRect(ARMOR_PLATE,     12, 12);
		assignItemRect(ARMOR_WARRIOR,   12, 12);
		assignItemRect(ARMOR_MAGE,      15, 15);
		assignItemRect(ARMOR_ROGUE,     14, 12);
		assignItemRect(ARMOR_HUNTRESS,  13, 15);
	}

	                                                                                    //16 free slots

	private static final int WANDS              =                           xy(1, 24);  //16 slots
	public static final int WAND_MAGIC_MISSILE  = WANDS+0;
	public static final int WAND_FIREBOLT       = WANDS+1;
	public static final int WAND_FROST          = WANDS+2;
	public static final int WAND_LIGHTNING      = WANDS+3;
	public static final int WAND_DISINTEGRATION = WANDS+4;
	public static final int WAND_PRISMATIC_LIGHT= WANDS+5;
	public static final int WAND_CORROSION      = WANDS+6;
	public static final int WAND_LIVING_EARTH   = WANDS+7;
	public static final int WAND_BLAST_WAVE     = WANDS+8;
	public static final int WAND_CORRUPTION     = WANDS+9;
	public static final int WAND_WARDING        = WANDS+10;
	public static final int WAND_REGROWTH       = WANDS+11;
	public static final int WAND_TRANSFUSION    = WANDS+12;
	static {
		for (int i = WANDS; i < WANDS+16; i++)
			assignItemRect(i, 14, 14);
	}

	private static final int RINGS          =                               xy(1, 25);  //16 slots
	public static final int RING_GARNET     = RINGS+0;
	public static final int RING_RUBY       = RINGS+1;
	public static final int RING_TOPAZ      = RINGS+2;
	public static final int RING_EMERALD    = RINGS+3;
	public static final int RING_ONYX       = RINGS+4;
	public static final int RING_OPAL       = RINGS+5;
	public static final int RING_TOURMALINE = RINGS+6;
	public static final int RING_SAPPHIRE   = RINGS+7;
	public static final int RING_AMETHYST   = RINGS+8;
	public static final int RING_QUARTZ     = RINGS+9;
	public static final int RING_AGATE      = RINGS+10;
	public static final int RING_DIAMOND    = RINGS+11;
	static {
		for (int i = RINGS; i < RINGS+16; i++)
			assignItemRect(i, 8, 10);
	}

	private static final int ARTIFACTS          =                            xy(1, 26);  //32 slots
	public static final int ARTIFACT_CLOAK      = ARTIFACTS+0;
	public static final int ARTIFACT_ARMBAND    = ARTIFACTS+1;
	public static final int ARTIFACT_CAPE       = ARTIFACTS+2;
	public static final int ARTIFACT_TALISMAN   = ARTIFACTS+3;
	public static final int ARTIFACT_HOURGLASS  = ARTIFACTS+4;
	public static final int ARTIFACT_TOOLKIT    = ARTIFACTS+5;
	public static final int ARTIFACT_SPELLBOOK  = ARTIFACTS+6;
	public static final int ARTIFACT_BEACON     = ARTIFACTS+7;
	public static final int ARTIFACT_CHAINS     = ARTIFACTS+8;
	public static final int ARTIFACT_HORN1      = ARTIFACTS+9;
	public static final int ARTIFACT_HORN2      = ARTIFACTS+10;
	public static final int ARTIFACT_HORN3      = ARTIFACTS+11;
	public static final int ARTIFACT_HORN4      = ARTIFACTS+12;
	public static final int ARTIFACT_CHALICE1   = ARTIFACTS+13;
	public static final int ARTIFACT_CHALICE2   = ARTIFACTS+14;
	public static final int ARTIFACT_CHALICE3   = ARTIFACTS+15;
	public static final int ARTIFACT_SANDALS    = ARTIFACTS+16;
	public static final int ARTIFACT_SHOES      = ARTIFACTS+17;
	public static final int ARTIFACT_BOOTS      = ARTIFACTS+18;
	public static final int ARTIFACT_GREAVES    = ARTIFACTS+19;
	public static final int ARTIFACT_ROSE1      = ARTIFACTS+20;
	public static final int ARTIFACT_ROSE2      = ARTIFACTS+21;
	public static final int ARTIFACT_ROSE3      = ARTIFACTS+22;
	static{
		assignItemRect(ARTIFACT_CLOAK,      9,  15);
		assignItemRect(ARTIFACT_ARMBAND,    16, 13);
		assignItemRect(ARTIFACT_CAPE,       16, 14);
		assignItemRect(ARTIFACT_TALISMAN,   15, 13);
		assignItemRect(ARTIFACT_HOURGLASS,  13, 16);
		assignItemRect(ARTIFACT_TOOLKIT,    15, 13);
		assignItemRect(ARTIFACT_SPELLBOOK,  13, 16);
		assignItemRect(ARTIFACT_BEACON,     16, 16);
		assignItemRect(ARTIFACT_CHAINS,     16, 16);
		assignItemRect(ARTIFACT_HORN1,      15, 15);
		assignItemRect(ARTIFACT_HORN2,      15, 15);
		assignItemRect(ARTIFACT_HORN3,      15, 15);
		assignItemRect(ARTIFACT_HORN4,      15, 15);
		assignItemRect(ARTIFACT_CHALICE1,   12, 15);
		assignItemRect(ARTIFACT_CHALICE2,   12, 15);
		assignItemRect(ARTIFACT_CHALICE3,   12, 15);
		assignItemRect(ARTIFACT_SANDALS,    16, 6 );
		assignItemRect(ARTIFACT_SHOES,      16, 6 );
		assignItemRect(ARTIFACT_BOOTS,      16, 9 );
		assignItemRect(ARTIFACT_GREAVES,    16, 14);
		assignItemRect(ARTIFACT_ROSE1,      14, 14);
		assignItemRect(ARTIFACT_ROSE2,      14, 14);
		assignItemRect(ARTIFACT_ROSE3,      14, 14);
	}

	                                                                                    //16 free slots

	private static final int SCROLLS        =                               xy(1, 29);  //16 slots
	public static final int SCROLL_KAUNAN   = SCROLLS+0;
	public static final int SCROLL_SOWILO   = SCROLLS+1;
	public static final int SCROLL_LAGUZ    = SCROLLS+2;
	public static final int SCROLL_YNGVI    = SCROLLS+3;
	public static final int SCROLL_GYFU     = SCROLLS+4;
	public static final int SCROLL_RAIDO    = SCROLLS+5;
	public static final int SCROLL_ISAZ     = SCROLLS+6;
	public static final int SCROLL_MANNAZ   = SCROLLS+7;
	public static final int SCROLL_NAUDIZ   = SCROLLS+8;
	public static final int SCROLL_BERKANAN = SCROLLS+9;
	public static final int SCROLL_ODAL     = SCROLLS+10;
	public static final int SCROLL_TIWAZ    = SCROLLS+11;
	
	public static final int SCROLL_CATALYST = SCROLLS+13;
	static {
		for (int i = SCROLLS; i < SCROLLS+16; i++)
			assignItemRect(i, 15, 14);
		assignItemRect(SCROLL_CATALYST, 12, 11);
	}
	
	private static final int EXOTIC_SCROLLS =                               xy(1, 30);  //16 slots
	public static final int EXOTIC_KAUNAN   = EXOTIC_SCROLLS+0;
	public static final int EXOTIC_SOWILO   = EXOTIC_SCROLLS+1;
	public static final int EXOTIC_LAGUZ    = EXOTIC_SCROLLS+2;
	public static final int EXOTIC_YNGVI    = EXOTIC_SCROLLS+3;
	public static final int EXOTIC_GYFU     = EXOTIC_SCROLLS+4;
	public static final int EXOTIC_RAIDO    = EXOTIC_SCROLLS+5;
	public static final int EXOTIC_ISAZ     = EXOTIC_SCROLLS+6;
	public static final int EXOTIC_MANNAZ   = EXOTIC_SCROLLS+7;
	public static final int EXOTIC_NAUDIZ   = EXOTIC_SCROLLS+8;
	public static final int EXOTIC_BERKANAN = EXOTIC_SCROLLS+9;
	public static final int EXOTIC_ODAL     = EXOTIC_SCROLLS+10;
	public static final int EXOTIC_TIWAZ    = EXOTIC_SCROLLS+11;
	static {
		for (int i = EXOTIC_SCROLLS; i < EXOTIC_SCROLLS+16; i++)
			assignItemRect(i, 15, 14);
	}
	
	private static final int STONES             =                           xy(1, 31);  //16 slots
	public static final int STONE_AGGRESSION    = STONES+0;
	public static final int STONE_AUGMENTATION  = STONES+1;
	public static final int STONE_AFFECTION     = STONES+2;
	public static final int STONE_BLAST         = STONES+3;
	public static final int STONE_BLINK         = STONES+4;
	public static final int STONE_CLAIRVOYANCE  = STONES+5;
	public static final int STONE_SLEEP         = STONES+6;
	public static final int STONE_DISARM        = STONES+7;
	public static final int STONE_ENCHANT       = STONES+8;
	public static final int STONE_FLOCK         = STONES+9;
	public static final int STONE_INTUITION     = STONES+10;
	public static final int STONE_SHOCK         = STONES+11;
	static {
		for (int i = STONES; i < STONES+16; i++)
			assignItemRect(i, 14, 12);
	}

	private static final int POTIONS        =                               xy(1, 32);  //16 slots
	public static final int POTION_CRIMSON          = POTIONS+0;
	public static final int POTION_AMBER            = POTIONS+1;
	public static final int POTION_GOLDEN           = POTIONS+2;
	public static final int POTION_JADE             = POTIONS+3;
	public static final int POTION_TURQUOISE        = POTIONS+4;
	public static final int POTION_AZURE            = POTIONS+5;
	public static final int POTION_INDIGO           = POTIONS+6;
	public static final int POTION_MAGENTA          = POTIONS+7;
	public static final int POTION_BISTRE           = POTIONS+8;
	public static final int POTION_CHARCOAL         = POTIONS+9;
	public static final int POTION_SILVER           = POTIONS+10;
	public static final int POTION_IVORY            = POTIONS+11;
    public static final int POTION_POISON_GREEN     = POTIONS+12;
    public static final int POTION_SNOW_WHITE       = POTIONS+13;
    public static final int POTION_MUDDY_GREEN      = POTIONS+14;
    public static final int POTION_MUDDY_YELLOW     = POTIONS+15;
	static {
		for (int i = POTIONS; i < POTIONS+16; i++)
			assignItemRect(i, 12, 14);
	}
	
	private static final int EXOTIC_POTIONS =                               xy(1, 33);  //16 slots
	public static final int EXOTIC_CRIMSON          = EXOTIC_POTIONS+0;
	public static final int EXOTIC_AMBER            = EXOTIC_POTIONS+1;
	public static final int EXOTIC_GOLDEN           = EXOTIC_POTIONS+2;
	public static final int EXOTIC_JADE             = EXOTIC_POTIONS+3;
	public static final int EXOTIC_TURQUOISE        = EXOTIC_POTIONS+4;
	public static final int EXOTIC_AZURE            = EXOTIC_POTIONS+5;
	public static final int EXOTIC_INDIGO           = EXOTIC_POTIONS+6;
	public static final int EXOTIC_MAGENTA          = EXOTIC_POTIONS+7;
	public static final int EXOTIC_BISTRE           = EXOTIC_POTIONS+8;
	public static final int EXOTIC_CHARCOAL         = EXOTIC_POTIONS+9;
	public static final int EXOTIC_SILVER           = EXOTIC_POTIONS+10;
	public static final int EXOTIC_IVORY            = EXOTIC_POTIONS+11;
    public static final int EXOTIC_POISON_GREEN     = EXOTIC_POTIONS+12;
    public static final int EXOTIC_SNOW_WHITE       = EXOTIC_POTIONS+13;
    public static final int EXOTIC_MUDDY_GREEN      = EXOTIC_POTIONS+14;
    public static final int EXOTIC_MUDDY_YELLOW     = EXOTIC_POTIONS+15;
	static {
		for (int i = EXOTIC_POTIONS; i < EXOTIC_POTIONS+16; i++)
			assignItemRect(i, 12, 13);
	}

	private static final int SEEDS              =                           xy(1, 34);  //16 slots
	public static final int SEED_ROTBERRY       = SEEDS+0;
	public static final int SEED_FIREBLOOM      = SEEDS+1;
	public static final int SEED_SWIFTTHISTLE   = SEEDS+2;
	public static final int SEED_SUNGRASS       = SEEDS+3;
	public static final int SEED_ICECAP         = SEEDS+4;
	public static final int SEED_STORMVINE      = SEEDS+5;
	public static final int SEED_SORROWMOSS     = SEEDS+6;
	public static final int SEED_DREAMFOIL      = SEEDS+7;
	public static final int SEED_EARTHROOT      = SEEDS+8;
	public static final int SEED_STARFLOWER     = SEEDS+9;
	public static final int SEED_FADELEAF       = SEEDS+10;
	public static final int SEED_BLINDWEED      = SEEDS+11;
    public static final int SEED_FEELERFERN     = SEEDS+12;
    public static final int SEED_LARVALEAVE     = SEEDS+13;
    public static final int SEED_TANKCABBAGE    = SEEDS+14;
    public static final int SEED_CORNWHEAT      = SEEDS+15;
	static{
		for (int i = SEEDS; i < SEEDS+16; i++)
			assignItemRect(i, 10, 10);
	}

    private static final int POTIONS2        =                               xy(1, 35);  //16 slots
    public static final int POTION_HONEY            = POTIONS2+0;
    public static final int POTION_BLOODY           = POTIONS2+1;
    public static final int POTION_ORANGE           = POTIONS2+2;
    public static final int POTION_VIOLETT          = POTIONS2+3;
    public static final int POTION_YELLOW           = POTIONS2+4;
    public static final int POTION_WHITE            = POTIONS2+5;
    public static final int POTION_BROWN            = POTIONS2+6;
    public static final int POTION_BRIGHT_BLUE      = POTIONS2+7;
    public static final int POTION_RAINBOW          = POTIONS2+8;
    public static final int POTION_BRIGHT_ORANGE    = POTIONS2+9;
    public static final int POTION_DARK_BLUE        = POTIONS2+10;
    public static final int POTION_BLACK            = POTIONS2+11;
    public static final int POTION_YELLOW_ORANGE    = POTIONS2+12;
    public static final int POTION_GRASS_GREEN      = POTIONS2+13;
    public static final int POTION_SKY_BLUE         = POTIONS2+14;
    public static final int POTION_GREEN_BLUE       = POTIONS2+15;
    static {
        for (int i = POTIONS2; i < POTIONS2+16; i++)
            assignItemRect(i, 12, 14);
    }

    private static final int EXOTIC_POTIONS2 =                               xy(1, 36);  //16 slots
    public static final int EXOTIC_HONEY            = EXOTIC_POTIONS2+0;
    public static final int EXOTIC_BLOODY           = EXOTIC_POTIONS2+1;
    public static final int EXOTIC_ORANGE           = EXOTIC_POTIONS2+2;
    public static final int EXOTIC_VIOLETT          = EXOTIC_POTIONS2+3;
    public static final int EXOTIC_YELLOW           = EXOTIC_POTIONS2+4;
    public static final int EXOTIC_WHITE            = EXOTIC_POTIONS2+5;
    public static final int EXOTIC_BROWN            = EXOTIC_POTIONS2+6;
    public static final int EXOTIC_BRIGHTBLUE       = EXOTIC_POTIONS2+7;
    public static final int EXOTIC_RAINBOW          = EXOTIC_POTIONS2+8;
    public static final int EXOTIC_BRIGHTORANGE     = EXOTIC_POTIONS2+9;
    public static final int EXOTIC_DARKBLUE         = EXOTIC_POTIONS2+10;
    public static final int EXOTIC_BLACK            = EXOTIC_POTIONS2+11;
    public static final int EXOTIC_YELLOW_ORANGE    = EXOTIC_POTIONS2+12;
    public static final int EXOTIC_GRASS_GREEN      = EXOTIC_POTIONS2+13;
    public static final int EXOTIC_SKY_BLUE         = EXOTIC_POTIONS2+14;
    public static final int EXOTIC_GREEN_BLUE       = EXOTIC_POTIONS2+15;
    static {
        for (int i = EXOTIC_POTIONS2; i < EXOTIC_POTIONS2+16; i++)
            assignItemRect(i, 12, 13);
    }

    private static final int SEEDS2              =                           xy(1, 37);  //16 slots
    public static final int SEED_SUNBLOOM       = SEEDS2+0;
    public static final int SEED_TOMATOBUSH     = SEEDS2+1;
    public static final int SEED_FIREFOXGLOVE   = SEEDS2+2;
    public static final int SEED_MUSCLEMOSS     = SEEDS2+3;
    public static final int SEED_BUTTERLION     = SEEDS2 +4;
    public static final int SEED_SNOWHEDGE      = SEEDS2 +5;
    public static final int SEED_STEAMWEED      = SEEDS2 +6;
    public static final int SEED_DEWCATCHER     = SEEDS2 +7;
    public static final int SEED_SEEDPOD        = SEEDS2 +8;
    public static final int SEED_CHANDALIERTAIL = SEEDS2 +9;
    public static final int SEED_NIGHTSHADEONION= SEEDS2 +10;
    public static final int SEED_BLACKHOLEFLOWER= SEEDS2 +11;
    public static final int SEED_COMBFLOWER     = SEEDS2 +12;
    public static final int SEED_BALLCROP       = SEEDS2 +13;
    public static final int SEED_BLUEEYEDSUSAN  = SEEDS2 +14;
    public static final int SEED_COCOSTUFT      = SEEDS2 +15;
    static{
        for (int i = SEEDS2; i < SEEDS2+16; i++)
            assignItemRect(i, 10, 10);
    }

    private static final int POTIONS3        =                               xy(1, 38);  //16 slots
    public static final int POTION_BLUE             = POTIONS3+0;
    public static final int POTION_FLAT_BLUE        = POTIONS3+1;
    public static final int POTION_PARASITIC        = POTIONS3+2;
    public static final int POTION_DIARRHOEA        = POTIONS3+3;
    public static final int POTION_PUNCH            = POTIONS3+4;
    public static final int POTION_BEIGE            = POTIONS3+5;
    public static final int POTION_BLOODY_POOP      = POTIONS3+6;
    public static final int POTION_WATER_BLUE       = POTIONS3+7;
    public static final int POTION_BRIGHT_GREEN     = POTIONS3+8;
    public static final int POTION_INDIGO_PURPLE    = POTIONS3+9;
    public static final int POTION_LIME_GREEN       = POTIONS3+10;
    public static final int POTION_ROSE             = POTIONS3+11;
    public static final int POTION_BRIGHT_PURPLE    = POTIONS3+12;
    public static final int POTION_DARK_ROSE        = POTIONS3+13;
    public static final int POTION_CORN_YELLOW      = POTIONS3+14;
    public static final int POTION_DARK_PURPLE      = POTIONS3+15;
    static {
        for (int i = POTIONS3; i < POTIONS3+16; i++)
            assignItemRect(i, 12, 14);
    }

    private static final int EXOTIC_POTIONS3 =                               xy(1, 39);  //16 slots
    public static final int EXOTIC_BLUE             = EXOTIC_POTIONS3+0;
    public static final int EXOTIC_FLATBLUE         = EXOTIC_POTIONS3+1;
    public static final int EXOTIC_PARASITIC        = EXOTIC_POTIONS3+2;
    public static final int EXOTIC_DIARRHOEA        = EXOTIC_POTIONS3+3;
    public static final int EXOTIC_PUNCH            = EXOTIC_POTIONS3+4;
    public static final int EXOTIC_BEIGE            = EXOTIC_POTIONS3+5;
    public static final int EXOTIC_BLOODY_POOP      = EXOTIC_POTIONS3+6;
    public static final int EXOTIC_WATERBLUE        = EXOTIC_POTIONS3+7;
    public static final int EXOTIC_BRIGHT_GREEN     = EXOTIC_POTIONS3+8;
    public static final int EXOTIC_INDIGO_PURPLE    = EXOTIC_POTIONS3+9;
    public static final int EXOTIC_LIMEGREEN        = EXOTIC_POTIONS3+10;
    public static final int EXOTIC_ROSE             = EXOTIC_POTIONS3+11;
    public static final int EXOTIC_BRIGHT_PURPLE    = EXOTIC_POTIONS3+12;
    public static final int EXOTIC_DARK_ROSE        = EXOTIC_POTIONS3+13;
    public static final int EXOTIC_DARK_PURPLE      = EXOTIC_POTIONS3+15;
    static {
        for (int i = EXOTIC_POTIONS3; i < EXOTIC_POTIONS3+16; i++)
            assignItemRect(i, 12, 13);
    }

    private static final int SEEDS3              =                           xy(1, 40);  //16 slots
    public static final int SEED_FROSTCORN       = SEEDS3 +0;
    public static final int SEED_WILLOWCANE      = SEEDS3 +1;
    public static final int SEED_PARASITESHRUB   = SEEDS3 +2;
    public static final int SEED_CRIMSONPEPPER   = SEEDS3 +3;
    public static final int SEED_APRICOBUSH      = SEEDS3 +4;
    public static final int SEED_WITHERFENNEL    = SEEDS3 +5;
    public static final int SEED_CHILLISNAPPER   = SEEDS3 +6;
    public static final int SEED_WATERWEED       = SEEDS3 +7;
    public static final int SEED_GRASSLILLY      = SEEDS3 +8;
    public static final int SEED_PEANUTPETAL     = SEEDS3 +9;
    public static final int SEED_KIWIVETCH       = SEEDS3 +10;
    public static final int SEED_ROSE            = SEEDS3 +11;
    public static final int SEED_VENUSFLYTRAP    = SEEDS3 +12;
    public static final int SEED_SUNCARNIVORE    = SEEDS3 +13;
    public static final int SEED_EGGBLOOM        = SEEDS3 +15;
    static{
        for (int i = SEEDS3; i < SEEDS3+16; i++)
            assignItemRect(i, 10, 10);
    }

    private static final int POTIONS4        =                               xy(1, 41);  //16 slots
    public static final int POTION_LIGHTNING_BLUE               = POTIONS4+0;
    public static final int POTION_VINE_RED                     = POTIONS4+1;
    public static final int POTION_PURE_WHITE                   = POTIONS4+2;
    public static final int POTION_LIGHT_LIME_GREEN             = POTIONS4+3;
    public static final int POTION_PALE_PURPLE                  = POTIONS4+4;
    public static final int POTION_LIGHT_VIOLETT                = POTIONS4+5;
    public static final int POTION_BLACK_WHITE                  = POTIONS4+6;
    public static final int POTION_WHITE_BLACK                  = POTIONS4+7;
    public static final int POTION_PALE_VIOLETT                 = POTIONS4+8;
    public static final int POTION_BROWN_GREEN                  = POTIONS4+9;
    public static final int POTION_DARK_BROWN_GREEN             = POTIONS4+10;
    public static final int POTION_MOSSY_GREEN                  = POTIONS4+11;
    public static final int POTION_PURPLE_VIOLETT               = POTIONS4+12;
    public static final int POTION_LIGHT_BLUE_GREEN             = POTIONS4+13;
    public static final int POTION_LIGHT_ROSE                   = POTIONS4+14;
    public static final int POTION_YELLOW_PURPLE                = POTIONS4+15;
    static {
        for (int i = POTIONS4; i < POTIONS4+16; i++)
            assignItemRect(i, 12, 14);
    }

    private static final int EXOTIC_POTIONS4 =                               xy(1, 42);  //16 slots
    public static final int EXOTIC_LIGHTNING_BLUE             = EXOTIC_POTIONS4+0;
    public static final int EXOTIC_DARK_BLOODY                  = EXOTIC_POTIONS4+1;
    public static final int EXOTIC_PURE_WHITE                   = EXOTIC_POTIONS4+2;
    public static final int EXOTIC_LIME_GREEN                   = EXOTIC_POTIONS4+3;
    public static final int EXOTIC_PALE_PURPLE                  = EXOTIC_POTIONS4+4;
    public static final int EXOTIC_LIGHT_VIOLETT                = EXOTIC_POTIONS4+5;
    public static final int EXOTIC_BLACK_WHITE                  = EXOTIC_POTIONS4+6;
    public static final int EXOTIC_WHITE_BLACK                  = EXOTIC_POTIONS4+7;
    public static final int EXOTIC_PALE_VIOLETT                 = EXOTIC_POTIONS4+8;
    public static final int EXOTIC_BROWN_GREEN                  = EXOTIC_POTIONS4+9;
    public static final int EXOTIC_DARK_BROWN_GREEN             = EXOTIC_POTIONS4+10;
    public static final int EXOTIC_MOSSY_GREEN                  = EXOTIC_POTIONS4+11;
    public static final int EXOTIC_PURPLE_VIOLETT               = EXOTIC_POTIONS4+12;
    public static final int EXOTIC_LIGHT_BLUE_GREEN             = EXOTIC_POTIONS4+13;
    public static final int EXOTIC_LIGHT_ROSE                   = EXOTIC_POTIONS4+14;
    public static final int EXOTIC_YELLOW_PURPLE                = EXOTIC_POTIONS4+15;
    static {
        for (int i = EXOTIC_POTIONS4; i < EXOTIC_POTIONS4+16; i++)
            assignItemRect(i, 12, 13);
    }

    private static final int SEEDS4              =                           xy(1, 43);  //16 slots
    public static final int SEED_LIGHTNINGLILY       = SEEDS4 +0;
    public static final int SEED_GOOGRASS            = SEEDS4 +1;
    public static final int SEED_POPPOPLAR           = SEEDS4 +2;
    public static final int SEED_SOURPITCHER         = SEEDS4 +3;
    public static final int SEED_CLITBALM            = SEEDS4 +4;
    public static final int SEED_HYPNOHEMP           = SEEDS4 +5;
    public static final int SEED_CLOCKCYPRESS        = SEEDS4 +6;
    public static final int SEED_EYEEUNYMUS          = SEEDS4 +7;
    public static final int SEED_GOBGRAPE            = SEEDS4 +8;
    public static final int SEED_BANANABEAN          = SEEDS4 +9;
    public static final int SEED_DIRTDAISY           = SEEDS4 +10;
    public static final int SEED_GRASSVINE           = SEEDS4 +11;
    public static final int SEED_LAVENDERLANTERN     = SEEDS4 +12;
    public static final int SEED_FLOWERTREE          = SEEDS4 +13;
    public static final int SEED_CRIMSONCROWN        = SEEDS4 +14;
    public static final int SEED_SHADOWBLOOM         = SEEDS4 +15;
    static{
        for (int i = SEEDS4; i < SEEDS4+16; i++)
            assignItemRect(i, 10, 10);
    }
	
	private static final int BREWS          =                               xy(1, 44);  //8 slots
	public static final int BREW_INFERNAL   = BREWS+0;
	public static final int BREW_BLIZZARD   = BREWS+1;
	public static final int BREW_SHOCKING   = BREWS+2;
	public static final int BREW_CAUSTIC    = BREWS+3;
	
	private static final int ELIXIRS        =                               xy(9, 44);  //8 slots
	public static final int ELIXIR_HONEY    = ELIXIRS+0;
	public static final int ELIXIR_AQUA     = ELIXIRS+1;
	public static final int ELIXIR_MIGHT    = ELIXIRS+2;
	public static final int ELIXIR_DRAGON   = ELIXIRS+3;
	public static final int ELIXIR_TOXIC    = ELIXIRS+4;
	public static final int ELIXIR_ICY      = ELIXIRS+5;
	public static final int ELIXIR_ARCANE   = ELIXIRS+6;
	static{
		for (int i = BREWS; i < BREWS+16; i++)
			assignItemRect(i, 12, 14);
	}
	
	                                                                                    //16 free slots

    private static final int SPELLS         =                               xy(1, 47);  //16 slots
    public static final int PHASE_SHIFT     = SPELLS+0;
    public static final int AQUA_BLAST      = SPELLS+1;
    public static final int MAGIC_INFUSE    = SPELLS+2;
    public static final int MAGIC_PORTER    = SPELLS+3;
    public static final int RETURN_BEACON   = SPELLS+4;
    public static final int FEATHER_FALL    = SPELLS+5;
    public static final int CURSE_INFUSE    = SPELLS+6;
    public static final int RECLAIM_TRAP    = SPELLS+7;
    public static final int ALCHEMIZE       = SPELLS+8;
    public static final int RECYCLE         = SPELLS+9;
    public static final int ENCHANT_INFUSE  = SPELLS+10;
    public static final int CRIMSON_EPITHET = SPELLS+11;
    public static final int FORCEFIELD      = SPELLS+12;
    public static final int HOLYBLAST       = SPELLS+13;
    public static final int DOOMCALL        = SPELLS+14;
    public static final int SEASONCHANGE    = SPELLS+15;
    static{
        assignItemRect(PHASE_SHIFT,     12, 11);
        assignItemRect(AQUA_BLAST,      11, 11);
        assignItemRect(MAGIC_INFUSE,    10, 15);
        assignItemRect(MAGIC_PORTER,    12, 11);
        assignItemRect(RETURN_BEACON,    8, 16);
        assignItemRect(FEATHER_FALL,    11, 11);
        assignItemRect(CURSE_INFUSE,    10, 15);
        assignItemRect(RECLAIM_TRAP,    11, 11);
        assignItemRect(ALCHEMIZE,       10, 15);
        assignItemRect(RECYCLE,         10, 15);
        assignItemRect(ENCHANT_INFUSE,  12, 11);
        assignItemRect(CRIMSON_EPITHET, 11, 11);
        assignItemRect(FORCEFIELD,      10, 15);
        assignItemRect(HOLYBLAST,       12, 11);
        assignItemRect(DOOMCALL,         8, 16);
        assignItemRect(SEASONCHANGE,    11, 11);
    }

    private static final int SPELLS_ROW2            =                               xy(1, 48);  //16 slots
    public static final int PLANT_SUMMON            = SPELLS_ROW2+0;
    public static final int NATURES_LULLABY         = SPELLS_ROW2+1;
    public static final int SPONTANEOUS_COMBUSTION  = SPELLS_ROW2+2;
    public static final int FORCE_PUSH              = SPELLS_ROW2+3;
    public static final int WILD_ENEGRY             = SPELLS_ROW2+4;
    static{
        assignItemRect(PLANT_SUMMON,            10, 15);
        assignItemRect(NATURES_LULLABY,         11, 11);
        assignItemRect(SPONTANEOUS_COMBUSTION,   8, 16);
        assignItemRect(FORCE_PUSH,               8, 16);
        assignItemRect(WILD_ENEGRY,               8, 16);
    }
	
	private static final int FOOD       =                                   xy(1, 49);  //16 slots
	public static final int MEAT                = FOOD+0;
	public static final int STEAK               = FOOD+1;
	public static final int STEWED              = FOOD+2;
	public static final int OVERPRICED          = FOOD+3;
	public static final int CARPACCIO           = FOOD+4;
	public static final int RATION              = FOOD+5;
	public static final int PASTY               = FOOD+6;
	public static final int PUMPKIN_PIE         = FOOD+7;
	public static final int CANDY_CANE          = FOOD+8;
	public static final int MEAT_PIE            = FOOD+9;
	public static final int BLANDFRUIT          = FOOD+10;
	public static final int BLAND_CHUNKS        = FOOD+11;
    public static final int PEANUT              = FOOD+12;
    public static final int APRICO              = FOOD+13;
    public static final int CORNWHEATSHAFT      = FOOD+14;
    public static final int EGG                 = FOOD+15;
	static{
		assignItemRect(MEAT,            15, 11);
		assignItemRect(STEAK,           15, 11);
		assignItemRect(STEWED,          15, 11);
		assignItemRect(OVERPRICED,      14, 11);
		assignItemRect(CARPACCIO,       15, 11);
		assignItemRect(RATION,          16, 12);
		assignItemRect(PASTY,           16, 11);
		assignItemRect(PUMPKIN_PIE,     16, 12);
		assignItemRect(CANDY_CANE,      13, 16);
		assignItemRect(MEAT_PIE,        16, 12);
		assignItemRect(BLANDFRUIT,      9,  12);
		assignItemRect(BLAND_CHUNKS,    14, 6);
        assignItemRect(PEANUT,          8,  9);
        assignItemRect(APRICO,          11, 11);
        assignItemRect(CORNWHEATSHAFT,  9,  9);
        assignItemRect(EGG,             11, 13);
	}

    private static final int FOOD2       =                                   xy(1, 50);  //16 slots
    public static final int GRAPE                = FOOD2+0;
    public static final int STRAWBERRYWHEAT      = FOOD2+1;
    public static final int WATERWHEAT           = FOOD2+2;
    public static final int GREENAPPLEWHEAT      = FOOD2+3;
    public static final int WHEAT                = FOOD2+4;
    static{
        assignItemRect(GRAPE,            9, 9);
        assignItemRect(STRAWBERRYWHEAT,  9, 9);
        assignItemRect(WATERWHEAT,       9, 9);
        assignItemRect(GREENAPPLEWHEAT,  9, 9);
        assignItemRect(WHEAT,            9, 9);
    }

	private static final int QUEST  =                                       xy(1, 51);  //32 slots
	public static final int SKULL           = QUEST+0;
	public static final int DUST            = QUEST+1;
	public static final int CANDLE          = QUEST+2;
	public static final int EMBER           = QUEST+3;
	public static final int PICKAXE         = QUEST+4;
	public static final int ORE             = QUEST+5;
	public static final int TOKEN           = QUEST+6;
	public static final int BLOB            = QUEST+7;
	public static final int SHARD           = QUEST+8;
    public static final int POTION_CATALYST = QUEST+9;
	static{
		assignItemRect(SKULL,           16, 11);
		assignItemRect(DUST,            12, 11);
		assignItemRect(CANDLE,          12, 12);
		assignItemRect(EMBER,           12, 11);
		assignItemRect(PICKAXE,         14, 14);
		assignItemRect(ORE,             15, 15);
		assignItemRect(TOKEN,           12, 12);
		assignItemRect(BLOB,            10, 9);
		assignItemRect(SHARD,           8,  10);
        assignItemRect(SHARD,           8,  10);
        assignItemRect(POTION_CATALYST, 6,  15);
	}

	private static final int BAGS       =                                   xy(1, 53);  //16 slots
	public static final int VIAL        = BAGS+0;
	public static final int POUCH       = BAGS+1;
	public static final int HOLDER      = BAGS+2;
	public static final int BANDOLIER   = BAGS+3;
	public static final int HOLSTER     = BAGS+4;
	static{
		assignItemRect(VIAL,        12, 12);
		assignItemRect(POUCH,       14, 15);
		assignItemRect(HOLDER,      16, 16);
		assignItemRect(BANDOLIER,   15, 16);
		assignItemRect(HOLSTER,     15, 16);
	}

    private static final int BOOKS        =                               xy(1, 54);  //16 slots
    public static final int BOOK_BLUE_GREEN        = BOOKS+0;
    public static final int BOOK_DARK_RED          = BOOKS+1;
    public static final int BOOK_PURPLE            = BOOKS+2;
    public static final int BOOK_PALE_BLUE         = BOOKS+3;
    public static final int BOOK_DARK_BLUE         = BOOKS+4;
    public static final int BOOK_YELLOW_GREEN      = BOOKS+5;
    public static final int BOOK_BRIGHT_RED        = BOOKS+6;
    public static final int BOOK_BRIGHT_GREEN      = BOOKS+7;
    public static final int BOOK_BRIGHT_DARK_BLUE  = BOOKS+8;
    public static final int BOOK_RED_PURPLE        = BOOKS+9;
    public static final int BOOK_BLUE              = BOOKS+10;
    public static final int BOOK_BROWN             = BOOKS+11;
    public static final int BOOK_BRIGHT_BROWN      = BOOKS+12;
    public static final int BOOK_BLACK             = BOOKS+13;
    public static final int BOOK_BRIGHT_BLUE_GREEN = BOOKS+14;
    public static final int BOOK_DARK_BRIGHT_BLUE  = BOOKS+15;
    static {
        for (int i = BOOKS; i < BOOKS+16; i++)
            assignItemRect(i, 13, 16);;
    }

	                                                                                    //16 free slots


	private static void assignItemRect( int item, int width, int height){
		int x = (item % WIDTH) * WIDTH;
		int y = (item / WIDTH) * WIDTH;
		film.add( item, x, y, x+width, y+height);
	}

}
