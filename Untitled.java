package pl.untitled;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Untitled extends JavaPlugin {

    private int cleanupInterval;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        cleanupInterval = config.getInt("cleanup-interval", 60);
        startStringCleanupTask();}

    private void startStringCleanupTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getWorlds().forEach(world -> world.getEntitiesByClass(Item.class).stream().filter(item -> item.getItemStack().getType() == Material.STRING).forEach(Item::remove));
            }
        }.runTaskTimer(this, cleanupInterval * 20L, cleanupInterval * 20L);
    }
}
