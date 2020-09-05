package me.medievaljob.jobs;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Woodcutter extends Job {
    public Woodcutter(String name) {
        super(name);
    }

    public Woodcutter(String name, int level, int progress, boolean active){
        super(name, level, progress, active);
    }

    public void customDrop(Block block){

        List<ItemStack> drops = new ArrayList<>(block.getDrops());
        int drop = 1;
        int chance = (int) (Math.random() * 100);
        System.out.println("Chance: " + chance);
        if(chance <= getLevel() * 10){
            System.out.println("+ Extra drop");
            drop+=getLevel() / 2;
        }

        switch (drops.get(0).getI18NDisplayName()){
            case "Oak Wood":
                block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.LOG, drop, (short) 0));
                break;
            case "Spruce Wood":
                block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.LOG, drop, (short) 1));
                break;
            case "Birch Wood":
                block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.LOG, drop, (short) 2));
                break;
            case "Jungle Wood":
                block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.LOG, drop, (short) 3));
                break;
        }
    }

    public void defaultDrop(Block block){

        List<ItemStack> drops = new ArrayList<>(block.getDrops());
        int chance = (int) (Math.random() * 100);
        System.out.println("Chance: " + chance + " Drop: " + drops.get(0).getI18NDisplayName());

        if (chance <= 50) {
            Material item1 = drops.get(0).getType();
            switch (drops.get(0).getI18NDisplayName()){
                case "Oak Wood":
                    block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.LOG, 1, (short) 0));
                    break;
                case "Spruce Wood":
                    block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.LOG, 1, (short) 1));
                    break;
                case "Birch Wood":
                    block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.LOG, 1, (short) 2));
                    break;
                case "Jungle Wood":
                    block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.LOG, 1, (short) 3));
                    break;
            }
        }
    }

}
