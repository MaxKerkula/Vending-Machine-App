package com.techelevator.utility;

public class Console {

    /**
     * Set the console output and background color to CONSOLE DEFAULT
     */
    public static final String ANSI_RESET = "\u001B[0m";
    /**
     * Set the console output color to GREEN
     */
    public static final String ANSI_GREEN = "\u001B[32m";
    /**
     * Set the console output color to RED
     */
    public static final String ANSI_RED = "\u001B[31m";


    public static final String ANSI_BLUE = "\u001b[34m";

    /**
     * Set the console output background color to WHITE
     */
    public static final String WHITE_BACKGROUND = "\u001B[47m";
    /**
     * Set the console output background color to BLACK
     */
    public static final String BLACK_BACKGROUND = "\u001B[40m";
    /**
     * Set the console output background color to GREEN
     */
    public static final String GREEN_BACKGROUND = "\u001B[42m";

    public static final String ANSI_CYAN = "\u001b[36m";

    public static final String ANSI_ORANGE = "\033[38:5:208m";

    /**
     *Returns a string consisting of the provided string repeated
     * the set number times. Most often used for creating ascii
     * line art, separators, and borders for output to the console.
     *
     * @param fillChar the character(s) to repeat
     * @param fillCount the number of times to repeat the character
     * @return A String consisting or the fillChar * fillCount times
     */
    public static String fillText(String fillChar, int fillCount) {

        return fillChar.repeat(fillCount);

    }

}