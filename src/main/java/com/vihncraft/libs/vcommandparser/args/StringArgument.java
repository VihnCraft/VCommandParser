package com.vihncraft.libs.vcommandparser.args;

import com.vihncraft.libs.vcommandparser.argparser.ParseResult;

import java.util.ArrayList;
import java.util.List;

public class StringArgument implements VArgument {
    @Override
    public ParseResult parseArgument(String[] args, VCommandArguments arguments, int argNumber) {
        String first = args[0];
        if (first.startsWith("\"")) {
            return parseQuoted(args, arguments, argNumber);
        } else {
            arguments.setStringArg(argNumber, first);
            return ParseResult.success(1, 1);
        }
    }

    private ParseResult parseQuoted(String[] args, VCommandArguments arguments, int argNumber) {
        String outputBuffer = "";
        int usedArgs = 1;
        String joinedArgs = String.join(" ", args);
        boolean escaped = false;
        boolean reachedEnd = false;

        for (int i = 1; i < joinedArgs.length(); i++) {


            Character _char = joinedArgs.charAt(i);
            if (_char == ' ') {
                usedArgs++;
            }
            if (!escaped) {
                if (_char == '\\') {
                    escaped = true;
                } else if (_char == '"') {
                    reachedEnd = true;
                    break;
                } else {
                    outputBuffer = outputBuffer + _char;
                }
            } else {
                escaped = false;
                outputBuffer = outputBuffer + _char;
            }
        }

        if (!reachedEnd) {
            return ParseResult.failure("arg.string.no_close");
        }

        arguments.setStringArg(argNumber, outputBuffer);

        return ParseResult.success(usedArgs, 1);
    }
}
