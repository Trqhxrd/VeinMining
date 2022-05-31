package me.trqhxrd.veinminer.config

import me.trqhxrd.veinminer.config.defaults.*
import org.bukkit.Material
import org.bukkit.plugin.java.JavaPlugin

object VeinMinerConfig {
    val tools = mutableMapOf<String, ToolGroup>()
    val ores = mutableMapOf<String, OreGroup>()

    fun save(plugin: JavaPlugin) {
        val cfg = plugin.config
        cfg.set("veinminer", null)

        tools.values.forEach { tool ->
            cfg.set("veinminer.tools.${tool.name}.tools", tool.tools.map { it.name })
        }

        ores.values.forEach { ore ->
            cfg.set("veinminer.ores.${ore.name}.permission", ore.permission)
            cfg.set("veinminer.ores.${ore.name}.maxSize", ore.maxSize)
            cfg.set("veinminer.ores.${ore.name}.tools", ore.tools.name)
            cfg.set("veinminer.ores.${ore.name}.materials", ore.blocks.map { it.name })
        }

        plugin.saveConfig()
    }

    fun load(plugin: JavaPlugin) {
        val cfg = plugin.config
        if (!cfg.contains("veinminer")) {
            this.setup(plugin)
            return
        }

        val toolsSection = cfg.getConfigurationSection("veinminer.tools")!!
        toolsSection.getKeys(false).forEach { toolGroupName ->
            this.tools[toolGroupName] = ToolGroup(
                toolGroupName,
                toolsSection.getStringList("$toolGroupName.tools").map { Material.valueOf(it) }.toSet()
            )
        }

        val oreSection = cfg.getConfigurationSection("veinminer.ores")!!
        oreSection.getKeys(false).forEach { oreGroupName ->
            val subSection = oreSection.getConfigurationSection(oreGroupName)!!
            this.ores[oreGroupName] = OreGroup(
                oreGroupName,
                subSection.getStringList("materials").map { Material.valueOf(it) }.toSet(),
                subSection.getString("permission", null),
                this.tools[subSection.getString("tools")]!!,
                subSection.getInt("maxSize")
            )
        }
    }

    fun setup(plugin: JavaPlugin) {
        this.tools[Pickaxes.name] = Pickaxes
        this.tools[Axes.name] = Axes
        this.tools[Shovels.name] = Shovels
        this.tools[Hoes.name] = Hoes

        this.ores[Ores.name] = Ores
        this.ores[Wood.name] = Wood
        this.ores[Sand.name] = Sand
        this.ores[Leaves.name] = Leaves

        this.save(plugin)
    }
}
