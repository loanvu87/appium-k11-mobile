package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import src.driver.DriverFactory;
import src.driver.Platform;
import src.models.components.login.LoginFormComponentMod02;
import src.models.components.login.LoginFormComponentMod03;
import src.models.pages.LoginScreenMod02;
import src.models.pages.LoginScreenMod03;

public class LoginTestMod03 {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {

            LoginScreenMod03 loginScreen = new LoginScreenMod03(appiumDriver);
            loginScreen.bottomNavComp().clickOnLoginIcon();

            LoginFormComponentMod03 loginFormComp = loginScreen.loginFormComp();
            loginFormComp
                    .inputUsername("mail@test.com")
                    .inputPassword("12345678")
                    .clickOnLoginBtn();

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
