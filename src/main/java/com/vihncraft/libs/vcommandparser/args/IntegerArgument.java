package com.vihncraft.libs.vcommandparser.args;

import com.vihncraft.libs.vcommandparser.argparser.ParseResult;

public class IntegerArgument implements VArgument {
    @Override
    public ParseResult parseArgument(String[] args, VCommandArguments arguments, int argNumber) {
        String arg = args[0];
        Integer result;
        try {
            result = Integer.valueOf(arg);
        } catch (NumberFormatException e) {
            return ParseResult.failure("arg.integer.invalid");
        }
        arguments.setIntArg(argNumber, result);
        return ParseResult.success(1, 1);
    }
}
