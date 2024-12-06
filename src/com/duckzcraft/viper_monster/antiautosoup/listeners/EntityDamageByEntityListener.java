package com.duckzcraft.viper_monster.antiautosoup.listeners;

import com.duckzcraft.viper_monster.antiautosoup.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();

        Main.getInstance().setLastAttackTime(player.getUniqueId());
    }
}