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
                        for(Job job : user.getSkills().getActiveSkills()){
                            message+= job.getName() + ": " + ChatColor.AQUA + job.getLevel() + " level, " +
                                    ChatColor.GREEN + job.getProgress() + " exp\n" + ChatColor.WHITE;
                        }
                        player.sendMessage(message);
                        break;
                    case "join":
                        if (args.length > 1) {
                            switch (args[1]) {
                                case "miner":
                                    user.getSkills().getMiner().setActive(true);
                                    user.getSkills().setExpBoost();
                                    player.sendMessage(ChatColor.AQUA + "Congratulations, you've become a miner !");
                                    mongoDB.updateUser(user);
                                    break;
                                case "woodcutter":
                                    user.getSkills().getWoodcutter().setActive(true);
                                    user.getSkills().setExpBoost();
                                    player.sendMessage(ChatColor.AQUA + "Congratulations, you've become a woodcutter !");
                                    mongoDB.updateUser(user);
                                    break;
                                case "farmer":
                                    user.getSkills().getFarmer().setActive(true);
                                    user.getSkills().setExpBoost();
                                    player.sendMessage(ChatColor.AQUA + "Congratulations, you've become a farmer !");
                                    mongoDB.updateUser(user);
                                    break;
                                case "breeder":
                                    user.getSkills().getBreeder().setActive(true);
                                    user.getSkills().setExpBoost();
                                    player.sendMessage(ChatColor.AQUA + "Congratulations, you've become a breeder !");
                                    mongoDB.updateUser(user);
                                    break;
                                case "hunter":
                                    user.getSkills().getHunter().setActive(true);
                                    user.getSkills().setExpBoost();
                                    player.sendMessage(ChatColor.AQUA + "Congratulations, you've become a hunter !");
                                    mongoDB.updateUser(user);
                                    break;
                                default:
                                    player.sendMessage("There is no such job");
                                    break;
                            }
                        }
                        break;
                    case "leave":
                        if (args.length > 1) {
                            switch (args[1]) {
                                case "miner":
                                    user.getSkills().getMiner().setActive(false);
                                    user.getSkills().setExpBoost();
                                    player.sendMessage("You left your job as a" + ChatColor.RED + " miner!");
                                    mongoDB.updateUser(user);
                                    break;
                                case "woodcutter":
                                    user.getSkills().getWoodcutter().setActive(false);
                                    user.getSkills().setExpBoost();
                                    player.sendMessage("You left your job as a" + ChatColor.RED + " woodcutter!");
                                    mongoDB.updateUser(user);
                                    break;
                                case "farmer":
                                    user.getSkills().getFarmer().setActive(false);
                                    user.getSkills().setExpBoost();
                                    player.sendMessage("You left your job as a" + ChatColor.RED + " farmer!");
                                    mongoDB.updateUser(user);
                                    break;
                                case "breeder":
                                    user.getSkills().getBreeder().setActive(false);
                                    user.getSkills().setExpBoost();
                                    player.sendMessage("You left your job as a" + ChatColor.RED + " hunter!");
                                    mongoDB.updateUser(user);
                                    break;
                                case "hunter":
                                    user.getSkills().getHunter().setActive(false);
                                    user.getSkills().setExpBoost();
                                    player.sendMessage("You left your job as a" + ChatColor.RED + " hunter!");
                                    mongoDB.updateUser(user);
                                    break;
                                default:
                                    player.sendMessage("There is no such job");
                                    break;
                            }
                        }
                        break;
                    case "test":
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
