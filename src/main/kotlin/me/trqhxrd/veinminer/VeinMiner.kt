package me.trqhxrd.veinminer

import me.trqhxrd.veinminer.listener.BlockBreakListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class VeinMiner : JavaPlugin() {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(BlockBreakListener, this)

        this.logger.info("Hello World!")
    }

    override fun onDisable() {
        this.logger.info("Goodbye!")
    }
}
