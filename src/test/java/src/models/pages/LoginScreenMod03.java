package src.models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import src.models.components.global.BottomNavComponent;
import src.models.components.login.LoginFormComponentMod03;

public class LoginScreenMod03 {

    private final AppiumDriver<MobileElement> appiumDriver;

    public LoginScreenMod03(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginFormComponentMod03 loginFormComp(){
        return new LoginFormComponentMod03(appiumDriver);
    }

    public BottomNavComponent bottomNavComp(){
        return new BottomNavComponent(appiumDriver);
    }
}
