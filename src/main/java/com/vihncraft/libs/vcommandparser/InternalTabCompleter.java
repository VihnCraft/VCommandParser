package com.vihncraft.libs.vcommandparser;

import com.vihncraft.libs.vcommandparser.argparser.ArgParser;
import com.vihncraft.libs.vcommandparser.args.VCommandArguments;
import com.vihncraft.libs.vcommandparser.command.VCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InternalTabCompleter implements TabCompleter {

    VCommand command;
    public InternalTabCompleter(VCommand command) {
        super();
        this.command = command;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command _command, @NotNull String s, @NotNull String[] strings) {
        // Complete arguments
        VCommandArguments arguments = new VCommandArguments();
        ArgParser parser = new ArgParser(strings, arguments, this.command.getArguments(), commandSender, false);
        return parser.autoComplete();
    }
}
