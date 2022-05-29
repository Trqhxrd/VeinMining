package me.trqhxrd.veinminer.detectors

import org.bukkit.block.Block

interface Detector {
    fun scan(start: Block, scanned: MutableSet<Block>)
}
