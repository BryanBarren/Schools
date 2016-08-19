package me.Bryan.School.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.Bryan.School.Main;

public class PlayerHit implements Listener{

	Main main;
	public PlayerHit(Main main) {
		this.main = main;
	}
	@EventHandler
	public void onPlayerHit(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			Player damaged = (Player)event.getEntity();
			Entity damager = event.getDamager();
			if(damager instanceof Player && damaged instanceof Player){
				event.setCancelled(true);
			}
		}
	}
}
