package me.Bryan.School;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.Bryan.School.ClassManager.ClassManager;
import me.Bryan.School.Commands.school;
import me.Bryan.School.CountDowns.StudyCountdown;
import me.Bryan.School.Events.PlayerHit;
import me.Bryan.School.Events.PlayerLeave;
import me.Bryan.School.Utils.ChatUtils;

public class Main extends JavaPlugin{
	public static List<Player> students = new ArrayList<Player>();
	public static Main plugin;
	public static ClassManager setStatus;
	public static int classCD;
	public static int studyCD;
	public static int classCounter = 90;
	public static int studyCounter = 30;
	public static boolean school = false;
	public static boolean studyPeriod = false;
	public static int minStudents = 6;
	public void onEnable() {
		System.out.println("works");
		registerEvents();
		registerCommands();
	}
	
	public void onDisable() {
		students = null;
	}
	
	public void registerEvents() {
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerLeave(plugin), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerHit(plugin), this);
	}
	
	public void registerCommands() {
		this.getCommand("class").setExecutor(new school(this));
	}
	
	public static void schoolStart() {
			 StudyCountdown.startStudyCountDown();
			 Bukkit.broadcastMessage(ChatUtils.prefix +"Class is initiated /class join to save a spot!");
	}

	public static Main getInstance(){
		return plugin;
	}
}
