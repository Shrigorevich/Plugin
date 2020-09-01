package me.medievaljob.listeners;

import me.medievaljob.jobs.Job;
import me.medievaljob.state.State;
import me.medievaljob.state.User;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class OnShearSheep implements Listener {

    private State state;
    private FileConfiguration config;

    public OnShearSheep(State state, FileConfiguration config){
        this.state = state;
        this.config = config;
    }

    @EventHandler
    public void onShearSheep(PlayerShearEntityEvent event){

        Player player = event.getPlayer();
        User user = state.getUser(player.getName());
        Job job = user.getOne("breeder");
        if (job.getActive()){
            job.updateProgress(config.getInt("SHEAR_EXP"), user.getExpBoost());
        }

        player.sendMessage("You sheared the sheep");
    }
}
