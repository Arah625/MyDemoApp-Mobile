package com.demoappmobile.ansi.colors;


import com.demoappmobile.ansi.AnsiCode;
import com.demoappmobile.ansi.Style.AnsiStyle;

/**
 * Provides a set of utility methods for applying ANSI color styles to text.
 * This class includes predefined ANSI codes for various colors and styles, such as bold and underline,
 * allowing for easy text formatting in console applications.
 */
public abstract class Color {

    public static final String RESET = AnsiCode.RESET;
    public static final String BLUE = AnsiColorGenerator.getStandardColor(4);
    public static final String RED = AnsiColorGenerator.getStandardColor(1);
    public static final String RED_BOLD = AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(1);
    public static final String BLUE_BOLD = AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(4);
    public static final String BLUE_BOLD_UNDERLINED = AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(4);
    public static final String GREEN = AnsiColorGenerator.getStandardColor(2);
    public static final String GREEN_BOLD = AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(2);
    public static final String YELLOW = AnsiColorGenerator.getStandardColor(3);
    public static final String YELLOW_BOLD = AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(3);
    public static final String MAGENTA = AnsiColorGenerator.getStandardColor(5);
    public static final String MAGENTA_BOLD = AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(5);

    private Color() {
        // Private constructor to prevent instantiation
    }

    /**
     * Applies blue color to the specified text.
     *
     * @param text The text to colorize.
     * @return The blue-colored text string.
     */
    public static String blue(String text) {
        return BLUE + text + RESET;
    }

    /**
     * Applies bold blue color to the specified text.
     *
     * @param text The text to colorize.
     * @return The bold blue-colored text string.
     */
    public static String blueBold(String text) {
        return BLUE_BOLD + text + RESET;
    }

    /**
     * Wraps the given text in ANSI codes to color it blue with bold and underline styles.
     *
     * @param text The text to be styled.
     * @return A string containing the original text wrapped in ANSI codes for blue color with bold and underline styles.
     */
    public static String blueBoldUnderlined(String text) {
        return BLUE_BOLD_UNDERLINED + text + RESET;
    }

    /**
     * Wraps the given text in ANSI codes to color it red.
     *
     * @param text The text to be colored.
     * @return A string containing the original text wrapped in ANSI codes for red color.
     */
    public static String red(String text) {
        return RED + text + RESET;
    }

    /**
     * Wraps the given text in ANSI codes to color it green.
     *
     * @param text The text to be colored.
     * @return A string containing the original text wrapped in ANSI codes for green color.
     */
    public static String green(String text) {
        return GREEN + text + RESET;
    }

    /**
     * Wraps the given text in ANSI codes to color it yellow.
     *
     * @param text The text to be colored.
     * @return A string containing the original text wrapped in ANSI codes for yellow color.
     */
    public static String yellow(String text) {
        return YELLOW + text + RESET;
    }

    /**
     * Wraps the given text in ANSI codes to color it magenta.
     *
     * @param text The text to be colored.
     * @return A string containing the original text wrapped in ANSI codes for magenta color.
     */
    public static String magenta(String text) {
        return MAGENTA + text + RESET;
    }

    /**
     * Wraps the given text in ANSI codes to color it red and apply bold style.
     *
     * @param text The text to be styled.
     * @return A string containing the original text wrapped in ANSI codes for red color and bold style.
     */
    public static String redBold(String text) {
        return RED_BOLD + text + RESET;
    }

    /**
     * Wraps the given text in ANSI codes to color it green and apply bold style.
     *
     * @param text The text to be styled.
     * @return A string containing the original text wrapped in ANSI codes for green color and bold style.
     */
    public static String greenBold(String text) {
        return GREEN_BOLD + text + RESET;
    }

    /**
     * Wraps the given text in ANSI codes to color it yellow and apply bold style.
     *
     * @param text The text to be styled.
     * @return A string containing the original text wrapped in ANSI codes for yellow color and bold style.
     */
    public static String yellowBold(String text) {
        return YELLOW_BOLD + text + RESET;
    }

    /**
     * Wraps the given text in ANSI codes to color it magenta and apply bold style.
     *
     * @param text The text to be styled.
     * @return A string containing the original text wrapped in ANSI codes for magenta color and bold style.
     */
    public static String magentaBold(String text) {
        return MAGENTA_BOLD + text + RESET;
    }
}