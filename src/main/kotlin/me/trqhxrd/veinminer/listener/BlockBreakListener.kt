package me.trqhxrd.veinminer.listener

import me.trqhxrd.veinminer.config.VeinMinerConfig
import me.trqhxrd.veinminer.detectors.DefaultDetector
import me.trqhxrd.veinminer.player.VeinMineUser
import org.bukkit.GameMode
import org.bukkit.block.Block
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.meta.Damageable

class BlockBreakListener : Listener {

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        if (!e.player.isSneaking) return
        val user = VeinMineUser.user(e.player)
        if (!user.veinmineEnabled) return

        val oreGroup = VeinMinerConfig.ores.values.firstOrNull { it.blocks.contains(e.block.type) } ?: return
        if (!oreGroup.tools.tools.contains(e.player.inventory.itemInMainHand.type)) return
        if (oreGroup.permission != null && !e.player.hasPermission(oreGroup.permission)) return

        val detected = mutableSetOf<Block>()
        val detector = DefaultDetector(oreGroup.maxSize)
        detector.scan(e.block, detected)

        var meta: Damageable? = null
        var level = 0

        if (e.player.inventory.itemInMainHand.itemMeta is Damageable) {
            meta = e.player.inventory.itemInMainHand.itemMeta as Damageable
            level = e.player.inventory.itemInMainHand.getEnchantmentLevel(Enchantment.DURABILITY)
        }

        for (block in detected) {
            block.breakNaturally(e.player.inventory.itemInMainHand)
            if (meta != null && e.player.gameMode == GameMode.SURVIVAL) {
                if ((0..level).random() == 0) {
                    meta.damage += 1
                    if (meta.damage > e.player.inventory.itemInMainHand.type.maxDurability - 64) break
                }
            }
        }

        if (meta != null) e.player.inventory.itemInMainHand.itemMeta = meta
    }
}
