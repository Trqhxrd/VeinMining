package me.trqhxrd.veinminer

import be.seeseemelk.mockbukkit.MockBukkit
import org.bukkit.Material
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

internal class VeinMinerTest {

    lateinit var plugin: VeinMiner

    @BeforeEach
    fun setUp() {
        MockBukkit.mock()
        plugin = MockBukkit.load(VeinMiner::class.java)
    }

    @AfterEach
    fun tearDown() {
        MockBukkit.unmock()
    }

    @Test
    fun onEnable() {
        assertTrue(MockBukkit.getMock().pluginManager.isPluginEnabled(plugin))
        assertEquals(plugin, VeinMiner.instance)
    }

    @Test
    fun onDisable() {
        MockBukkit.getMock().pluginManager.disablePlugin(plugin)
        assertFalse(MockBukkit.getMock().pluginManager.isPluginEnabled(plugin))
    }
}
