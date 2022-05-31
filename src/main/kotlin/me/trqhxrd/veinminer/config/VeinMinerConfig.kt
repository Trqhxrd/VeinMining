package me.trqhxrd.veinminer.config

import me.trqhxrd.veinminer.config.defaults.*
import org.bukkit.Material.valueOf
import org.bukkit.plugin.java.JavaPlugin

object VeinMinerConfig {
    val tools = mutableMapOf<String, ToolGroup>()
    val ores = mutableMapOf<String, OreGroup>()

    fun save(plugin: JavaPlugin) {
        plugin.config.set("veinminer", null)

        this.tools.values.forEach { tool ->
            plugin.config.set(
                "veinminer.tools.${tool.name}.tools",
                tool.tools.map { it.name }.toList()
            )
        }

        this.ores.values.forEach { group ->
            plugin.config.set("veinminer.materials.${group.name}.permission", group.permission)
            plugin.config.set("veinminer.materials.${group.name}.maxSize", group.maxSize)
            plugin.config.set("veinminer.materials.${group.name}.tools", group.tools.name)
            plugin.config.set("veinminer.materials.${group.name}.blocks", group.blocks.map { it.name }.toTypedArray())
        }

        plugin.saveConfig()
    }

    fun load(plugin: JavaPlugin) {
        if (!plugin.config.contains("veinminer")) {
            this.setup(plugin)
            return
        }

        val tools = plugin.config.getConfigurationSection("veinminer.tools")!!
        tools.getKeys(false).forEach { group ->
            val section = tools.getConfigurationSection(group)!!
            this@VeinMinerConfig.tools[group] =
                ToolGroup(group, section.getStringList("tools").map { valueOf(it) }.toSet())
        }

        val materials = plugin.config.getConfigurationSection("veinminer.materials")!!
        materials.getKeys(false).forEach { material ->
            val section = materials.getConfigurationSection(material)!!
            section.getKeys(false).forEach { name ->
                this.ores[name] = OreGroup(
                    name,
                    section.getStringList("blocks").map { valueOf(it) }.toSet(),
                    section.getString("permission", null),
                    this.tools[section.getString("tools")]!!,
                    section.getInt("maxSize", 64)
                )
            }
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
