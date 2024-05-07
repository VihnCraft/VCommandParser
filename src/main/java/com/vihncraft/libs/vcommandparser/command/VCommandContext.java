package com.vihncraft.libs.vcommandparser.command;

import com.vihncraft.libs.vcommandparser.args.VCommandArguments;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VCommandContext {

    VCommandArguments arguments;

    Player player;

    CommandSender sender;

    public VCommandContext(Player player, @NotNull VCommandArguments arguments, @NotNull CommandSender sender) {
        this.player = player;
        this.arguments = arguments;
        this.sender = sender;
    }

    /**
     * Get the player who ran the command. Will return null if ran by non-player sender (e.g. console).
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the arguments. You can use the .get%Type%Arg(int arg) function to get an argument, where %Type% is the type of argument.
     */
    public VCommandArguments getArguments() {
        return arguments;
    }

    /**
     * Send a message to the player.
     */
    public void sendMessage(String text) {
        sender.sendMessage(text);
    }

    /**
     * Send a *styled* message to the player.
     */
    public void sendMessage(Component text) {
        sender.sendMessage(text);
    }

}
