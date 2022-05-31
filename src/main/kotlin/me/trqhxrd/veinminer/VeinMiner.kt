package me.trqhxrd.veinminer

import me.trqhxrd.veinminer.config.VeinMinerConfig
import me.trqhxrd.veinminer.listener.BlockBreakListener
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin

object VeinMiner {

    lateinit var plugin: JavaPlugin

    fun enable(plugin: JavaPlugin) {
        this.plugin = plugin

        VeinMinerConfig.load(this.plugin)
        Bukkit.getPluginManager().registerEvents(BlockBreakListener, this.plugin)
    }

    fun disable() {
        HandlerList.unregisterAll(this.plugin)
        VeinMinerConfig.save(this.plugin)
    }
}
