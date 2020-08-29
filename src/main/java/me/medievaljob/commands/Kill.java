package me.medievaljob.commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kill implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if (command.getName().equals("kill")) {
				player.sendMessage("You just kill yourself");
				player.setHealth(0);
			}
			
		}
		return false;
	}
}