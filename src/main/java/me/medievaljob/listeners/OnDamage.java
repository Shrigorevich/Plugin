package me.medievaljob.listeners;

import me.medievaljob.jobs.Job;
import me.medievaljob.state.State;
import me.medievaljob.state.User;
import me.medievaljob.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnDamage implements Listener {

    private State state;

    public OnDamage(State state){
        this.state = state;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){

        Entity damager = event.getDamager();
        //System.out.println(damager);

        if (damager instanceof Player){
            User user = state.getUser(damager.getName());
            Job hunter = user.getOne("hunter");
            if (hunter.getActive()){
                //System.out.println(event.getDamage());
                LivingEntity entity = (LivingEntity) event.getEntity();
                if (entity instanceof LivingEntity){
                    System.out.println(ChatColor.AQUA + "" + entity.getHealth());
                    event.setDamage(event.getDamage() + Config.getDouble("DAMAGE_PER_LVL") * hunter.getLevel());
                    System.out.println(ChatColor.GREEN + "" + entity.getHealth());
                }
                //System.out.println(event.getDamage());
            }
        }
    }
}
