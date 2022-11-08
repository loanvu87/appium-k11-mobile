package src.contexts;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.functions.ExpectedCondition;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.WebDriver;

public class WaitMoreThanOneContext implements ExpectedCondition<Boolean> {

    private final AppiumDriver<MobileElement> appiumDriver;

    public WaitMoreThanOneContext(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    @Override
    public Boolean apply(WebDriver webDriver) {
        return appiumDriver.getContextHandles().size() > 1;
    }
}
