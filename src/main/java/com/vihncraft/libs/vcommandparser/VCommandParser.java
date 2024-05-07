package com.vihncraft.libs.vcommandparser;

import com.vihncraft.libs.vcommandparser.args.OptionArgument;
import com.vihncraft.libs.vcommandparser.command.VCommand;
import com.vihncraft.libs.vcommandparser.command.VCommandContext;
import com.vihncraft.libs.vcommandparser.command.VCommandSender;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class VCommandParser extends JavaPlugin {

    public static Logger LOGGER;

    public static VCommandParser PLUGIN;

    public static FileConfiguration config;

    private ServerHook hook;

    @Override
    public void onEnable() {
        LOGGER = getLogger();
        PLUGIN = this;
        hook = null;

        // Welcome message
        LOGGER.info(String.format("VCommandParser version %s is installed.", getDescription().getVersion()));

        // Load config
        loadConfig();

        // Register commands
        VCommand configCommand = new VCommand("vcommandparser", VCommandSender.Player, new ConfigCommandExecutor());
        configCommand.addArgument(
                new OptionArgument(new String[] {
                        "reload",
                        "about"
                })
        );
        configCommand.register(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        LOGGER.info("Goodbye from VCommandParser.");
    }

    public void loadConfig() {
        saveDefaultConfig(); // Make sure config exists
        FileConfiguration config = getConfig();
        int configVersion = config.getInt("config-version");
        if (configVersion != 0) {
            LOGGER.severe("CONFIG OUT OF DATE: THERE MAY BE ISSUES");
            LOGGER.severe("CONFIG OUT OF DATE: THERE MAY BE ISSUES");
            LOGGER.severe("CONFIG OUT OF DATE: THERE MAY BE ISSUES");
            LOGGER.severe("CONFIG OUT OF DATE: THERE MAY BE ISSUES");
            LOGGER.severe("CONFIG OUT OF DATE: THERE MAY BE ISSUES");
        }
        VCommandParser.config = config;
    }

    public static Component getMessage(String key) {
        String message = config.getString(String.format("messages.%s", key));
        Component outMsg;
        if (message != null) {
            outMsg = MiniMessage.miniMessage().deserialize(message);
        } else {
            outMsg = Component.text(key);
        }
        if (VCommandParser.PLUGIN.hook != null) {
            return VCommandParser.PLUGIN.hook.getMessage(key, outMsg);
        } else {
            return outMsg;
        }
    }

    /**
     * Register your server hook. Only one hook can be applied per server, and the server-hook-enabled config option must be enabled.
     * @param hook The server hook to be registered.
     * @return boolean - If the hook was applied.
     */
    public static boolean registerServerHook(ServerHook hook) {
        if (VCommandParser.PLUGIN.hook == null) {
            if (VCommandParser.PLUGIN.getConfig().getBoolean("server-hook-enabled")) {
                VCommandParser.PLUGIN.hook = hook;
                return true;
            }
        }
        return false;
    }
}
