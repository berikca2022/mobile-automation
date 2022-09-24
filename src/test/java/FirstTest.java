import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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

    @Test
    public void testSwipeArticle(){
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
                "Appium",
                "Cannot find input field",
                15);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Automation for Apps')]"),
                "Cannot find 'Automation for Apps' text",
                5);
        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Automation for Apps')]"),
                "Cannot find 'Java (programming language)' text",
                15);

        swipeUpToFindElement(By.xpath("//*[contains(@text, 'View article in browser')]" ),
                "Cannot find 'View article in browser' text",
                10);


    }

    @Test
    public void saveFirstArticleToMyList(){
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
        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find 'Java (programming language)' text",
                15);

        waitForElementAndClick(By.xpath("//android.widget.TextView[@content-desc='Save']"),
                "Cannot find 'Save' button",
                5);

        waitForElementAndClick(By.xpath("//*[contains(@text, 'ADD TO LIST')]"),
                "Cannot find 'Save' button",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                "Learning Programming",
                "cannot find text input field",
                5);
        waitForElementAndClick(By.xpath("//*[contains(@text, 'OK')]"),
                "Cannot find 'OK' button",
                5);

        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article",
                5);

        waitForElementAndClick(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.ImageButton"),
                "Cannot close article",
                5);

        waitForElementAndClick(By.xpath("//*[contains(@text, 'Saved')]"),
                "Cannot find 'Saved' button",
                5);

        String name_of_folder = "Learning Programming";
        waitForElementAndClick(By.xpath("//*[contains(@text, '" + name_of_folder + "')]"),
                "Cannot find 'Learning Programming' list",
                5);

        swipeElementToLeft(By.xpath("//*[contains(@text, '" + name_of_folder + "')]"),
                "Cannot find 'Learning Programming' topic");

        waitForElementNotPresent(By.xpath("//*[@text, 'Java (programming language)']"),
                "Cannot delete saved article",
                5);

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

    protected void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick(){
        swipeUp(2000);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){
            if(already_swiped > max_swipes){
                waitForElementPresent(by, "cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message){

        WebElement element = waitForElementPresent (
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(5000)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }
}
