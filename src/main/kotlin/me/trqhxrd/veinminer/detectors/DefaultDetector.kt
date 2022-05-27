package me.trqhxrd.veinminer.detectors

import org.bukkit.block.Block
import org.bukkit.block.BlockFace

class DefaultDetector(val maxAmount: Int = 64) : Detector {

    override fun scan(start: Block, scanned: MutableSet<Block>) {
        for (face in BlockFace.values().filter { it.isCartesian }) {
            if (scanned.size >= this.maxAmount) break
            val b = start.getRelative(face)
            if (scanned.contains(b)) continue
            if (b.type != start.type) continue
            scanned.add(b)
            this.scan(b, scanned)
        }
    }
}
