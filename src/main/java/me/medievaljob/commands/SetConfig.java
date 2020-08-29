package me.medievaljob.commands;

import me.medievaljob.MedievalJob;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SetConfig implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Plugin plugin = MedievalJob.getPlugin(MedievalJob.class);
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(ChatColor.AQUA + "Config change");	
			plugin.getConfig().set("Data", "Cool ass, " + player.getDisplayName());
		}
		return false;
	}
}

//player.sendMessage("Right case");
//plugin.getConfig().set("Data", "Cool ass, Smarkatch");