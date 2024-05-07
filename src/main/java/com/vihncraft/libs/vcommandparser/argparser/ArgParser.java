package com.vihncraft.libs.vcommandparser.argparser;

import com.vihncraft.libs.vcommandparser.VCommandParser;
import com.vihncraft.libs.vcommandparser.args.VArgument;
import com.vihncraft.libs.vcommandparser.args.VCommandArguments;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArgParser {

    String[] args;
    int currentArg;
    int currentOutputArg;
    VCommandArguments outputArgs;

    VArgument[] argumentsToProcess;
    int argumentId;

    CommandSender sender;
    boolean loud;

    public ArgParser(String[] args, VCommandArguments outputArgs, VArgument[] argumentsToProcess, CommandSender sender, boolean loud) {
        this.args = args;
        this.outputArgs = outputArgs;
        this.argumentsToProcess = argumentsToProcess;

        this.currentArg = 0;
        this.currentOutputArg = 0;
        this.argumentId = 0;

        this.sender = sender;
        this.loud = loud;
    }

    public boolean parseAll() {
        while (true) {
            boolean success = parseNext();
            VCommandParser.LOGGER.info(String.format("%d - %d", argumentId, argumentsToProcess.length));
            if (!success) {
                return false;
            }
            if (argumentId == argumentsToProcess.length) {
                break;
            }
        }
        if (currentArg != args.length) {
            sendMessage("parsing.too_many_args");
            return false;
        } else {
            return true;
        }
    }

    public boolean parseNext() {
        String[] nextArgs;
        try {
            nextArgs = Arrays.stream(args, currentArg, args.length).toArray(String[]::new);
        } catch (ArrayIndexOutOfBoundsException e) {
            return expectedAnotherArgument();
        }
        if (nextArgs.length == 0) {
            return expectedAnotherArgument();
        }

        ParseResult result = argumentsToProcess[argumentId].parseArgument(nextArgs, outputArgs, currentOutputArg);
        if (!result.success) {
            sendMessage(result.errorMessage);
            return false;
        }

        argumentId++;
        currentArg =+ result.usedArgs;
        currentOutputArg =+ result.outputtedArgs;

        return true;
    }

    private boolean expectedAnotherArgument() {
        sendMessage("parsing.too_few_args");
        return false;
    }

    private void sendMessage(String message) {
        if (loud) {
            sender.sendMessage(VCommandParser.getMessage(message));
        }
    }

    public List<String> autoComplete() {
        while (true) {
            boolean success = parseNext();
            if (!success) {
                return autoCompleteNext();
            }
            if (argumentId == argumentsToProcess.length) {
                return new ArrayList<>();
            }
        }
    }

    public List<String> autoCompleteNext() {
        String[] nextArgs;
        try {
            nextArgs = Arrays.stream(args, currentArg, args.length).toArray(String[]::new);
        } catch (ArrayIndexOutOfBoundsException e) {
            nextArgs = new String[] {};
        }

        return argumentsToProcess[argumentId].tabComplete(nextArgs);
    }
}
