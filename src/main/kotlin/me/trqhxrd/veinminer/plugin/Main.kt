package me.trqhxrd.veinminer.plugin

import me.trqhxrd.veinminer.VeinMiner
import me.trqhxrd.veinminer.plugin.command.VeinMineCommand
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File

class Main : JavaPlugin {

    constructor() : super()
    constructor(loader: JavaPluginLoader, description: PluginDescriptionFile, dataFolder: File, file: File) :
            super(loader, description, dataFolder, file)

    override fun onEnable() {
        VeinMiner.enable(this)
        VeinMineCommand(this)
    }

    override fun onDisable() = VeinMiner.disable()
}
