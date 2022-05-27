package me.trqhxrd.veinminer

import org.bukkit.plugin.java.JavaPlugin

class VeinMiner : JavaPlugin() {
    override fun onEnable() {
        this.logger.info("Hello World!")
    }

    override fun onDisable() {
        this.logger.info("Goodbye!")
    }
}
