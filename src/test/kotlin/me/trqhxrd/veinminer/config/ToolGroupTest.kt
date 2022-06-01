package me.trqhxrd.veinminer.config

import be.seeseemelk.mockbukkit.MockBukkit
import me.trqhxrd.veinminer.plugin.Main
import org.bukkit.Material
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

internal class ToolGroupTest {

    private val g1 = ToolGroup("debug", setOf(Material.IRON_SHOVEL, Material.DIAMOND_SHOVEL))
    private val g2 = ToolGroup("debug", setOf(Material.IRON_SHOVEL, Material.DIAMOND_SHOVEL))
    private val g3 = ToolGroup("debug", setOf(Material.IRON_SHOVEL, Material.DIAMOND_PICKAXE))

    @BeforeEach
    fun setUp() {
        MockBukkit.mock()
        MockBukkit.load(Main::class.java)
    }

    @AfterEach
    fun tearDown() {
        MockBukkit.unmock()
    }

    @Test
    fun testEquals() {
        assertTrue(g1.equals(g2))
        assertFalse(g2.equals(g3))
    }

    @Test
    fun testHashCode() {
        assertEquals(g1.hashCode(), g2.hashCode())
        assertNotEquals(g2.hashCode(), g3.hashCode())
    }
}
