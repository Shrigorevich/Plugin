package me.medievaljob.jobs;

import me.medievaljob.utils.Config;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Farmer extends Job {

    public Farmer(String name) {
        super(name);
    }

    public Farmer(String name, int level, int progress, boolean active){
        super(name, level, progress, active);
    }

    public void customDrop(Block block){
        //List<ItemStack> drops = new ArrayList<>(block.getDrops());
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

        World world = block.getWorld();

        switch (block.getType()){
            case CROPS:
                world.dropItem(block.getLocation(), new ItemStack(Material.WHEAT, 1 + extraDrop));
                world.dropItem(block.getLocation(), new ItemStack(Material.SEEDS, 1 + extraSeeds));
                break;
            case CARROT:
                world.dropItem(block.getLocation(), new ItemStack(Material.CARROT_ITEM, 1 + extraDrop + extraSeeds));
                break;
            case POTATO:
                world.dropItem(block.getLocation(), new ItemStack(Material.POTATO_ITEM, 1 + extraDrop + extraSeeds));
                break;
            case MELON_BLOCK:
                world.dropItem(block.getLocation(), new ItemStack(Material.MELON, 2 + extraDrop));
                break;
            case PUMPKIN:
                world.dropItem(block.getLocation(), new ItemStack(Material.PUMPKIN, 1 + extraDrop / 2));
                break;
        }
    }

    public void defaultDrop(Block block){

        List<ItemStack> drops = new ArrayList<>(block.getDrops());
        int randomPercent = (int) (Math.random() * 100);
        System.out.println("Chance: " + randomPercent + "Farmer Drop: " + drops);

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

        World world = block.getWorld();
        switch (block.getType()){
            case CROPS:
                world.dropItem(block.getLocation(), new ItemStack(Material.WHEAT, 1 + extraDrop));
                world.dropItem(block.getLocation(), new ItemStack(Material.SEEDS, 1 + extraSeeds));
                break;
            case CARROT:
                world.dropItem(block.getLocation(), new ItemStack(Material.CARROT_ITEM, 1 + extraDrop + extraSeeds));
                break;
            case POTATO:
                world.dropItem(block.getLocation(), new ItemStack(Material.POTATO_ITEM, 1 + extraDrop + extraSeeds));
                break;
            case MELON_BLOCK:
                world.dropItem(block.getLocation(), new ItemStack(Material.MELON, 2 + extraDrop));
                break;
            case PUMPKIN:
                world.dropItem(block.getLocation(), new ItemStack(Material.PUMPKIN, 1 + extraDrop / 2));
                break;
        }
    }

}
