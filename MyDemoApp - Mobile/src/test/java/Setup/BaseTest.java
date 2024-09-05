package Setup;

import com.demoappmobile.screen.nativecontext.Catalog;
import com.demoappmobile.screenutilities.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public abstract class BaseTest {

    protected String WEB_VIEW_URL = "https://www.saucedemo.com";
    protected WebDriver driver;
    protected Catalog catalog;
    protected DriverManager driverManager;

    @BeforeMethod(alwaysRun = true)
    public void driverSetup(ITestContext context, ITestResult iTestResult) throws IOException, InterruptedException {
        String platform = context.getCurrentXmlTest().getParameter("platform");
        String device = context.getCurrentXmlTest().getParameter("device");
        driverManager = DriverManager.getInstance();
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
        commonSetup();
    }

    /**
     * Common setup actions to be performed at the start of each test method.
     * Navigates to the initial URL and performs any required initial actions on the page.
     */
    private void commonSetup() {
        catalog = new Catalog(driver);
    }

    @AfterTest
    public void close(){
//        driver.quit();
    }
}