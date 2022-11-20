package src.test_flows;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import src.models.pages.LoginScreenMod03;

public class BaseFlow {
    protected final AppiumDriver<MobileElement> appiumDriver;

    public BaseFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void goToLoginScreen(){
        new LoginScreenMod03(appiumDriver).bottomNavComp().clickOnLoginIcon();
    }
}
