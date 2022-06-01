package me.trqhxrd.veinminer.listener

import be.seeseemelk.mockbukkit.MockBukkit
import me.trqhxrd.veinminer.plugin.Main
import me.trqhxrd.veinminer.player.VeinMineUser
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BlockBreakListenerTest {

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
    fun breakOre() {
        val world = MockBukkit.getMock().addSimpleWorld("debug")
        val blocks = buildSet<Block> {
            for (x in 0..9)
                for (y in 0..9)
                    for (z in 0..9)
                        this.add(world.getBlockAt(x, y, z))
        }

        blocks.forEach { it.type = Material.COAL_ORE }

        val block = world.getBlockAt(0, 9, 0)
        val p = MockBukkit.getMock().addPlayer()
        p.teleport(Location(world, 0.0, 10.0, 0.0))
        breakBlock(p, block)

        assertEquals(Material.AIR, block.type)
        assertEquals(999, blocks.filter { it.type == Material.COAL_ORE }.size)
    }

    @Test
    fun breakSneak() {
        val world = MockBukkit.getMock().addSimpleWorld("debug")
        val blocks = buildSet<Block> {
            for (x in 0..9)
                for (y in 0..9)
                    for (z in 0..9)
                        this.add(world.getBlockAt(x, y, z))
        }

        blocks.forEach { it.type = Material.SPONGE }

        val block = world.getBlockAt(0, 9, 0)
        val p = MockBukkit.getMock().addPlayer()
        p.isSneaking = true
        p.teleport(Location(world, 0.0, 10.0, 0.0))
        breakBlock(p, block)

        assertEquals(Material.AIR, block.type)
        assertEquals(999, blocks.filter { it.type == Material.SPONGE }.size)
    }

    @Test
    fun breakSneakOre() {
        val world = MockBukkit.getMock().addSimpleWorld("debug")
        val blocks = buildSet<Block> {
            for (x in 0..9)
                for (y in 0..9)
                    for (z in 0..9)
                        this.add(world.getBlockAt(x, y, z))
        }

        blocks.forEach { it.type = Material.COAL_ORE }

        val block = world.getBlockAt(0, 9, 0)
        val p = MockBukkit.getMock().addPlayer()
        p.isSneaking = true
        p.teleport(Location(world, 0.0, 10.0, 0.0))
        p.inventory.setItemInMainHand(ItemStack(Material.IRON_PICKAXE))
        breakBlock(p, block)

        assertEquals(Material.AIR, block.type)
        assertEquals(999, blocks.filter { it.type == Material.COAL_ORE }.size)
    }

    @Test
    fun breakPermissionSneakOre() {
        val world = MockBukkit.getMock().addSimpleWorld("debug")
        val blocks = buildSet<Block> {
            for (x in 0..9)
                for (y in 0..9)
                    for (z in 0..9)
                        this.add(world.getBlockAt(x, y, z))
        }

        blocks.forEach { it.type = Material.COAL_ORE }

        val block = world.getBlockAt(0, 9, 0)
        val p = MockBukkit.getMock().addPlayer()
        p.isSneaking = true
        p.isOp = true
        p.teleport(Location(world, 0.0, 10.0, 0.0))
        p.inventory.setItemInMainHand(ItemStack(Material.IRON_PICKAXE))
        breakBlock(p, block)

        assertEquals(Material.AIR, block.type)
        assertEquals(935, blocks.filter { it.type == Material.COAL_ORE }.size)
    }

    @Test
    fun breakBlockVeinMineToggleOff() {
        val world = MockBukkit.getMock().addSimpleWorld("debug")
        val blocks = buildSet<Block> {
            for (x in 0..9)
                for (y in 0..9)
                    for (z in 0..9)
                        this.add(world.getBlockAt(x, y, z))
        }

        blocks.forEach { it.type = Material.COAL_ORE }

        val block = world.getBlockAt(0, 9, 0)
        val p = MockBukkit.getMock().addPlayer()
        p.isSneaking = true
        p.isOp = true
        p.teleport(Location(world, 0.0, 10.0, 0.0))
        VeinMineUser.user(p).veinmineEnabled = false
        p.inventory.setItemInMainHand(ItemStack(Material.IRON_PICKAXE))
        breakBlock(p, block)

        assertEquals(Material.AIR, block.type)
        assertEquals(999, blocks.filter { it.type == Material.COAL_ORE }.size)
    }

    private fun breakBlock(player: Player, block: Block) {
        val event = BlockBreakEvent(block, player)
        MockBukkit.getMock().pluginManager.callEvent(event)
        if (event.isCancelled) return
        event.block.breakNaturally(player.inventory.itemInMainHand)
    }
}
