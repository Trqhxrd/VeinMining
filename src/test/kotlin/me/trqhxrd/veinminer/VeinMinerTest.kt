package me.trqhxrd.veinminer

import be.seeseemelk.mockbukkit.MockBukkit
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

internal class VeinMinerTest {

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
    fun onEnable() {
        assertTrue(MockBukkit.getMock().pluginManager.isPluginEnabled(plugin))
        assertEquals(plugin, VeinMiner.plugin)
    }

    @Test
    fun onDisable() {
        MockBukkit.getMock().pluginManager.disablePlugin(plugin)
        assertFalse(MockBukkit.getMock().pluginManager.isPluginEnabled(plugin))
    }
}
