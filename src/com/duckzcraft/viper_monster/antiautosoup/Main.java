package com.duckzcraft.viper_monster.antiautosoup;

import com.duckzcraft.viper_monster.antiautosoup.listeners.EntityDamageByEntityListener;
import com.duckzcraft.viper_monster.antiautosoup.listeners.EntityDamageListener;
import com.duckzcraft.viper_monster.antiautosoup.listeners.InventoryClickListener;
import com.duckzcraft.viper_monster.antiautosoup.utils.Violation;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main extends JavaPlugin {

    private static Main instance;
    private Map<UUID, Long> lastAttacked = new HashMap<>();
    public Map<UUID, Violation> violations = new HashMap<>();

    @Override
    public void onEnable() {
		File file=new File(getDataFolder(),"config.yml");
		if(!file.exists()) {
			try {
				saveResource("config_template.yml",false);
				File file2=new File(getDataFolder(),"config_template.yml");
				file2.renameTo(new File(getDataFolder(),"config.yml"));
			}catch(Exception e) {}
		}

        instance = this;

        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new EntityDamageByEntityListener(), this);
        manager.registerEvents(new EntityDamageListener(), this);
        manager.registerEvents(new InventoryClickListener(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Main getInstance() {
        return instance;
    }

    public long getLastAttackTime(UUID uuid) {
        if (!lastAttacked.containsKey(uuid)) lastAttacked.put(uuid, System.currentTimeMillis());

        return lastAttacked.get(uuid);
    }

    public void setLastAttackTime(UUID uuid) {
        lastAttacked.put(uuid, System.currentTimeMillis());
    }

    public int raiseViolationLevel(UUID uuid) {
        Violation violation = getViolation(uuid);

        violation.raiseViolationLevel();
        violations.put(uuid, violation);

        return violation.getViolationLevel();
    }

    public Violation getViolation(UUID uuid) {
        if (!violations.containsKey(uuid)) violations.put(uuid, new Violation());

        return violations.get(uuid);
    }
}