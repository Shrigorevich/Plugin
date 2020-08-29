package me.medievaljob;

import me.medievaljob.state.State;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import me.medievaljob.commands.Jobs;
import me.medievaljob.commands.SetConfig;

import me.medievaljob.listeners.OnBroke;
import me.medievaljob.listeners.OnJoin;

//SunCertPathBuilderException: unable to find valid certification path to requested target
public class MedievalJob extends JavaPlugin implements Listener {

    private MongoDB mongoDB = new MongoDB(getConfig().getString("MongoURI"));
    private State state;

    @Override
    public void onEnable() {
        this.getLogger().info("IT IS WORK" );
        state = new State(mongoDB.getData());
        //config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //events
        getServer().getPluginManager().registerEvents(new OnJoin(state, mongoDB), this);
        getServer().getPluginManager().registerEvents(new OnBroke(state, mongoDB, getConfig()), this);
        //commands
        getCommand("setconfig").setExecutor(new SetConfig());
        getCommand("jobs").setExecutor(new Jobs(state, mongoDB));

        System.out.println("Connection to the database");
    }

    @Override
    public void onDisable() {
        mongoDB.saveState(state.getUsers());
        this.getLogger().info("FUCKING PLUGIN SHUT DOWN");
    }
}
//mongodb+srv://shrigorevich:<password>@cluster0.kjaby.azure.mongodb.net/<dbname>?retryWrites=true&w=majority
