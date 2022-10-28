package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.functions.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.driver.DriverFactory;
import src.driver.Platform;

public class LoginFormTest {

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

            //Verification | Login Dialog
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id("android:id/alertTitle")));

            MobileElement loginDialogTitleElem = appiumDriver.findElement(MobileBy.id("android:id/alertTitle"));
            System.out.println("Dialog Title: " + loginDialogTitleElem.getText());
            //Print the dialog content

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
