import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.nio.file.WatchEvent;
import java.util.List;
import java.util.Set;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "testDevice");
        caps.setCapability("platformVersion", "9");
        caps.setCapability("automationName", "Appium");
        caps.setCapability("appPackage", "org.wikipedia");
        caps.setCapability("appActivity", ".main.MainActivity");
        caps.setCapability("app", "Users/kito/Projects/mobile-automation/apks/org.wikipedia.com.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void firstTest(){
        WebElement skip_button = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        skip_button.click();

        WebElement search_field = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
        search_field.click();

        WebElement enter_text = waitElementById("search_src_text", "Cannot find input field", 5);
        enter_text.sendKeys("abc");

    }

    private WebElement waitElementById(String id, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }
}
