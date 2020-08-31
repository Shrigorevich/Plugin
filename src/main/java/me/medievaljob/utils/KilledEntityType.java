package me.medievaljob.utils;
import org.bukkit.entity.EntityType;

public class KilledEntityType {

    public static boolean isPeacefulEntity(EntityType entity){
        switch (entity){
            case COW:
            case PIG:
            case SHEEP:
            case CHICKEN:
            case HORSE:
                return true;
            default:
                return false;
        }
    }

    public static boolean isHostileEntity(EntityType entity){
        switch (entity){
            case CREEPER:
            case ZOMBIE:
            case SKELETON:
            case WITCH:
            case PLAYER:
            case SPIDER:
            case CAVE_SPIDER:
            case ENDERMAN:
                return true;
            default:
                return false;
        }
    }
}
