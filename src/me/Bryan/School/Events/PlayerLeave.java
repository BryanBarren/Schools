package me.Bryan.School.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Bryan.School.Main;

public class PlayerLeave implements Listener{
	Main main;
	public PlayerLeave(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event){
		Player player = event.getPlayer();
		if(Main.students.contains(player)) {
			Main.students.remove(player);
		}
	}
}
