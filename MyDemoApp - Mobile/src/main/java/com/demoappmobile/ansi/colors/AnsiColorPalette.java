package com.demoappmobile.ansi.colors;

import com.demoappmobile.ansi.AnsiCode;
import com.demoappmobile.ansi.Style.AnsiStyle;

/**
 * Enumerates a palette of ANSI color codes for text and background styling in console applications.
 * This palette includes standard, bold, underlined, and bright variations of basic colors, as well as combinations
 * of bold and underlined styles. It leverages the {@link AnsiColorGenerator} to generate the necessary ANSI codes
 * and provides methods to apply these color styles to text.
 */
public enum AnsiColorPalette implements AnsiCode {
    BLACK(AnsiColorGenerator.getStandardColor(0)),
    RED(AnsiColorGenerator.getStandardColor(1)),
    GREEN(AnsiColorGenerator.getStandardColor(2)),
    YELLOW(AnsiColorGenerator.getStandardColor(3)),
    BLUE(AnsiColorGenerator.getStandardColor(4)),
    MAGENTA(AnsiColorGenerator.getStandardColor(5)),
    CYAN(AnsiColorGenerator.getStandardColor(6)),
    WHITE(AnsiColorGenerator.getStandardColor(7)),

    BLACK_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(0)),
    RED_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(1)),
    GREEN_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(2)),
    YELLOW_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(3)),
    BLUE_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(4)),
    MAGENTA_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(5)),
    CYAN_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(6)),
    WHITE_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(7)),

    BLACK_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(0)),
    RED_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(1)),
    GREEN_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(2)),
    YELLOW_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(3)),
    BLUE_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(4)),
    MAGENTA_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(5)),
    CYAN_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(6)),
    WHITE_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(7)),

    BLACK_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(0)),
    RED_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(1)),
    GREEN_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(2)),
    YELLOW_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(3)),
    BLUE_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(4)),
    MAGENTA_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(5)),
    CYAN_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(6)),
    WHITE_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(7)),

    BLACK_BRIGHT(AnsiColorGenerator.getBrightColor(8)),
    RED_BRIGHT(AnsiColorGenerator.getBrightColor(9)),
    GREEN_BRIGHT(AnsiColorGenerator.getBrightColor(10)),
    YELLOW_BRIGHT(AnsiColorGenerator.getBrightColor(11)),
    BLUE_BRIGHT(AnsiColorGenerator.getBrightColor(12)),
    MAGENTA_BRIGHT(AnsiColorGenerator.getBrightColor(13)),
    CYAN_BRIGHT(AnsiColorGenerator.getBrightColor(14)),
    WHITE_BRIGHT(AnsiColorGenerator.getBrightColor(15)),

    BLACK_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(8)),
    RED_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(9)),
    GREEN_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(10)),
    YELLOW_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(11)),
    BLUE_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(12)),
    MAGENTA_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(13)),
    CYAN_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(14)),
    WHITE_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(15)),

    BLACK_BACKGROUND(AnsiColorGenerator.getBackgroundColor(0)),
    RED_BACKGROUND(AnsiColorGenerator.getBackgroundColor(1)),
    GREEN_BACKGROUND(AnsiColorGenerator.getBackgroundColor(2)),
    YELLOW_BACKGROUND(AnsiColorGenerator.getBackgroundColor(3)),
    BLUE_BACKGROUND(AnsiColorGenerator.getBackgroundColor(4)),
    MAGENTA_BACKGROUND(AnsiColorGenerator.getBackgroundColor(5)),
    CYAN_BACKGROUND(AnsiColorGenerator.getBackgroundColor(6)),
    WHITE_BACKGROUND(AnsiColorGenerator.getBackgroundColor(7)),

    BLACK_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(8)),
    RED_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(9)),
    GREEN_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(10)),
    YELLOW_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(11)),
    BLUE_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(12)),
    MAGENTA_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(13)),
    CYAN_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(14)),
    WHITE_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(15));

    private final String colorCode;

    AnsiColorPalette(String colorCode) {
        this.colorCode = colorCode;
    }

    /**
     * Applies both text and background color to a given string.
     *
     * @param text            The text to colorize.
     * @param textColor       The text color.
     * @param backgroundColor The background color.
     * @return The colorized text string.
     */
    public static String applyColor(String text, AnsiColorPalette textColor, AnsiColorPalette backgroundColor) {
        return textColor.apply(backgroundColor.apply(text));
    }

    /**
     * Applies text color to a given string.
     *
     * @param text      The text to colorize.
     * @param textColor The text color.
     * @return The colorized text string.
     */
    public static String applyColor(String text, AnsiColorPalette textColor) {
        return textColor.apply(text);
    }

    /**
     * Gets the ANSI color code associated with this palette entry.
     *
     * @return The ANSI color code as a string.
     */
    public String getColor() {
        return colorCode;
    }

    /**
     * Applies this palette's color to a given string, and resets the color at the end of the string.
     *
     * @param text The text to colorize.
     * @return The colorized text string with this palette's color.
     */
    public String apply(String text) {
        return colorCode + text + AnsiCode.RESET;
    }
}