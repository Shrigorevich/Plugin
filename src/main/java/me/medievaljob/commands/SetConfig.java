package me.medievaljob.commands;

import me.medievaljob.MedievalJob;
import me.medievaljob.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetConfig implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 1){
				switch (args[0]){
					case "set":
						Config.set(args[1], args[2]);
					case "getint":
						player.sendMessage(ChatColor.GREEN + "Value: " + ChatColor.WHITE +
								Config.getInt(args[1]));
					case "getstring":
						player.sendMessage(ChatColor.GREEN + "Value: " + ChatColor.WHITE +
								Config.getString(args[1]));
				}
			}
		}
		return true;
	}
}

//player.sendMessage("Right case");
//plugin.getConfig().set("Data", "Cool ass, Smarkatch");