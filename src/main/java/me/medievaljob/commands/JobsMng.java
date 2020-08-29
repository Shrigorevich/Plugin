package me.medievaljob.commands;

import me.medievaljob.state.State;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobsMng implements CommandExecutor {
    State state;

    public JobsMng(State state) {
        this.state = state;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length > 1){
                switch (args[0]){
                    default:
                        player.sendMessage("unknown command");
                }
            }
        }
        return false;
    }
}
