package com.vihncraft.libs.vcommandparser.args;

import com.vihncraft.libs.vcommandparser.argparser.ParseResult;

import java.util.ArrayList;
import java.util.List;

public interface VArgument {
    public ParseResult parseArgument(String[] args, VCommandArguments arguments, int argNumber);

    public default List<String> tabComplete(String[] args) {
        return new ArrayList<>();
    };
}
