package me.trqhxrd.veinminer

import me.trqhxrd.veinminer.listener.BlockBreakListener
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.HandlerList
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File

class VeinMiner : JavaPlugin {

    constructor() : super()
    constructor(loader: JavaPluginLoader, description: PluginDescriptionFile, dataFolder: File, file: File) :
            super(loader, description, dataFolder, file)

    companion object {
        lateinit var instance: VeinMiner
    }

    val veinmineable = mutableSetOf<Material>()

    override fun onEnable() {
        instance = this

        this.config.options().copyDefaults(true)
        this.config.getStringList("materials")
            .map { Material.valueOf(it) }
            .forEach { this.veinmineable.add(it) }

        Bukkit.getPluginManager().registerEvents(BlockBreakListener(this), this)
    }

    override fun onDisable() {
        HandlerList.unregisterAll(this)
    }
}
