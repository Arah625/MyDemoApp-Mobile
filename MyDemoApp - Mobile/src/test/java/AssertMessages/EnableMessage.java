package AssertMessages;


import com.demoappmobile.ansi.colors.Color;

public abstract class EnableMessage {

    public static String buttonIsNotEnabled(String button) {
        return Color.red("Button " + Color.redBold(button) + Color.red(" is not enabled!"));
    }

    public static String buttonIsEnabled(String button) {
        return Color.red("Button " + Color.redBold(button) + Color.red(" is enabled!"));
    }
}
