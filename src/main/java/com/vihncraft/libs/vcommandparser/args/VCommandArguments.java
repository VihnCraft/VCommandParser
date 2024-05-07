package com.vihncraft.libs.vcommandparser.args;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class VCommandArguments {
    Map<Integer, Integer> intArgs;

    Map<Integer, String> stringArgs;

    Map<Integer, Float> floatArgs;

    Map<Integer, Player> playerArgs;

    public VCommandArguments() {
        intArgs = new HashMap<>();
        stringArgs = new HashMap<>();
        floatArgs = new HashMap<>();
        playerArgs = new HashMap<>();
    }

    public void setIntArg(int arg, Integer value) {
        intArgs.put(arg, value);
    }

    public Integer getIntArg(int arg) {
        return intArgs.get(arg);
    }

    public void setStringArg(int arg, String value) {
        stringArgs.put(arg, value);
    }

    public String getStringArg(int arg) {
        return stringArgs.get(arg);
    }

    public void setFloatArg(int arg, Float value) {
        floatArgs.put(arg, value);
    }

    public Float getFloatArg(int arg) {
        return floatArgs.get(arg);
    }

    public void setPlayerArg(int arg, Player value) {
        playerArgs.put(arg, value);
    }

    public Player getPlayerArg(int arg) {
        return playerArgs.get(arg);
    }
}
