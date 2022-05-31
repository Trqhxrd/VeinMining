package me.trqhxrd.veinminer.config.defaults

import me.trqhxrd.veinminer.config.ToolGroup
import org.bukkit.Material.*

object Pickaxes : ToolGroup(
    "pickaxes",
    setOf(
        WOODEN_PICKAXE,
        STONE_PICKAXE,
        IRON_PICKAXE,
        GOLDEN_PICKAXE,
        DIAMOND_PICKAXE,
        NETHERITE_PICKAXE
    )
)

object Axes : ToolGroup(
    "axes",
    setOf(
        WOODEN_AXE,
        STONE_AXE,
        IRON_AXE,
        GOLDEN_AXE,
        DIAMOND_AXE,
        NETHERITE_AXE
    )
)

object Shovels : ToolGroup(
    "shovels",
    setOf(
        WOODEN_SHOVEL,
        STONE_SHOVEL,
        IRON_SHOVEL,
        GOLDEN_SHOVEL,
        DIAMOND_SHOVEL,
        NETHERITE_SHOVEL
    )
)


object Hoes : ToolGroup(
    "hoes",
    setOf(
        WOODEN_HOE,
        STONE_HOE,
        IRON_HOE,
        GOLDEN_HOE,
        DIAMOND_HOE,
        NETHERITE_HOE
    )
)
