package com.vihncraft.libs.vcommandparser.args;

import com.vihncraft.libs.vcommandparser.argparser.ParseResult;

public class GreedyStringArgument implements VArgument {
    @Override
    public ParseResult parseArgument(String[] args, VCommandArguments arguments, int argNumber) {
        String joinedString = String.join(" ", args);
        arguments.setStringArg(argNumber, joinedString);
        return ParseResult.success(args.length, 1);
    }
}
