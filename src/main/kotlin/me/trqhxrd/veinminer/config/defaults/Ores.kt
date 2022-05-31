package me.trqhxrd.veinminer.config.defaults

import me.trqhxrd.veinminer.config.OreGroup
import org.bukkit.Material.*

object Ores : OreGroup(
    "ores",
    setOf(
        COPPER_ORE,
        COAL_ORE,
        IRON_ORE,
        GOLD_ORE,
        EMERALD_ORE,
        LAPIS_ORE,
        REDSTONE_ORE,
        DIAMOND_ORE,
        DEEPSLATE_COAL_ORE,
        DEEPSLATE_IRON_ORE,
        DEEPSLATE_GOLD_ORE,
        DEEPSLATE_EMERALD_ORE,
        DEEPSLATE_LAPIS_ORE,
        DEEPSLATE_REDSTONE_ORE,
        DEEPSLATE_DIAMOND_ORE,
        ANCIENT_DEBRIS,
        NETHER_QUARTZ_ORE,
        NETHER_GOLD_ORE
    ),
    "veinminer.mine.ores",
    Pickaxes
)

object Wood : OreGroup(
    "wood",
    setOf(
        ACACIA_LOG,
        BIRCH_LOG,
        DARK_OAK_LOG,
        JUNGLE_LOG, OAK_LOG,
        SPRUCE_LOG,
        CRIMSON_STEM,
        WARPED_STEM
    ),
    "veinminer.mine.woods",
    Axes
)

object Sand : OreGroup(
    "sand",
    setOf(
        SAND,
        RED_SAND,
        GRAVEL
    ),
    "veinminer.mine.sands",
    Shovels
)

object Leaves : OreGroup(
    "leaves",
    setOf(
        ACACIA_LEAVES,
        BIRCH_LEAVES,
        DARK_OAK_LEAVES,
        JUNGLE_LEAVES,
        OAK_LEAVES,
        SPRUCE_LEAVES,
        NETHER_WART_BLOCK,
        WARPED_WART_BLOCK,
        HAY_BLOCK
    ),
    "veinminer.mine.leaves",
    Hoes
)
