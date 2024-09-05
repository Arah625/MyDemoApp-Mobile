package com.demoappmobile.screenutilities.driver;

import java.time.Duration;

public class Configuration {
    public static final String APP_PATH = "C:\\Users\\Chrystian\\Downloads\\Android-MyDemoAppRN.1.3.0.build-244.apk";
    public static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    public static final int DEFAULT_TIMEOUT = 10;
    public static final Duration WAIT_TIME = Duration.ofSeconds(10);
    public static final Duration POLLING_INTERVAL = Duration.ofMillis(500);
}
