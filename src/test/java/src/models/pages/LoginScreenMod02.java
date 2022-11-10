package src.models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import src.models.components.global.BottomNavComponent;
import src.models.components.login.LoginFormComponent;
import src.models.components.login.LoginFormComponentMod02;

public class LoginScreenMod02 {

    private final AppiumDriver<MobileElement> appiumDriver;

    public LoginScreenMod02(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginFormComponentMod02 loginFormComp(){
        return new LoginFormComponentMod02(appiumDriver);
    }

    public BottomNavComponent bottomNavComp(){
        return new BottomNavComponent(appiumDriver);
    }
}
