package me.Bryan.School.CountDowns;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.Bryan.School.Main;
import me.Bryan.School.ClassManager.ClassManager;
import me.Bryan.School.Commands.school;
import me.Bryan.School.Utils.ChatUtils;

public class StudyCountdown {
	Main main;
	public StudyCountdown(Main main) {
		this.main = main;
	}
	public static void startStudyCountDown() {
		if(Main.setStatus == ClassManager.STUDYPERIOD) {
			Main.studyCD = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
				@Override
				public void run() {
					if(Bukkit.getOnlinePlayers().size() < Main.minStudents) {
						return;
					}
					
					if((Bukkit.getOnlinePlayers().size() < Main.minStudents) && (Main.studyCounter == 20)) {
						Bukkit.broadcastMessage(ChatUtils.prefix + ChatColor.GOLD +"Restarting studyCounter.. Not enough players online");
						Bukkit.broadcastMessage(ChatUtils.prefix + ChatColor.GOLD +"Amount of students needed: 6");
						Bukkit.broadcastMessage(ChatUtils.prefix + ChatColor.GOLD +"Amount of players online " +Bukkit.getServer().getOnlinePlayers().size());
				
			}
					
					for(Player all : Main.students) {
						all.setLevel(Main.studyCounter);
						all.setExp((float)Main.studyCounter/(float)90); // Need a float variable to set level exp bar full
					}
					
					if(Main.studyCounter == 20 || Main.studyCounter == 10 || Main.studyCounter == 5 || Main.studyCounter == 4 || Main.studyCounter == 3 || Main.studyCounter == 2) {
						Bukkit.broadcastMessage(ChatUtils.prefix + ChatColor.GOLD +"Class starting in " + Main.studyCounter + ChatColor.GOLD +" seconds");
					}
					
					if(Main.studyCounter == 1) {
						Bukkit.broadcastMessage(ChatUtils.prefix + ChatColor.GOLD + "Class starts in 1 second");
						for(Player all : Main.students) {
							all.playSound(all.getLocation(), Sound.BLOCK_ANVIL_PLACE, 10f, 0f);
						}
					}
					
					if(Main.studyCounter == 0) {
						Bukkit.broadcastMessage(ChatUtils.prefix + ChatColor.GOLD +"teleported all players to class!!");
						for(Player all : Main.students) {
							all.playSound(all.getLocation(), Sound.ENTITY_CHICKEN_EGG, 10f, 0f);
							all.teleport(school.classroom);
						}
						ClassCountdown.startClassCountDown();
						//Main.setStatus = ClassManager.STUDYPERIOD;
						Main.school = false;
						Main.studyPeriod = true;
					}
					
					Main.studyCounter -= 1;
				}
			}, 0L, 20L);
		}
	}

}
