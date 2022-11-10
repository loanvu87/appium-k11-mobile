package src.models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class BottomNavComponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By loginIconSel = MobileBy.AccessibilityId("Login");

    public BottomNavComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public MobileElement loginIconElem(){
        return appiumDriver.findElement(loginIconSel);
    }

    // Method 2 | Introduce main interaction method
    public void clickOnLoginIcon(){
        appiumDriver.findElement(loginIconSel).click();
    }
}
