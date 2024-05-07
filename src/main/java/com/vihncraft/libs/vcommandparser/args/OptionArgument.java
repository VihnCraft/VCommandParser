package com.vihncraft.libs.vcommandparser.args;

import com.vihncraft.libs.vcommandparser.argparser.ParseResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionArgument implements VArgument {

    String[] options;

    public OptionArgument(String[] options) {
        this.options = options;
    }

    @Override
    public ParseResult parseArgument(String[] args, VCommandArguments arguments, int argNumber) {
        String pickedOption = args[0];

        for (String allowedOption : options) {
            if (pickedOption.equals(allowedOption)) {
                arguments.setStringArg(argNumber, pickedOption);
                return ParseResult.success(1, 1);
            }
        }

        return ParseResult.failure("arg.option.invalid");
    }

    @Override
    public List<String> tabComplete(String[] args) {
        return Arrays.asList(options);
    }
}
