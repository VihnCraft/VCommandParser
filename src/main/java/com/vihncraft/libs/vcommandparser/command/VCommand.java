package com.vihncraft.libs.vcommandparser.command;

import com.vihncraft.libs.vcommandparser.InternalCommandExecutor;
import com.vihncraft.libs.vcommandparser.InternalTabCompleter;
import com.vihncraft.libs.vcommandparser.args.VArgument;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class VCommand {

    String name;

    VCommandSender commandSender;

    VCommandExecutor commandExecutor;

    List<VArgument> arguments;

    public VCommand(@NotNull String name, @NotNull VCommandSender commandSender, @NotNull VCommandExecutor commandExecutor) {
        this.name = name;
        this.commandSender = commandSender;
        this.commandExecutor = commandExecutor;
        this.arguments = new ArrayList<>();
    }

    /**
     * Register this command to your plugin. Make sure to include it in plugin.yml!
     */
    public void register(@NotNull JavaPlugin plugin) {
        plugin.getCommand(this.name).setExecutor(new InternalCommandExecutor(this));
        plugin.getCommand(this.name).setTabCompleter(new InternalTabCompleter(this));
    }

    public @NotNull VCommandSender getCommandSender() {
        return commandSender;
    }

    /**
     * Execute the command with a given context. Arguments should be verified to be correct before execution, or you may encounter unexpected behaviour.
     */
    public void execute(VCommandContext commandContext) {
        this.commandExecutor.onExecute(commandContext);
    }

    /**
     * Add an argument to the command.
     */
    public void addArgument(VArgument argument) {
        arguments.add(argument);
    }

    public VArgument[] getArguments() {
        return arguments.toArray(new VArgument[0]);
    }
}
