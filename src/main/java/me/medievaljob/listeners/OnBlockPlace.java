package me.medievaljob.listeners;
import me.medievaljob.MedievalJob;
import org.bukkit.plugin.Plugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class OnBlockPlace implements Listener {

    private Plugin plugin = MedievalJob.getPlugin(MedievalJob.class);

    @EventHandler
    public void onBlockCook(BlockPlaceEvent event){
        event.getBlock().setMetadata("notNaturally", new FixedMetadataValue(plugin, true));
    }
}
