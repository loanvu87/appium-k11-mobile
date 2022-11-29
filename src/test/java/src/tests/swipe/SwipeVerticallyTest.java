package src.tests.swipe;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import src.driver.DriverFactory;
import src.driver.Platform;
import src.tests.BaseTest;

import java.time.Duration;

public class SwipeVerticallyTest extends BaseTest {

    @Test
    public void testSwipeVertically() {

        AppiumDriver<MobileElement> appiumDriver = getDriver();

            // Navigate to login screen
            MobileElement navFormsBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormsBtnElem.click();

            // Wait until we are on the new screen after navigating
            WebDriverWait wait = new WebDriverWait(appiumDriver, 5L);
            wait.until(ExpectedConditions.visibilityOfElementLocated
                    (MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Form components\")")));


            // Get mobile screen
            Dimension dimension = appiumDriver.manage().window().getSize();
            int screenHeight = dimension.getHeight();
            int screenWidth = dimension.getWidth();

            // Calculate touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;
            int yStartPoint = 50 * screenHeight / 100;
            int yEndPoint = 10 * screenHeight / 100;

            // Convert points to coordinate
            PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

            // TouchAction
            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            // Find active button and click
            appiumDriver.findElement(MobileBy.AccessibilityId("button-Active")).click();

        Assert.fail("ERR...");
    }
}
