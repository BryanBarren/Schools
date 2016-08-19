package me.Bryan.School.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Bryan.School.Main;
import me.Bryan.School.ClassManager.ClassManager;
import me.Bryan.School.Utils.ChatUtils;

public class school implements CommandExecutor {
	public static Location classroom;
	public Location spawn;
	Main main;

	public school(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage(ChatUtils.prefix + ChatColor.GOLD + "Command usage:");
				player.sendMessage(ChatColor.GREEN + "/class join"); // DONE
				player.sendMessage(ChatColor.GREEN + "/class start"); // DONE
				player.sendMessage(ChatColor.GREEN + "/class kick <player>"); // DONE
				player.sendMessage(ChatColor.GREEN + "/class setclassroom"); // DONE
				player.sendMessage(ChatColor.GREEN + "/class end"); //DONE
				player.sendMessage(ChatColor.GREEN + "/class setspawn"); //DONE
			}
			if (args[0].equalsIgnoreCase("join")) {
				Main.students.add(player);
				player.sendMessage(ChatUtils.prefix + ChatColor.GREEN + "You've saved a spot in class!");
				return true;
			} else if (args[0].equalsIgnoreCase("start")) {
				if (player.hasPermission("school.teacher")) {
					Main.setStatus = ClassManager.STUDYPERIOD;
					Main.schoolStart();
					player.sendMessage(ChatUtils.prefix + ChatColor.GREEN + "You've just started a class.");
					return true;
				} else {
					player.sendMessage(ChatUtils.prefix + ChatColor.RED + "You're no teacher!");
					return true;
				}
			} else if (args[0].equalsIgnoreCase("kick")) {
				if (args.length == 1) {
					player.sendMessage(ChatUtils.prefix + ChatColor.RED + "Please specify a player");
					return true;
				}
				String message = "";
				for (int i = 2; i < args.length; i++) {
					message += args[i] + " ";
				}
				message.trim();
				if (Bukkit.getServer().getPlayer(message) != null) {
					if(player.hasPermission("school.kick")) {
					Main.students.remove(message);
					Bukkit.dispatchCommand(sender, "spawn " + message);
					player.sendMessage(ChatUtils.prefix + ChatColor.GREEN + "player kicked from your class!");
					} else {player.sendMessage(ChatUtils.prefix + ChatColor.RED + "You're no teacher");}
				} else {
					player.sendMessage(ChatUtils.prefix + ChatColor.RED + "Player null");
					return true;
				}
			} else if (args[0].equalsIgnoreCase("setclassroom")) {
				if(player.hasPermission("school.teacher")) {
				classroom.add(player.getLocation());
				player.sendMessage(ChatUtils.prefix + ChatColor.GREEN + "Classroom set location!");
				Bukkit.broadcastMessage(ChatUtils.prefix + ChatColor.RED + ChatColor.BOLD
						+ "Staff, please don't set another class room location.");
				} else {
					player.sendMessage(ChatUtils.prefix + ChatColor.RED + "You're no teacher");
				}
			} else if (args[0].equalsIgnoreCase("end")) {
				for (Player all : Main.students) {
					all.teleport(spawn);
					all.sendMessage(ChatUtils.prefix + ChatColor.GREEN + "Class has ended!");
				}
				Main.students.clear();
			} else if (args[0].equalsIgnoreCase("setspawn")) {
				if(player.hasPermission("school.teacher")) {
				spawn.add(player.getLocation());
				player.sendMessage(ChatUtils.prefix + ChatColor.GREEN
						+ "You've set the spawn location, where all students get teleported back.");
				}else {player.sendMessage(ChatUtils.prefix + ChatColor.RED + "You're no teacher");}
			}
		} else {
			sender.sendMessage(ChatUtils.prefix + ChatColor.RED + "Only players may join a class.");
			return true;
		}
		return true;
	}

}
