package me.medievaljob.commands;

import me.medievaljob.MongoDB;
import me.medievaljob.jobs.Job;
import me.medievaljob.state.State;
import me.medievaljob.state.User;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Jobs implements CommandExecutor {
    State state;
    MongoDB mongoDB;

    public Jobs(State state, MongoDB mongoDB) {
        this.state = state;
        this.mongoDB = mongoDB;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            User user = state.getUser(player.getName());
            if (args.length > 0) {
                switch (args[0]) {

                    case "status":
                        String message = "";
                        for(Job job : user.getActiveSkills()){
                            message+= job.getName() + ": " + ChatColor.AQUA + job.getLevel() + " level, " +
                                    ChatColor.GREEN + job.getProgress() + " exp\n" + ChatColor.WHITE;
                        }
                        player.sendMessage(message);
                        break;
                    case "join":
                        if (args.length > 1) {
                            Job job = user.getOne(args[1]);
                            if(job instanceof Job){
                                job.setActive(true);
                                user.setExpBoost();
                                player.sendMessage(ChatColor.AQUA + "Congratulations, you've become a " + job.getName() + "!");
                                // ну тут в форматированную строку переделать
                                mongoDB.updateUser(user);
                            }else{
                                player.sendMessage(ChatColor.RED + "Такой работы у нас нет! " + args[1]);
                            }
                        }
                        break;
                    case "leave":
                        if (args.length > 1) {
                            Job job = user.getOne(args[1]);
                            if(job instanceof Job){
                                job.setActive(false);
                                user.setExpBoost();
                                player.sendMessage(ChatColor.AQUA + "You left your job as a " + ChatColor.RED + job.getName());
                                // ну тут в форматированную строку переделать
                                mongoDB.updateUser(user);
                            }else{
                                player.sendMessage(ChatColor.RED + "Такой работы у нас нет! " + args[1]);
                            }
                        }
                        break;
                    case "test":
                        for(Job job : user.getActiveSkills()){
                            System.out.println(job);
                        }
                        break;
                    case "savestate":
                        mongoDB.saveState(state.getUsers());
                        break;
                    default:
                        player.sendMessage("Non-existent argument: " + args[0]);
                }

            } else {
                player.sendMessage(ChatColor.AQUA + "You need to provide arguments (/jobs status, /jobs join)");
            }
        }
        return true;
    }
}
