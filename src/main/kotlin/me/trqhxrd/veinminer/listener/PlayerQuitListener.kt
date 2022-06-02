package me.trqhxrd.veinminer.listener

import me.trqhxrd.veinminer.player.VeinMineUser
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin

class PlayerQuitListener : Listener {

    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent) {
        val user = VeinMineUser.users.first { it.player == e.player }
        user.save()
        VeinMineUser.users.remove(user)
    }
}
