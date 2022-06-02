package me.trqhxrd.veinminer.config

import be.seeseemelk.mockbukkit.MockBukkit
import me.trqhxrd.veinminer.plugin.Main
import org.bukkit.Material
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class VeinMinerConfigTest {

    lateinit var plugin: Main
    private val debugConfig =
        """
            veinminer:
              tools:
                debug:
                  tools:
                  - IRON_SHOVEL
                  - DIAMOND_SHOVEL
              ores:
                debug:
                  maxSize: 64
                  tools: debug
                  materials:
                  - SAND

        """.trimIndent()

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
            debugConfig,
            this.plugin.config.saveToString()
        )
    }

    @Test
    fun load() {
        VeinMinerConfig.tools.clear()
        VeinMinerConfig.ores.clear()
        VeinMinerConfig.save(this.plugin)

        assertTrue(VeinMinerConfig.tools.isEmpty())
        assertTrue(VeinMinerConfig.ores.isEmpty())

        this.plugin.config.loadFromString(debugConfig)
        VeinMinerConfig.load(this.plugin)

        assertTrue(VeinMinerConfig.tools.containsKey("debug"))
        assertEquals(
            ToolGroup("debug", setOf(Material.IRON_SHOVEL, Material.DIAMOND_SHOVEL)),
            VeinMinerConfig.tools["debug"]
        )
    }
}
