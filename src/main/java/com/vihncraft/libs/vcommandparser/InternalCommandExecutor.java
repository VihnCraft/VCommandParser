package com.vihncraft.libs.vcommandparser;

import com.vihncraft.libs.vcommandparser.argparser.ArgParser;
import com.vihncraft.libs.vcommandparser.args.VCommandArguments;
import com.vihncraft.libs.vcommandparser.command.VCommand;
import com.vihncraft.libs.vcommandparser.command.VCommandContext;
import com.vihncraft.libs.vcommandparser.command.VCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class InternalCommandExecutor implements CommandExecutor {

    VCommand command;
    public InternalCommandExecutor(VCommand command) {
        super();
        this.command = command;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command _command, @NotNull String s, @NotNull String[] strings) {
        // Get player and check command sender
        Player player = null;
        if (commandSender instanceof Player) {
            player = (Player) commandSender;
            if (command.getCommandSender() == VCommandSender.Console) {
                commandSender.sendMessage("Only the console and command blocks can execute this command.");
                return false;
            }
        } else if (command.getCommandSender() == VCommandSender.Player) {
            commandSender.sendMessage("Only players can send this command.");
            return false;
        }

        // Parse arguments
        VCommandArguments arguments = new VCommandArguments();
        ArgParser parser = new ArgParser(strings, arguments, this.command.getArguments(), commandSender, true);
        boolean success = parser.parseAll();

        // Run command
        if (success) {
            VCommandContext commandContext = new VCommandContext(player, arguments, commandSender);
            command.execute(commandContext);
        }
        return false;
    }
}
