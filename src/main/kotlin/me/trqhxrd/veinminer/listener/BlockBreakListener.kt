package me.trqhxrd.veinminer.listener

import me.trqhxrd.veinminer.detectors.DefaultDetector
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

object BlockBreakListener : Listener {

    val whitelist = mutableSetOf(
        Material.COAL_ORE,
        Material.IRON_ORE,
        Material.GOLD_ORE,
    )

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        if (!e.player.isSneaking) return
        if (!this.whitelist.contains(e.block.type)) return

        val scanned = mutableSetOf<Block>()
        val detector = DefaultDetector()
        detector.scan(e.block, scanned)
        scanned.forEach { it.breakNaturally(e.player.inventory.itemInMainHand) }
    }
}
