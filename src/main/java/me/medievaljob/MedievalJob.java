package me.medievaljob;

import me.medievaljob.listeners.*;
import me.medievaljob.state.State;
import me.medievaljob.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import me.medievaljob.commands.Jobs;
import me.medievaljob.commands.SetConfig;

//TODO: add job boosts for Hunter
//TODO: set drop for OnShear event
//TODO: PlayerItemDamageEvent



public class MedievalJob extends JavaPlugin implements Listener {

    private MongoDB mongoDB = new MongoDB(getConfig().getString("MongoURI"));
    private State state;

    @Override
    public void onEnable() {
        this.getLogger().info(ChatColor.AQUA + "IT IS WORK" );
        state = new State(mongoDB.getData());
        //config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        FileConfiguration config = getConfig();

        //events
        getServer().getPluginManager().registerEvents(new OnJoin(state, mongoDB), this);
        getServer().getPluginManager().registerEvents(new OnEntityDeath(state, mongoDB, config), this);
        getServer().getPluginManager().registerEvents(new OnBroke(state, mongoDB, config), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
        getServer().getPluginManager().registerEvents(new OnShearSheep(state, config), this);
        getServer().getPluginManager().registerEvents(new OnBreed(state), this);
        getServer().getPluginManager().registerEvents(new OnDamage(state), this);

        //commands
        //getCommand("config").setExecutor(new SetConfig());
        getCommand("jobs").setExecutor(new Jobs(state, mongoDB));

        System.out.println(ChatColor.AQUA + "Connection to the database");
    }

    @Override
    public void onDisable() {
        mongoDB.saveState(state.getUsers());
        this.getLogger().info("MJ plugin completed successfully");
    }
}
//mongodb+srv://shrigorevich:<password>@cluster0.kjaby.azure.mongodb.net/<dbname>?retryWrites=true&w=majority
