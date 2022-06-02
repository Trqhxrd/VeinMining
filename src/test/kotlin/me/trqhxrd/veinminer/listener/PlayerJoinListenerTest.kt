package me.trqhxrd.veinminer.listener

import be.seeseemelk.mockbukkit.MockBukkit
import me.trqhxrd.veinminer.player.VeinMineUser
import me.trqhxrd.veinminer.plugin.Main
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

internal class PlayerJoinListenerTest {

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
    fun onPlayerJoin() {
        assertTrue(VeinMineUser.users.isEmpty())
        val p = MockBukkit.getMock().addPlayer("Notch")
        assertDoesNotThrow() { VeinMineUser.user(p) }
    }
}
