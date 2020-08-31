package me.medievaljob.listeners;

import me.medievaljob.jobs.Job;
import me.medievaljob.state.State;
import me.medievaljob.state.User;
import me.medievaljob.utils.Config;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

public class OnBreed implements Listener {
    private State state;
    public OnBreed(State state){
        this.state = state;
    }

    @EventHandler
    public void onBreed(EntityBreedEvent event) {

        EntityType entityType = event.getEntityType();
        String breederName = event.getBreeder().getName();
        User user = state.getUser(breederName);
        Job job = user.getSkills().getOne("breeder");
        if(job.getActive()){
            job.updateProgress(Config.getInt(entityType.toString() + "_BREEDING_EXP"), user.getSkills().getExpBoost());
        }
    }
}
