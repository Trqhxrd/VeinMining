package me.trqhxrd.veinminer.gui

import me.trqhxrd.veinminer.player.VeinMineUser
import org.bukkit.entity.HumanEntity
import org.bukkit.inventory.Inventory

abstract class AbstractGui(protected val inv: Inventory) {

    fun open(human: HumanEntity) = human.openInventory(this.inv)
    fun open(user: VeinMineUser) = this.open(user.player)
}
