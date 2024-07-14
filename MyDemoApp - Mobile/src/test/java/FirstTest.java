import Setup.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {


//    @BeforeTest
//    public void setup() throws MalformedURLException {
//        String appiumServerUrl = "http://127.0.0.1:4723";
//
//        DesiredCapabilities dc = new DesiredCapabilities();
//        dc.setCapability("platformName","Android");
//        dc.setCapability("appium:automationName","uiautomator2");
//        dc.setCapability("appium:app","C:\\Users\\Chrystian\\Downloads\\Android-MyDemoAppRN.1.3.0.build-244.apk");
//
//        driver = new AndroidDriver(new URL(appiumServerUrl),dc);
//    }

    @Test
    public void test(){
        home.hamburgerMenuButtonCLick();
        home.goToLoginScreen();
    }

    @AfterTest
    public void close(){
//        driver.quit();
    }
}