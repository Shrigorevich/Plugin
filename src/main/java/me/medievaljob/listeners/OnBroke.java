package me.medievaljob.listeners;

import me.medievaljob.MongoDB;
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

    public OnBroke(State state, MongoDB mongoDB, FileConfiguration config){
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

	    switch (block.getType().name()){
            case "COAL_ORE":
                user.getSkills().getMiner().updateProgress(config.getInt("COAL_EXP"), user.getSkills().getExpBoost());
                block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.COAL, 2));
                break;
            case "IRON_ORE":
                user.getSkills().getMiner().updateProgress(config.getInt("IRON_EXP"), user.getSkills().getExpBoost());
                break;
            case "REDSTONE_ORE":
                user.getSkills().getMiner().updateProgress(config.getInt("REDSTONE_EXP"), user.getSkills().getExpBoost());
                break;
            case "LAPIS_ORE":
                user.getSkills().getMiner().updateProgress(config.getInt("LAPIS_EXP"), user.getSkills().getExpBoost());
                break;
            case "GOLD_ORE":
                user.getSkills().getMiner().updateProgress(config.getInt("GOLD_EXP"), user.getSkills().getExpBoost());
                break;
            case "EMERALD_ORE":
                user.getSkills().getMiner().updateProgress(config.getInt("EMERALD_EXP"), user.getSkills().getExpBoost());
                break;
            case "DIAMOND_ORE":
                user.getSkills().getMiner().updateProgress(config.getInt("DIAMOND_EXP"), user.getSkills().getExpBoost());
                break;
            default:
                break;
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