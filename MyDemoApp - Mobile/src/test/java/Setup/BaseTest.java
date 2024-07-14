package Setup;

import com.demoappmobile.screen.Home;
import com.demoappmobile.screenutilities.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;

public abstract class BaseTest {

    protected String WEB_VIEW_URL = "https://www.saucedemo.com";
    protected WebDriver driver;
    protected Home home;
    protected DriverManager driverManager;

    @BeforeMethod(alwaysRun = true)
    public void driverSetup(ITestContext context, ITestResult iTestResult) {
        String platform = context.getCurrentXmlTest().getParameter("platform");
        String device = context.getCurrentXmlTest().getParameter("device");
        driverManager = DriverManager.getInstance();
        try {
            switch (platform.toUpperCase()) {
                case "ANDROID":
                    driverManager.initAndroidDriver(device);
                    driver = driverManager.getDriver();
                    break;
                case "IOS":
                    driverManager.initIOSDriver(device);
                    driver = driverManager.getDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown platform: " + platform);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Driver initialization failed: " + e.getMessage());
        }
        commonSetup();
    }

    /**
     * Common setup actions to be performed at the start of each test method.
     * Navigates to the initial URL and performs any required initial actions on the page.
     */
    private void commonSetup() {
        home = new Home(driver);
    }
}
//    @Parameters({"platform"})
//    @BeforeMethod(alwaysRun = true)
//    public void driverSetup(String platform, ITestContext context, ITestResult iTestResult) {
//        switch (platform.toUpperCase()) {
//            case "ANDROID":
//                AndroidDriverSetup.getInstance().initializeDriver(context.getCurrentXmlTest().getParameter("device"));
//                driver = AndroidDriverSetup.getInstance().getDriver();
//                break;
//            case "IOS":
//                IOSDriverSetup.getInstance().initializeDriver(context.getCurrentXmlTest().getParameter("device"));
//                driver = IOSDriverSetup.getInstance().getDriver();
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown platform: " + platform);
//        }
//        commonSetup();
//    }