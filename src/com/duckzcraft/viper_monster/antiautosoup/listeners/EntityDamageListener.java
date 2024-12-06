package com.duckzcraft.viper_monster.antiautosoup.listeners;

import com.duckzcraft.viper_monster.antiautosoup.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (event.getCause() == EntityDamageEvent.DamageCause.SUICIDE) return;

        Player player = (Player) event.getEntity();

        Main.getInstance().setLastAttackTime(player.getUniqueId());
    }
}