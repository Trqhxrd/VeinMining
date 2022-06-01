package me.trqhxrd.veinminer.plugin.command

import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.entity.PlayerMock
import me.trqhxrd.veinminer.player.VeinMineUser
import me.trqhxrd.veinminer.plugin.Main
import org.bukkit.plugin.java.JavaPlugin
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class VeinMineCommandTest {

    private lateinit var plugin: JavaPlugin

    @BeforeEach
    fun setUp() {
        MockBukkit.mock()
        this.plugin = MockBukkit.load(Main::class.java)
    }

    @AfterEach
    fun tearDown() {
        MockBukkit.unmock()
    }

    @Test
    fun onTabComplete() {
        val p = MockBukkit.getMock().addPlayer("Notch")
        val command = this.plugin.getCommand("veinminer")!!
        val completer = command.tabCompleter!!
        assertTrue(completer.onTabComplete(p, command, "vm", arrayOf(""))!!.isNotEmpty())
        assertTrue(completer.onTabComplete(p, command, "vm", arrayOf())!!.isEmpty())
    }

    @Test
    fun onCommand() {
        val p = MockBukkit.getMock().addPlayer("Notch")
        p.performCommand("vm")
        assertDoesNotThrow { p.nextMessage() }
        this.flushMessages(p)

        val user = VeinMineUser.user(p)
        val state = user.veinmineEnabled
        p.performCommand("vm toggle")
        assertEquals(user.veinmineEnabled, !state)
        assertDoesNotThrow { p.nextMessage() }
        this.flushMessages(p)
    }

    private fun flushMessages(p: PlayerMock) {
        while (true) if (p.nextMessage() == null) break
    }
}
