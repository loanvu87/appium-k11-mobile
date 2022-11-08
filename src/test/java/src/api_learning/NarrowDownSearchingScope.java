package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import src.driver.DriverFactory;
import src.driver.Platform;

import java.time.Duration;
import java.util.List;

public class NarrowDownSearchingScope {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Get mobile screen
            Dimension dimension = appiumDriver.manage().window().getSize();
            int screenHeight = dimension.getHeight();
            int screenWidth = dimension.getWidth();

            // Calculate touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;
            int yStartPoint = 0;
            int yEndPoint = 40 * screenHeight / 100;

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
            List<MobileElement> notificationsElem =
                    appiumDriver.findElements(MobileBy.xpath("//android.widget.LinearLayout[@resource-id=\"com.android.systemui:id/quick_qs_panel\"]//android.widget.LinearLayout//android.widget.LinearLayout"));

            if(notificationsElem.isEmpty()){
                throw new RuntimeException("[ERR] There is no notifications!");
            }else {
                for (MobileElement notificationElem : notificationsElem) {
                    MobileElement notificationLabelElem = notificationElem.findElement(MobileBy.xpath("//android.view.ViewGroup"));
                    System.out.println(notificationLabelElem.getAttribute("content-desc"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();

    }
}
