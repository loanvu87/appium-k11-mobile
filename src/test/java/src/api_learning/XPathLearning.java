package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import src.driver.DriverFactory;
import src.driver.Platform;

import java.util.List;

public class XPathLearning {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to login screen
            MobileElement NavLoginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            NavLoginBtnElem.click();

            // Find all matching elems
            List<MobileElement> credFieldsElem = appiumDriver.findElements(MobileBy.xpath("//android.widget.EditText"));
            final int USERNAME_INDEX = 0;
            final int PASSWORD_INDEX = 1;
            credFieldsElem.get(USERNAME_INDEX).sendKeys("test@mail.com");
            credFieldsElem.get(PASSWORD_INDEX).sendKeys("12345678");

            // Find log in info text by UiSelector
            MobileElement loginInstructionElem =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"When the device has\")"));
            System.out.println(loginInstructionElem.getText());

            // Debug purpose ONLY
            Thread.sleep(4000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
