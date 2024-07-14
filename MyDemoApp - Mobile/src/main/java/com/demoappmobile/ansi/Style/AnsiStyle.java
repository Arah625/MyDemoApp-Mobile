package com.demoappmobile.ansi.Style;


import com.demoappmobile.ansi.AnsiCode;

/**
 * Provides ANSI escape codes for text styling in console applications.
 * This utility class includes static fields for commonly used text styles such as bold, italic, and underline,
 * facilitating the application of these styles to strings for console output.
 */
public class AnsiStyle implements AnsiCode {

    /**
     * ANSI escape code for applying bold style to text.
     */
    public static final String BOLD = "\u001B[1m";

    /**
     * ANSI escape code for applying italic style to text.
     * Note: Italic may not be supported in all console environments.
     */
    public static final String ITALIC = "\u001B[3m";

    /**
     * ANSI escape code for applying underline style to text.
     */
    public static final String UNDERLINE = "\u001B[4m";

    private AnsiStyle() {
        // Private constructor to prevent instantiation
    }
}