package me.trqhxrd.veinminer.listener

import me.trqhxrd.veinminer.player.VeinMineUser
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

class PlayerJoinListener(val plugin: JavaPlugin) : Listener {

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        VeinMineUser(plugin, e.player)
    }
}
