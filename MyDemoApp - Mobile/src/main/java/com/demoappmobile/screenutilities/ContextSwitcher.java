package com.demoappmobile.screenutilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;

public class ContextSwitcher {

    private AppiumDriver driver;

    public ContextSwitcher(AppiumDriver driver) {
        this.driver = driver;
    }

    public void switchToWebView() {
        if (driver instanceof SupportsContextSwitching contextSwitchingDriver) {
            for (String context : contextSwitchingDriver.getContextHandles()) {
                if (context.contains("WEBVIEW")) {
                    contextSwitchingDriver.context(context);
                    break;
                }
            }
        } else {
            throw new UnsupportedOperationException("Context switching is only supported for drivers that implement SupportsContextSwitching.");
        }
    }

    public void switchToNative() {
        if (driver instanceof SupportsContextSwitching contextSwitchingDriver) {
            contextSwitchingDriver.context("NATIVE_APP");
        } else {
            throw new UnsupportedOperationException("Context switching is only supported for drivers that implement SupportsContextSwitching.");
        }
    }
}
