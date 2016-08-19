package me.Bryan.School.CountDowns;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.Bryan.School.Main;
import me.Bryan.School.ClassManager.ClassManager;
import me.Bryan.School.Utils.ChatUtils;

public class ClassCountdown {
	
	public static void startClassCountDown() {
		if(Main.setStatus == ClassManager.CLASS) {
			Main.classCD = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
				@Override
				public void run() {

					
					for(Player all : Main.students) {
						all.setLevel(Main.classCounter);
						all.setExp((float)Main.classCounter/(float)90); // Need a float variable to set level exp bar full
					}
					
					if(Main.classCounter == 60 || Main.classCounter == 30 || Main.classCounter == 10 || Main.classCounter == 5 || Main.classCounter == 4 || Main.classCounter == 3|| Main.classCounter == 2) {
						Bukkit.broadcastMessage(ChatUtils.prefix + ChatColor.GOLD +"Class starting in " + Main.classCounter + ChatColor.GOLD +" seconds");
					}
					
					if(Main.classCounter == 1) {
						Bukkit.broadcastMessage(ChatUtils.prefix + ChatColor.GOLD + "Class starts in 1 second");
						for(Player all : Bukkit.getOnlinePlayers()) {
							all.playSound(all.getLocation(), Sound.BLOCK_ANVIL_PLACE, 10f, 0f);
						}
					}
					
					if(Main.classCounter == 0) {
						for(Player all : Main.students) {
							all.playSound(all.getLocation(), Sound.ENTITY_CHICKEN_EGG, 10f, 0f);
							all.sendMessage(ChatUtils.prefix + ChatColor.GOLD + "Class has now begun, please don't talk.");
						}
						Main.setStatus = ClassManager.CLASS;
						Main.school = true;
						Main.studyPeriod = false;
					}
					
					Main.classCounter -= 1;
				}
			}, 0L, 20L);
		}
	}

}
