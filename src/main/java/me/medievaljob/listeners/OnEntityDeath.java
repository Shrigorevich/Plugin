package me.medievaljob.listeners;

import me.medievaljob.MongoDB;
import me.medievaljob.jobs.Breeder;
import me.medievaljob.jobs.Job;
import me.medievaljob.state.State;
import me.medievaljob.state.User;
import me.medievaljob.utils.KilledEntityType;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class OnEntityDeath implements Listener {
    State state;
    MongoDB mongoDB;
    FileConfiguration config;

    public OnEntityDeath(State state, MongoDB mongoDB, FileConfiguration config) {
        this.state = state;
        this.mongoDB = mongoDB;
        this.config = config;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        Entity entity = event.getEntity();
        EntityType entityType = event.getEntityType();

        Player player = event.getEntity().getKiller();

        if (player instanceof Player) {
            User user = state.getUser(player.getName());

            if (KilledEntityType.isPeacefulEntity(entityType)) {
                Breeder breeder = (Breeder) user.getOne("breeder");
                if (breeder.getActive()) {
                    breeder.updateProgress(config.getInt(entityType + "_EXP"), user.getExpBoost());
                    System.out.println(event.getDrops());
                    breeder.customDrop(entity, event.getDrops());
                }else{
                    breeder.defaultDrop(entity, event.getDrops());
                }
            }

            if (KilledEntityType.isHostileEntity(entityType)) {
                Job hunter = user.getOne("hunter");
                if (hunter.getActive()) {
                    hunter.updateProgress(config.getInt(entityType + "_EXP"), user.getExpBoost());
                }
            }
        }
    }
}
