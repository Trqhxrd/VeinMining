package me.trqhxrd.veinminer.gui

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object GuiElements {
    const val CLOSE_SLOT = 49
    val BACKGROUND = run {
        val item = ItemStack(Material.GRAY_STAINED_GLASS_PANE)
        val meta = item.itemMeta!!
        meta.setDisplayName("§c")
        item.itemMeta = meta
        item
    }

    fun generateItem(material: Material, name: String): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta!!
        meta.setDisplayName(name)
        item.itemMeta = meta
        return item
    }
}
