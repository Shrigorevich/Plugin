package me.medievaljob.listeners;

import me.medievaljob.jobs.Job;
import me.medievaljob.state.State;
import me.medievaljob.state.User;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

public class OnBreed implements Listener {
    private State state;
    private FileConfiguration config;
    public OnBreed(State state, FileConfiguration config){
        this.state = state;
        this.config = config;
    }

    @EventHandler
    public void onBreed(EntityBreedEvent event) {

        EntityType entityType = event.getEntityType();
        String breederName = event.getBreeder().getName();
        User user = state.getUser(breederName);
        Job job = user.getSkills().getOne("breeder");
        System.out.println(entityType + " " + entityType.toString() + "_BREEDING_EXP");
        if(job.getActive()){
            System.out.println("Go exp");
            job.updateProgress(config.getInt(entityType.toString() + "_BREEDING_EXP"), user.getSkills().getExpBoost());
        }
    }
}
