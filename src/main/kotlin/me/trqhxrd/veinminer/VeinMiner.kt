package me.trqhxrd.veinminer

import me.trqhxrd.veinminer.listener.BlockBreakListener
import org.bukkit.Bukkit
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File

class VeinMiner : JavaPlugin {

    constructor() : super()
    constructor(loader: JavaPluginLoader, description: PluginDescriptionFile, dataFolder: File, file: File) :
            super(loader, description, dataFolder, file)

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(BlockBreakListener, this)

        this.logger.info("Hello World!")
    }

    override fun onDisable() {
        this.logger.info("Goodbye!")
    }
}
