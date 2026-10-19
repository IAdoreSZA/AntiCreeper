package nz.anticreeper;

import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiCreeper extends JavaPlugin implements Listener {

    private boolean peaceful;
    private boolean preventExplosion;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        peaceful = getConfig().getBoolean("peaceful", true);
        preventExplosion = getConfig().getBoolean("prevent-explosion", true);
        getServer().getPluginManager().registerEvents(this, this);
    }

    // Creepers never pick a target, so they wander like passive mobs and never start to hiss.
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTarget(EntityTargetEvent event) {
        if (peaceful && event.getEntity() instanceof Creeper && event.getTarget() != null) {
            event.setCancelled(true);
        }
    }

    // Stops the explosion from happening at all.
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onExplosionPrime(ExplosionPrimeEvent event) {
        if (preventExplosion && event.getEntity() instanceof Creeper) {
            event.setCancelled(true);
        }
    }

    // If an explosion does go off, it breaks no blocks.
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntity() instanceof Creeper) {
            event.blockList().clear();
        }
    }

    // ...and hurts no players, mobs, armor stands or item frames.
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Creeper) {
            event.setCancelled(true);
        }
    }

    // ...and doesn't pop paintings or item frames off walls.
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onHangingBreak(HangingBreakByEntityEvent event) {
        if (event.getRemover() instanceof Creeper) {
            event.setCancelled(true);
        }
    }
}
