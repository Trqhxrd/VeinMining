package me.trqhxrd.veinminer.player

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class VeinMineUser(
    val plugin: JavaPlugin,
    val player: Player,
    var veinmineEnabled: Boolean = true,
    val file: File = File(plugin.dataFolder, "players/${player.uniqueId}.yml")
) {

    init {
        this.load()
        users.add(this)
    }

    companion object {
        val users = mutableSetOf<VeinMineUser>()

        fun user(player: Player) = this.users.first { it.player == player }
    }

    fun save() {
        file.parentFile.mkdirs()
        file.createNewFile()
        val config = YamlConfiguration()
        config.set("name", this.player.name)
        config.set("uuid", this.player.uniqueId.toString())
        config.set("veinmine.enabled", this.veinmineEnabled)

        config.save(file)
    }

    fun load() {
        if (!file.exists()) return
        val config = YamlConfiguration.loadConfiguration(file)
        this.veinmineEnabled = config.getBoolean("veinmine.enabled")
    }
}
