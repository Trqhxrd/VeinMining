package me.trqhxrd.veinminer.listener

import me.trqhxrd.veinminer.config.VeinMinerConfig
import me.trqhxrd.veinminer.detectors.DefaultDetector
import org.bukkit.block.Block
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

object BlockBreakListener : Listener {

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        if (!e.player.isSneaking) return

        val oreGroup = VeinMinerConfig.ores.values.firstOrNull { it.blocks.contains(e.block.type) } ?: return
        println(oreGroup.toString())
        if (!oreGroup.tools.tools.contains(e.player.inventory.itemInMainHand.type)) return
        if (oreGroup.permission != null && !e.player.hasPermission(oreGroup.permission)) return

        val detected = mutableSetOf<Block>()
        val detector = DefaultDetector(oreGroup.maxSize)
        detector.scan(e.block, detected)
        detected.forEach { it.breakNaturally(e.player.inventory.itemInMainHand) }
    }
}
