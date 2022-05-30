package me.trqhxrd.veinminer.listener

import me.trqhxrd.veinminer.VeinMiner
import me.trqhxrd.veinminer.config.VeinMinerConfig
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

        var group: VeinMinerConfig.Group? = null

        for (toolGroup in this.plugin.cfg.toolGroups.values) {
            if (toolGroup.permission != null && !e.player.hasPermission(toolGroup.permission)) continue
            if (!toolGroup.tools.contains(e.player.inventory.itemInMainHand.type)) continue
            if (!toolGroup.materials.contains(e.block.type)) continue
            group = toolGroup
            break
        }

        if (group == null) return

        val scanned = mutableSetOf<Block>()
        val detector = DefaultDetector(group.maxSize)
        detector.scan(e.block, scanned)
        scanned.forEach { it.breakNaturally(e.player.inventory.itemInMainHand) }
    }
}
