package me.medievaljob.listeners;
import me.medievaljob.MongoDB;
import me.medievaljob.jobs.Job;
import me.medievaljob.jobs.Miner;
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

        Block block = event.getBlock();
        Material blockType = block.getType();

        if (BlockType.isSpecialBlock(blockType)){
            if (! event.getBlock().hasMetadata("notNaturally")){
                System.out.println("Block is naturally");
                Player player = event.getPlayer();
                User user = state.getUser(player.getName());
                String blockName = blockType.name();
                player.sendMessage(ChatColor.GREEN + blockName);
                event.setDropItems(false);
                if(BlockType.isRockBlock(blockType)){
                    Miner miner = (Miner) user.getOne("miner");
                    if(miner.getActive()){
                        miner.updateProgress(config.getInt(blockName + "_EXP"), user.getExpBoost());
                        miner.customDrop(block);
                    }else{
                        miner.defaultDrop(block);
                    }
                }else if(BlockType.isPlantBlock(blockType)){
                    Job farmer = user.getOne("farmer");
                    if(farmer.getActive()){
                        farmer.updateProgress(config.getInt(blockName + "_EXP"), user.getExpBoost());
                        farmer.customDrop(block);
                    }else{
                        farmer.defaultDrop(block);
                    }
                }else if (BlockType.isWoodBlock(blockType)){
                    System.out.println("Block is wood");
                    Job woodcutter = user.getOne("woodcutter");
                    if(woodcutter.getActive()){
                        woodcutter.updateProgress(config.getInt(blockName + "_EXP"), user.getExpBoost());
                        woodcutter.customDrop(block);
                    }
                    else{
                        System.out.println("Default drop");
                        woodcutter.defaultDrop(block);
                    }
                }
            }
        }
    }
}