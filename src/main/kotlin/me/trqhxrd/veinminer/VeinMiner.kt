package me.trqhxrd.veinminer

import me.trqhxrd.veinminer.config.VeinMinerConfig
import me.trqhxrd.veinminer.listener.BlockBreakListener
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerialization
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

    val cfg = VeinMinerConfig(this)

    override fun onEnable() {
        instance = this
        ConfigurationSerialization.registerClass(VeinMinerConfig::class.java, "config")

        this.cfg.toolGroups["pickaxe"] = VeinMinerConfig.Group(
            "pickaxe",
            null,
            64,
            setOf(
                Material.WOODEN_PICKAXE,
                Material.STONE_PICKAXE,
                Material.IRON_PICKAXE,
                Material.GOLDEN_PICKAXE,
                Material.DIAMOND_PICKAXE,
                Material.NETHERITE_PICKAXE
            ),
            setOf(
                Material.COPPER_ORE,
                Material.COAL_ORE,
                Material.IRON_ORE,
                Material.GOLD_ORE,
                Material.EMERALD_ORE,
                Material.LAPIS_ORE,
                Material.REDSTONE_ORE,
                Material.DIAMOND_ORE,
                Material.DEEPSLATE_COAL_ORE,
                Material.DEEPSLATE_IRON_ORE,
                Material.DEEPSLATE_GOLD_ORE,
                Material.DEEPSLATE_EMERALD_ORE,
                Material.DEEPSLATE_LAPIS_ORE,
                Material.DEEPSLATE_REDSTONE_ORE,
                Material.DEEPSLATE_DIAMOND_ORE,
                Material.ANCIENT_DEBRIS,
                Material.NETHER_QUARTZ_ORE,
                Material.NETHER_GOLD_ORE
            )
        )

        this.config.set("config", this.cfg)
        this.saveConfig()

        Bukkit.getPluginManager().registerEvents(BlockBreakListener(this), this)
    }

    override fun onDisable() {
        HandlerList.unregisterAll(this)
    }
}
