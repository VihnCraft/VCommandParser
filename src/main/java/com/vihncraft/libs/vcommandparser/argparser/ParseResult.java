package com.vihncraft.libs.vcommandparser.argparser;

public class ParseResult {
    int usedArgs;
    int outputtedArgs;
    boolean success;
    String errorMessage;

    private ParseResult(int usedArgs, int outputtedArgs, boolean success, String errorMessage) {
        this.usedArgs = usedArgs;
        this.outputtedArgs = outputtedArgs;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public static ParseResult success(int usedArgs, int outputtedArgs) {
        return new ParseResult(usedArgs, outputtedArgs, true, null);
    }

    public static ParseResult failure(String errorMessage) {
        return new ParseResult(0, 0, false, errorMessage);
    }
}
