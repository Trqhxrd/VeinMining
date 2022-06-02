package me.trqhxrd.veinminer

import me.trqhxrd.veinminer.config.VeinMinerConfig
import me.trqhxrd.veinminer.listener.BlockBreakListener
import me.trqhxrd.veinminer.listener.PlayerJoinListener
import me.trqhxrd.veinminer.listener.PlayerQuitListener
import me.trqhxrd.veinminer.player.VeinMineUser
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin

object VeinMiner {

    lateinit var plugin: JavaPlugin

    fun enable(plugin: JavaPlugin) {
        this.plugin = plugin

        VeinMinerConfig.load(this.plugin)
        Bukkit.getPluginManager().registerEvents(BlockBreakListener(), this.plugin)
        Bukkit.getPluginManager().registerEvents(PlayerQuitListener(), this.plugin)
        Bukkit.getPluginManager().registerEvents(PlayerJoinListener(this.plugin), this.plugin)

        Bukkit.getOnlinePlayers().forEach { VeinMineUser(this.plugin, it) }
    }

    fun disable() {
        HandlerList.unregisterAll(this.plugin)
        VeinMineUser.users.forEach { it.save() }
        VeinMineUser.users.clear()
        VeinMinerConfig.save(this.plugin)
    }
}
