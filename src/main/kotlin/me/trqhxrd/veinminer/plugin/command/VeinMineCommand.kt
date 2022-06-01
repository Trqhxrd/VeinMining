package me.trqhxrd.veinminer.plugin.command

import me.trqhxrd.veinminer.player.VeinMineUser
import net.md_5.bungee.api.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class VeinMineCommand(plugin: JavaPlugin) : TabExecutor {

    init {
        val cmd = plugin.getCommand("veinminer")!!
        cmd.setExecutor(this)
        cmd.tabCompleter = this
    }

    /**
     * Requests a list of possible completions for a command argument.
     *
     * @param sender Source of the command.  For players tab-completing a
     * command inside of a command block, this will be the player, not
     * the command block.
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args The arguments passed to the command, including final
     * partial argument to be completed
     * @return A List of possible completions for the final argument, or null
     * to default to the command executor
     */
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ) = when (args.size) {
        1 -> mutableListOf("toggle")
        else -> mutableListOf<String>()
    }

    /**
     * Executes the given command, returning its success.
     * <br></br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return true if a valid command, otherwise false
     */
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return true
        when (args.size) {
            1 -> if (args[0].lowercase() == "toggle" || args[0].lowercase() == "t") {
                val user = VeinMineUser.user(sender)
                user.veinmineEnabled = !user.veinmineEnabled
            } else this.help(sender)
            else -> this.help(sender)
        }
        return true
    }

    private fun msg(sender: CommandSender, msg: String) = sender.sendMessage(
        ChatColor.translateAlternateColorCodes('&', "&2[&aVeinMiner&2] &a$msg")
    )

    private fun help(sender: CommandSender) {
        this.msg(sender, "&lHelp:")
        this.msg(sender, "/vm toggle &7- &aEnables or disables the veinminer.")
    }
}
