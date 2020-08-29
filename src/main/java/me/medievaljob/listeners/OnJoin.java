package me.medievaljob.listeners;

import me.medievaljob.MongoDB;
import me.medievaljob.state.State;
import me.medievaljob.state.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ServiceLoader;

public class OnJoin implements Listener {
	State state;
	MongoDB mongoDB;
	public OnJoin(State state, MongoDB mongoDB){
		this.state = state;
		this.mongoDB = mongoDB;
	}
	@EventHandler
    public void onJoin(PlayerJoinEvent event) {
    	Player player = event.getPlayer();

    	//this.getLogger().info(player.getAddress() + player.getName());

		User user = state.getUser(player.getName());
    	if (user instanceof User){
		}else{
			player.sendMessage(ChatColor.BLUE + "Устройтесь на работу! В Джеронии налог на тунеядство.");
    		User newUser = new User(player.getName());
    		state.addUser(newUser);
    		mongoDB.saveUser(newUser);
		}
    }

}

