package me.trqhxrd.veinminer.listener

import me.trqhxrd.veinminer.VeinMiner
import me.trqhxrd.veinminer.detectors.DefaultDetector
import org.bukkit.block.Block
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BlockBreakListener(val plugin: VeinMiner) : Listener {

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        if (!e.player.hasPermission("veinminer.veinmine")) return
        if (!e.player.isSneaking) return
        if (!plugin.veinmineable.contains(e.block.type)) return

        val scanned = mutableSetOf<Block>()
        val detector = DefaultDetector()
        detector.scan(e.block, scanned)
        scanned.forEach { it.breakNaturally(e.player.inventory.itemInMainHand) }
    }
}
