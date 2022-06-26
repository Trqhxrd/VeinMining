package me.trqhxrd.veinminer.gui

import me.trqhxrd.veinminer.player.VeinMineUser
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

class GuiMain(val veinMineUser: VeinMineUser) : AbstractGui(Bukkit.createInventory(null, 6 * 9, name)) {

    companion object :Listener{
        const val name = "§8Main"
        const val REMOVE_SLOT = 19
        const val EDIT_SLOT = 22
        const val ADD_SLOT = 25
        val REMOVE_ITEM = GuiElements.generateItem(Material.REDSTONE, "§cRemove item")
        val EDIT_ITEM = GuiElements.generateItem(Material.COMPARATOR, "§bEdit item")
        val ADD_ITEM = GuiElements.generateItem(Material.EMERALD, "§aAdd item")

        @EventHandler
        fun onInventoryClick(e: InventoryClickEvent) {
            if (e.clickedInventory == e.view.topInventory) {
                val user = VeinMineUser.user(e.whoClicked as Player)
                when (e.slot) {
                    REMOVE_SLOT -> GuiList(user, GuiList.Mode.REMOVE).open(e.whoClicked)
                    EDIT_SLOT -> GuiList(user,GuiList.Mode.EDIT).open(e.whoClicked)
                    ADD_SLOT -> GuiAddEdit(user, GuiAddEdit.Mode.ADD).open(e.whoClicked)
                }
            }
        }

        @EventHandler
        fun onClose(e: InventoryCloseEvent) {

        }
    }

    init {
        for (i in 0 until this.inv.size) this.inv.setItem(i, GuiElements.BACKGROUND)

        this.inv.setItem(REMOVE_SLOT, REMOVE_ITEM)
        this.inv.setItem(EDIT_SLOT, EDIT_ITEM)
        this.inv.setItem(ADD_SLOT, ADD_ITEM)
    }
}
