package com.demoappmobile.screenutilities;

import com.demoappmobile.Logger.InfoMessage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;

public class ContextSwitcher {

    private AppiumDriver driver;

    public ContextSwitcher(AppiumDriver driver) {
        this.driver = driver;
    }

    public void switchToWebView() {
        switchContext("WEBVIEW");
    }

    public void switchToNative() {
        switchContext("NATIVE_APP");
    }

    private void switchContext(String contextType) {
        if (driver instanceof SupportsContextSwitching contextSwitchingDriver) {
            if (contextType.equals("WEBVIEW")) {
                for (String context : contextSwitchingDriver.getContextHandles()) {
                    if (context.contains(contextType)) {
                        contextSwitchingDriver.context(context);
                        InfoMessage.switchingContext("WEBVIEW");
                        return;
                    }
                }
                throw new IllegalStateException("No WebView context found");
            } else if (contextType.equals("NATIVE_APP")) {
                contextSwitchingDriver.context(contextType);
                InfoMessage.switchingContext("NATIVE");
            }
        } else {
            //TODO: Add Custom exception - Unknown/UnsupportedContextException
            throw new UnsupportedOperationException("Context switching is only supported for drivers that implement SupportsContextSwitching.");
        }
    }
}
