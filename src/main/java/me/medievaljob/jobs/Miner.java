package me.medievaljob.jobs;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class Miner extends Job {
    public Miner(String name) {
        super(name);
    }

    public Miner(String name, int level, int progress, boolean active){
        super(name, level, progress, active);
    }

    public void dropCoal(Block block){
        block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.COAL, 2));
    }
}
