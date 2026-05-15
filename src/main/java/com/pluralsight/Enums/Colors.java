package com.pluralsight.Enums;

/**
 * Represents ANSI RGB colors used for styling console output.
 *
 * Each enum constant defines a color using RGB values and provides
 * utility methods to apply that color to text.
 */
public enum Colors {

    TRON(125, 253, 254),
    ORANGE_JUICE(255,128,0),
    GOLD(255, 215, 0),
    GREEN(76, 175, 80),
    PURPLE(168, 85, 247),
    AMBER(255,191, 0),
    CRIMSON(220, 20, 60),
    CHAMPAGNE_SILVER(210, 200, 190),
    MUTED_GRAY(156, 163, 175);

    // ANSI escape code used to reset console color formatting.
    public static final String RESET = "\u001B[0m";

    // RGB values for the color
    private final int R;
    private final int G;
    private final int B;

    Colors(int R, int G, int B) {
        this.R = R;
        this.G = G;
        this.B = B;
    }

    /**
     * Wraps the given text in this color and resets formatting afterward.
     *
     * @param text the text to color
     * @return the colored text with ANSI escape codes applied
     */
    public String colorize(String text) {
        return getCode() + text + RESET;
    }

    /**
     * Returns the ANSI escape code for this color using 24-bit RGB formatting.
     *
     * Format: \u001B[38;2;R;G;Bm
     *
     * @return the ANSI color code string
     */
    public String getCode() {
        return "\u001B[38;2;" + R + ";" + G + ";" + B + "m";
    }
}
