package me.medievaljob.jobs;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Miner extends Job {
    public Miner(String name) {
        super(name);
    }

    public Miner(String name, int level, int progress, boolean active){
        super(name, level, progress, active);
    }

}
