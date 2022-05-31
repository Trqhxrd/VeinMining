package me.trqhxrd.veinminer.config

import be.seeseemelk.mockbukkit.MockBukkit
import me.trqhxrd.veinminer.Main
import org.bukkit.Material
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class VeinMinerConfigTest {

    lateinit var plugin: Main

    @BeforeEach
    fun setUp() {
        MockBukkit.mock()
        plugin = MockBukkit.load(Main::class.java)
    }

    @AfterEach
    fun tearDown() {
        MockBukkit.unmock()
    }

    @Test
    fun save() {
        VeinMinerConfig.tools.clear()
        VeinMinerConfig.ores.clear()
        VeinMinerConfig.tools["debug"] = ToolGroup("debug", setOf(Material.IRON_SHOVEL, Material.DIAMOND_SHOVEL))
        VeinMinerConfig.ores["debug"] = OreGroup("debug", setOf(Material.SAND), null, VeinMinerConfig.tools["debug"]!!)

        VeinMinerConfig.save(plugin)

        assertEquals(
            """
            veinminer:
              tools:
                debug:
                  tools:
                  - IRON_SHOVEL
                  - DIAMOND_SHOVEL
              materials:
                debug:
                  maxSize: 64
                  tools: debug
                  blocks:
                  - SAND

        """.trimIndent(),
            this.plugin.config.saveToString()
        )
    }

    @Test
    fun load() {
        assertTrue(this.plugin.config.get("veinminer.tools.pickaxes") != null)
    }
}
