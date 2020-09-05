package me.medievaljob.jobs;

import me.medievaljob.utils.Config;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Breeder extends Job {
    public Breeder(String name) {
        super(name);
    }

    public Breeder(String name, int level, int progress, boolean active) {
        super(name, level, progress, active);
    }

    public void customDrop(Entity entity, List<ItemStack> drops){
        int randomPercent = (int) (Math.random() * 100);
        System.out.println("Chance: " + randomPercent);

        int extraDrop = 0;
        int extraSeeds = 0;
        int chance = getLevel() * Config.getInt("CHANCE_PER_LVL");
        if(0 <= randomPercent && randomPercent <= chance){
            System.out.println("+ Extra drop, with chance: " + chance );
            extraDrop+=getLevel() / 2;
        }
        if (randomPercent < Config.getInt("EXTRA_SEEDS_CHANCE")){
            System.out.println("Extra seeds");
            extraSeeds+=1;
        }
        drops.clear();
        World world = entity.getWorld();

        switch (entity.getType()){
            case COW:
                world.dropItem(entity.getLocation(), new ItemStack(Material.RAW_BEEF, 1 + extraDrop));
                world.dropItem(entity.getLocation(), new ItemStack(Material.LEATHER, 1 + extraSeeds));
                break;
            case SHEEP:
                world.dropItem(entity.getLocation(), new ItemStack(Material.RAW_BEEF, 1 + extraDrop));
                world.dropItem(entity.getLocation(), new ItemStack(Material.WOOL, 1 + extraSeeds));
                break;
            case PIG:
                world.dropItem(entity.getLocation(), new ItemStack(Material.PORK, 1 + extraDrop + extraSeeds));
                break;
            case CHICKEN:
                world.dropItem(entity.getLocation(), new ItemStack(Material.RAW_CHICKEN, 1));
                world.dropItem(entity.getLocation(), new ItemStack(Material.FEATHER, 1 + extraDrop));
                break;
            case RABBIT:
                world.dropItem(entity.getLocation(), new ItemStack(Material.RABBIT, 1));
                break;
            default:
                break;
        }
    }

    public void defaultDrop(Entity entity, List<ItemStack> drops){

        int randomPercent = (int) (Math.random() * 100);
        System.out.println("Default drop");
        System.out.println("Chance: " + randomPercent + "Breeder Drop: " + drops);

        int extraDrop = 0;
        int extraSeeds = 0;

        if(randomPercent <= 50){
            System.out.println("+ Extra drop, with chance: " + randomPercent);
            extraDrop += 1;
        }
        if (randomPercent < Config.getInt("EXTRA_SEEDS_CHANCE")){
            System.out.println("Extra seeds");
            extraSeeds+=1;
        }
        drops.clear();
        World world =  entity.getWorld();
        switch (entity.getType()){
            case COW:
                world.dropItem(entity.getLocation(), new ItemStack(Material.RAW_BEEF, extraDrop ));
                world.dropItem(entity.getLocation(), new ItemStack(Material.LEATHER, extraSeeds ));
                break;
            case SHEEP:
                world.dropItem(entity.getLocation(), new ItemStack(Material.RAW_BEEF, extraDrop ));
                world.dropItem(entity.getLocation(), new ItemStack(Material.WOOL, extraSeeds));
                break;
            case PIG:
                world.dropItem(entity.getLocation(), new ItemStack(Material.PORK, extraDrop ));
                break;
            case CHICKEN:
                world.dropItem(entity.getLocation(), new ItemStack(Material.RAW_CHICKEN, extraDrop ));
                world.dropItem(entity.getLocation(), new ItemStack(Material.FEATHER, extraSeeds));
                break;
            case RABBIT:
                world.dropItem(entity.getLocation(), new ItemStack(Material.RABBIT, extraDrop ));
                break;
            default:
                break;
        }
    }
}
