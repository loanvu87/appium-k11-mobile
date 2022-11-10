package src.models.components.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class LoginFormComponentMod02 {

    private final AppiumDriver<MobileElement> appiumDriver ;
    private static final By usernameSel = MobileBy.AccessibilityId("input-email");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginFormComponentMod02(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void inputUsername(String username){
        appiumDriver.findElement(usernameSel).sendKeys(username);
    }

    public void inputPassword(String password){
        appiumDriver.findElement(passwordSel).sendKeys(password);
    }

    public void clickOnLoginBtn(){
        appiumDriver.findElement(loginBtnSel).click();
    }
}
