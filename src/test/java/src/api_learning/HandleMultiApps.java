package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.driver.AppPackages;
import src.driver.DriverFactory;
import src.driver.Platform;

import java.time.Duration;

public class HandleMultiApps {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            //Navigate to login form
            MobileElement NavLoginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            NavLoginBtnElem.click();

            //Find Login form elements
            MobileElement emailInputFieldElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement pwdInputFieldElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

            //Interact with login form | fill username and password
            emailInputFieldElem.sendKeys("mail@test.com");
            pwdInputFieldElem.sendKeys("12345678");
            loginBtnElem.click();

            // Run app in background
//            appiumDriver.runAppInBackground(Duration.ofSeconds(3));

            // Open settings app
            appiumDriver.activateApp(AppPackages.SETTINGS);
            appiumDriver.findElement(MobileBy.xpath("//*[@text='Connections']")).click();

            boolean isWifiON = appiumDriver.findElement(MobileBy.AccessibilityId("Wi-Fi")).getAttribute("text").equals("On");
            int timeToToggle = isWifiON ? 2 : 1;
            for (int clickTime = 0; clickTime < timeToToggle; clickTime++) {
                WebDriverWait wait = new WebDriverWait(appiumDriver, 5L);
                appiumDriver.findElement(MobileBy.AccessibilityId("Wi-Fi")).click();
                if (timeToToggle == 2) {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Connect to Wi-Fi networks.\")")));
                } else {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"On\")")));
                }
            }



            // Comeback to the main app
            appiumDriver.activateApp(AppPackages.WEBDRIVER_IO);
            appiumDriver.findElement(MobileBy.xpath("//*[@text='OK']")).click();

            // Debug purpose ONLY
            Thread.sleep(4000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
