import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
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
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find 'SKIP' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search field' button",
                5
        );

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Java",
                "Cannot find input field",
                15
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' topic by searching 'Java' ",
                20);
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find 'SKIP' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search field' button",
                5);

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Java",
                "Cannot find input field",
                15
        );

        waitForElementAndClear(
                By.id("search_src_text"),
                "Cannot clear the field",
                5
        );
//        waitForElementAndClick(
//                By.id("search_close_btn"),
//                "Cannot find 'Close 'X' ' button",
//                5
//
//        );

        waitForElementNotPresent(
                By.id("search_close_btn"),
                "'Close 'X'' button is present",
                5);
    }

    @Test
    public void testCompareArticleTitle(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find 'SKIP' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search field' button",
                5);

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Java",
                "Cannot find input field",
                15);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' text",
                5);
        WebElement title_name_element =  waitForElementPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find 'Java (programming language)' text",
                15);

        String article_title = title_name_element.getAttribute("text");

        Assert.assertEquals(
                "title does not match",
                "Java (programming language)",
                article_title);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
}
