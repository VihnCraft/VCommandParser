package com.vihncraft.libs.vcommandparser.args;

import com.vihncraft.libs.vcommandparser.argparser.ParseResult;

public class FloatArgument implements VArgument {
    @Override
    public ParseResult parseArgument(String[] args, VCommandArguments arguments, int argNumber) {
        String arg = args[0];
        Float result;
        try {
            result = Float.valueOf(arg);
        } catch (NumberFormatException e) {
            return ParseResult.failure("arg.float.invalid");
        }
        arguments.setFloatArg(argNumber, result);
        return ParseResult.success(1, 1);
    }
}
