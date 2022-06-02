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
        val itemInHand = e.player.inventory.itemInMainHand

        val oreGroup = VeinMinerConfig.ores.values.firstOrNull { it.blocks.contains(e.block.type) } ?: return
        if (!oreGroup.tools.tools.contains(itemInHand.type)) return
        if (oreGroup.permission != null && !e.player.hasPermission(oreGroup.permission)) return

        val detected = mutableSetOf<Block>()
        val detector = DefaultDetector(oreGroup.maxSize)
        detector.scan(e.block, detected)

        var meta: Damageable? = null
        var level = 0

        if (itemInHand.itemMeta is Damageable) {
            meta = itemInHand.itemMeta as Damageable
            level = itemInHand.getEnchantmentLevel(Enchantment.DURABILITY)
        }

        for (block in detected) {
            block.breakNaturally(itemInHand)
            if (meta != null && e.player.gameMode == GameMode.SURVIVAL) {
                if ((0..level).random() == 0) {
                    meta.damage += 1
                    if (meta.damage > itemInHand.type.maxDurability - 64) break
                }
            }
        }

        if (meta != null) itemInHand.itemMeta = meta
    }
}
