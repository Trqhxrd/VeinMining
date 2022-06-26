package me.trqhxrd.veinminer.gui

import me.trqhxrd.veinminer.player.VeinMineUser
import org.bukkit.Bukkit
import org.bukkit.event.Listener

class GuiAddEdit(val user: VeinMineUser, val mode: Mode) :
    AbstractGui(Bukkit.createInventory(null, 6 * 9, mode.title)) {

    companion object : Listener {
    }

    enum class Mode(val title: String) {
        ADD("§8Add Item"), EDIT("§8Edit Item")
    }
}
