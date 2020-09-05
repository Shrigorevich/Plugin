package me.medievaljob.jobs;

import me.medievaljob.utils.Config;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Job {
    private String name;
    private int level;
    private int progress;
    private boolean active;
    private int expToLvl;

    public Job(String name, int level, int progress, boolean active) {
        this.name = name;
        this.level = level;
        this.progress = progress;
        this.active = active;
        this.expToLvl = setExpToLvl(level);
    }

    public Job(String name) {
        this.name = name;
        this.level = 1;
        this.progress = 0;
        this.active = false;
        this.expToLvl = setExpToLvl(1);
    }

    public String getName() {
        return name;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void updateProgress(int exp, double expBoost) {
        this.progress += (int) exp * expBoost;
        if (progress > expToLvl) {
            this.level += 1;
            setExpToLvl(this.level);
            setProgress(0);
        }
    }

    public int setExpToLvl(int lvl){
        double expToLvl = Config.getInt("EXP_CONST") * Math.pow(2, lvl);
        return (int) expToLvl;
    }

    public void customDrop(Block block){

        List<ItemStack> drops = new ArrayList<>(block.getDrops());
        int drop = 1;
        int chance = (int) (Math.random() * 100);
        System.out.println("Chance: " + chance);
        if(0 <= chance && chance <= getLevel() * 10){
            System.out.println("+ Extra drop");
            drop+=getLevel() / 2;
        }

        block.getWorld().dropItem(block.getLocation(), new ItemStack(drops.get(0).getType(), drop));
    }

    public void defaultDrop(Block block){

        List<ItemStack> drops = new ArrayList<>(block.getDrops());
        int chance = (int) (Math.random() * 100);

        if (0 <= chance && chance <= 50) {
            Material item1 = drops.get(0).getType();
            block.getWorld().dropItem(block.getLocation(), new ItemStack(drops.get(0).getType(), 1));

        }
    }
}
