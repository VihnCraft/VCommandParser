package com.vihncraft.libs.vcommandparser;

import com.vihncraft.libs.vcommandparser.command.VCommandContext;
import com.vihncraft.libs.vcommandparser.command.VCommandExecutor;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ConfigCommandExecutor implements VCommandExecutor {
    @Override
    public void onExecute(VCommandContext commandContext) {
        String option = commandContext.getArguments().getStringArg(0);

        if (option.equals("reload")) {

            commandContext.sendMessage("Reloading...");
            VCommandParser.PLUGIN.loadConfig();
            commandContext.sendMessage("Reloaded.");

        } else if (option.equals("about")) {

            commandContext.sendMessage(MiniMessage.miniMessage().deserialize(
                    "You can learn about VCommandParser on the <dark_aqua><underlined><click:open_url:https://github.com/VihnCraft/VCommandParser>GitHub repo<reset>"
                            +" or <dark_aqua><underlined><click:open_url:https://modrinth.com/plugin/vcommandparser>Modrinth page<reset>. VCommandParser was created for the "
                            +"<dark_aqua><underlined><click:open_url:http://vihncraft.com>VihnCraft<reset> Minecraft Server, and is maintained by it's dev team."
            ));

        }
    }
}
