import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

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
    public void firstTest() throws InterruptedException {
        WebElement skip_button = driver.findElementByXPath("//*[contains(@text, 'SKIP')]");
        skip_button.click();

        WebElement search_field = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
        search_field.click();

        WebElement element_to_enter_search_line = waitElementPresentById(
                "search_src_text",
                "Cannot find input field");
        element_to_enter_search_line.sendKeys("Java ");
        waitElementPresentByXpath(
                "//*[contains(@text, 'Object-oriented programming language')]",
                "Cannot find 'Object-oriented programming language' topic by searching 'Java' ",
                20);
    }

    private WebElement waitElementPresentById(String id, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitElementPresentById(String id, String error_message) {
        return waitElementPresentById(id, error_message, 5);
    }

    private WebElement waitElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitElementPresentByXpath(String xpath, String error_message) {
        return waitElementPresentById(xpath, error_message, 5);
    }
}
