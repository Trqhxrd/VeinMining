package me.trqhxrd.veinminer.listener

import be.seeseemelk.mockbukkit.MockBukkit
import me.trqhxrd.veinminer.player.VeinMineUser
import me.trqhxrd.veinminer.plugin.Main
import org.bukkit.Bukkit
import org.bukkit.event.player.PlayerQuitEvent
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class PlayerQuitListenerTest {

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
    fun onPlayerQuit() {
        val p = MockBukkit.getMock().addPlayer("Notch")
        assertTrue(VeinMineUser.users.isNotEmpty())

        val event = PlayerQuitEvent(p, "§eThey left D:")
        Bukkit.getPluginManager().callEvent(event)

        val users = VeinMineUser.users
        assertTrue(VeinMineUser.users.isEmpty())
    }
}
