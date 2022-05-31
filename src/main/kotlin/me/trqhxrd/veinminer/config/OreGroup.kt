package me.trqhxrd.veinminer.config

import org.bukkit.Material

open class OreGroup(val name: String,val blocks:Set<Material>, val permission: String?, val tools: ToolGroup, val maxSize: Int = 64)
