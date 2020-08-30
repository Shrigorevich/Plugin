package me.medievaljob.utils;

import org.bukkit.Material;

public class BlockType {

    public static boolean isRockBlock(Material block){
        switch (block){
            case COAL_ORE:
                return true;
            case IRON_ORE:
            case REDSTONE_ORE:
            case LAPIS_ORE:
            case GOLD_ORE:
            case EMERALD_ORE:
            case DIAMOND_ORE:
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
            case WOOD:
            case LOG:
                return true;
            default:
                return false;
        }
    }
}
