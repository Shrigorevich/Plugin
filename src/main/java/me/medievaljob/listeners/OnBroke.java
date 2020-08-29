package me.medievaljob.listeners;

import me.medievaljob.MongoDB;
import me.medievaljob.commands.Jobs;
import me.medievaljob.jobs.Job;
import me.medievaljob.jobs.Miner;
import me.medievaljob.state.State;
import me.medievaljob.state.User;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;

public class OnBroke implements Listener {
    private State state;
    private MongoDB mongoDB;
    private FileConfiguration config;

    public OnBroke(State state, MongoDB mongoDB, FileConfiguration config) {
        this.state = state;
        this.mongoDB = mongoDB;
        this.config = config;
    }

    @EventHandler
    public void onBroke(BlockBreakEvent event) {
        Player player = event.getPlayer();
        User user = state.getUser(player.getName());
        Block block = event.getBlock();
        event.setDropItems(false);
        //System.out.println(event.getBlock().getType().hashCode());
        player.sendMessage(ChatColor.GREEN + block.getType().name());
        if (user.getSkills().getOne("miner").getActive()) {
            Job miner = user.getSkills().getOne("miner");

            switch (block.getType()) {
                case COAL_ORE:
                    miner.updateProgress(config.getInt("COAL_EXP"), user.getSkills().getExpBoost());
                    break;
                case IRON_ORE:
                    user.getSkills().getOne("miner").updateProgress(config.getInt("IRON_EXP"), user.getSkills().getExpBoost());
                    break;
                case REDSTONE_ORE:
                    user.getSkills().getOne("miner").updateProgress(config.getInt("REDSTONE_EXP"), user.getSkills().getExpBoost());
                    break;
                case LAPIS_ORE:
                    user.getSkills().getOne("miner").updateProgress(config.getInt("LAPIS_EXP"), user.getSkills().getExpBoost());
                    break;
                case GOLD_ORE:
                    user.getSkills().getOne("miner").updateProgress(config.getInt("GOLD_EXP"), user.getSkills().getExpBoost());
                    break;
                case EMERALD_ORE:
                    user.getSkills().getOne("miner").updateProgress(config.getInt("EMERALD_EXP"), user.getSkills().getExpBoost());
                    break;
                case DIAMOND_ORE:
                    user.getSkills().getOne("miner").updateProgress(config.getInt("DIAMOND_EXP"), user.getSkills().getExpBoost());
                    break;
                default:
                    break;
            }
        }
        if(user.getSkills().getOne("woodcutter").getActive()){
            switch (block.getType()) {
                case LOG:
                    break;
                case WOOD:
                    break;
                default:
                    break;
            }
        }
        if(user.getSkills().getOne("farmer").getActive()){
            switch (block.getType()) {
                case CROPS:
                    break;
                case SUGAR_CANE_BLOCK:
                    break;
                case CARROT:
                    break;
                case POTATO:
                    break;
                case PUMPKIN:
                    break;
                case MELON_BLOCK:
                    break;
                default:
                    break;
            }
        }


//	    player.sendMessage(String.valueOf(event.getBlock().getType().isBlock()));
//        player.sendMessage(ChatColor.BLUE + event.getBlock().getType().name());
    }
}


//@Override
//public void applyToolEffect(Player player, Block block, int enchantlevel, BlockEvent event) {
//    if (!(event instanceof BlockBreakEvent)) {
//        return; // block not broken yet. do nothing.
//    }
//    if (event.getBlock().getType().equals(new ItemStack(Material.IRON_BLOCK))) {
//        event.getBlock().setType(Material.AIR);
//        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.DIAMOND_BLOCK));
//    }
//}

//event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.DIAMOND, 1));