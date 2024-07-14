package com.demoappmobile.ansi.colors;


import com.demoappmobile.ansi.AnsiCode;

/**
 * Generates ANSI color codes for text and background colors in console applications. This class offers
 * methods to generate standard, bright, grayscale, and custom RGB color codes as well as background color codes.
 * It implements the {@link AnsiCode} interface, ensuring compatibility with ANSI standards for color coding.
 */
public class AnsiColorGenerator implements AnsiCode {

    private static final String TEXT_COLOR_PREFIX = "\u001B[38;5;";

    private static final String BACKGROUND_COLOR_PREFIX = "\u001B[48;5;";

    private static final String END_SEQUENCE = "m";

    private AnsiColorGenerator() {
    }

    /**
     * Generates a standard ANSI color code for text based on a provided color code.
     *
     * @param colorCode The color code, ranging from 0 to 15, where 0-7 are standard colors and 8-15 are bright colors.
     * @return The ANSI color code string for the specified color.
     * @throws IllegalArgumentException If the color code is not within the valid range.
     */
    public static String getStandardColor(int colorCode) {
        if (colorCode < 0 || colorCode > 15) {
            throw new IllegalArgumentException("Color code must be between 0 and 15.");
        }
        return TEXT_COLOR_PREFIX + colorCode + END_SEQUENCE;
    }

    /**
     * Generates a bright color ANSI code for text, specifically using color codes between 8 and 15.
     *
     * @param colorCode The bright color code, which must be between 8 and 15.
     * @return The ANSI color code string for the specified bright color.
     * @throws IllegalArgumentException If the color code is not within the valid bright color range.
     */
    public static String getBrightColor(int colorCode) {
        if (colorCode < 8 || colorCode > 15) {
            throw new IllegalArgumentException("Bright color code must be between 8 and 15.");
        }
        return TEXT_COLOR_PREFIX + colorCode + END_SEQUENCE;
    }

    /**
     * Generates an ANSI color code for a custom RGB color, where each component (r, g, b) ranges from 0 to 5.
     *
     * @param r The red component of the color.
     * @param g The green component of the color.
     * @param b The blue component of the color.
     * @return The ANSI color code string for the specified RGB color.
     * @throws IllegalArgumentException If any color component is outside the 0-5 range.
     */
    public static String getColorCode(int r, int g, int b) {
        if (r < 0 || r > 5 || g < 0 || g > 5 || b < 0 || b > 5) {
            throw new IllegalArgumentException("Color components (r, g, b) must be in the range 0-5.");
        }
        return TEXT_COLOR_PREFIX + (16 + (r * 36) + (g * 6) + b) + END_SEQUENCE;
    }

    /**
     * Generates a grayscale ANSI color code for the background based on a shade value between 0 and 23.
     *
     * @param shade The grayscale shade value.
     * @return The ANSI color code string for the specified grayscale shade.
     */
    public static String getGrayscaleCode(int shade) {
        shade = Math.min(Math.max(shade, 0), 23);
        return BACKGROUND_COLOR_PREFIX + (232 + shade) + END_SEQUENCE;
    }

    /**
     * Generates an ANSI color code for the background based on a provided color code.
     *
     * @param colorCode The background color code, which must be between 0 and 255.
     * @return The ANSI color code string for the specified background color.
     */
    public static String getBackgroundColor(int colorCode) {
        colorCode = Math.min(Math.max(colorCode, 0), 255);
        return BACKGROUND_COLOR_PREFIX + colorCode + END_SEQUENCE;
    }
}
