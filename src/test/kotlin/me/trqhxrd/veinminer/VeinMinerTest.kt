package me.trqhxrd.veinminer

import be.seeseemelk.mockbukkit.MockBukkit
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class VeinMinerTest {

    @BeforeEach
    fun setUp() {
        MockBukkit.mock()
        MockBukkit.load(VeinMiner::class.java)
    }

    @AfterEach
    fun tearDown() {
        MockBukkit.unmock()
    }

    @Test
    fun onEnable() {
        assertTrue(MockBukkit.getMock().pluginManager.isPluginEnabled("VeinMiner"))
    }
}
