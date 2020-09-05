package me.medievaljob.listeners;
import me.medievaljob.MedievalJob;
import me.medievaljob.utils.BlockType;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class OnBlockPlace implements Listener {

    private Plugin plugin = MedievalJob.getPlugin(MedievalJob.class);

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Material blockType = event.getBlockReplacedState().getType();
        System.out.println(blockType);
        if(BlockType.isPlantBlock(blockType)){
            System.out.println("Ok, it could be a plant block");
            event.getBlock().removeMetadata("notNaturally", plugin);
        }else {
            System.out.println("Place block event" );
            event.getBlock().setMetadata("notNaturally", new FixedMetadataValue(plugin, true));
        }
    }
}
