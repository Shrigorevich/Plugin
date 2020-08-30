package me.medievaljob.listeners;

import me.medievaljob.MongoDB;
import me.medievaljob.jobs.Job;
import me.medievaljob.state.State;
import me.medievaljob.state.User;
import me.medievaljob.utils.BlockType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

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
        Material block = event.getBlock().getType();
        String blockName = block.name();
        event.setDropItems(false);
        //System.out.println(event.getBlock().getType().hashCode());
        player.sendMessage(ChatColor.GREEN + blockName);

        if(BlockType.isRockBlock(block)){
            Job miner = user.getSkills().getOne("miner");
            if(miner.getActive()){
                miner.updateProgress(config.getInt(blockName + "_EXP"), user.getSkills().getExpBoost());
            }
        }else if(BlockType.isPlantBlock(block)){
            Job farmer = user.getSkills().getOne("farmer");
            if(farmer.getActive()){
                farmer.updateProgress(config.getInt(blockName + "_EXP"), user.getSkills().getExpBoost());
            }
        }else if (BlockType.isWoodBlock(block)){
            Job woodcutter = user.getSkills().getOne("woodcutter");
            if(woodcutter.getActive()){
                woodcutter.updateProgress(config.getInt(blockName + "_EXP"), user.getSkills().getExpBoost());
            }
        }else{
            return;
        }
    }
}
