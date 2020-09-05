package me.medievaljob.utils;

import org.bukkit.Material;

public class BlockType {

    public static boolean isRockBlock(Material block){
        switch (block){
            case COAL_ORE:
            case IRON_ORE:
            case REDSTONE_ORE:
            case LAPIS_ORE:
            case GOLD_ORE:
            case EMERALD_ORE:
            case DIAMOND_ORE:
                return true;
            default:
                return false;
        }
    }

    public static boolean isPlantBlock(Material block){
        switch (block){
            case CROPS:
            case SUGAR_CANE_BLOCK:
            case PUMPKIN:
            case MELON_BLOCK:
            case CARROT:
            case POTATO:
                return true;
            default:
                return false;
        }
    }

    public static boolean isWoodBlock(Material block){
        switch (block){
            case LOG:
                return true;
            default:
                return false;
        }
    }

    public static boolean isSpecialBlock(Material block){
        switch (block){
            case LOG:
            case CROPS:
            case SUGAR_CANE_BLOCK:
            case PUMPKIN:
            case MELON_BLOCK:
            case CARROT:
            case POTATO:
            case COAL_ORE:
            case IRON_ORE:
            case REDSTONE_ORE:
            case LAPIS_ORE:
            case GOLD_ORE:
            case EMERALD_ORE:
            case DIAMOND_ORE:
                return true;
            default:
                return false;
        }
    }
}
