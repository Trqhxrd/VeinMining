package me.trqhxrd.veinminer.gui

import me.trqhxrd.veinminer.player.VeinMineUser
import org.bukkit.Bukkit
import org.bukkit.event.Listener

class GuiList(user: VeinMineUser, mode: Mode) : AbstractGui(Bukkit.createInventory(null, 6 * 9, name)) {

    companion object : Listener {
        const val name = "§8Select item"
    }

    enum class Mode {
        EDIT, REMOVE
    }
}
