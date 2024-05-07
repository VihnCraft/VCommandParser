package com.vihncraft.libs.vcommandparser;

import net.kyori.adventure.text.Component;

public interface ServerHook {
    public default Component getMessage(String key, Component configMessage) {
        return configMessage;
    };
}
