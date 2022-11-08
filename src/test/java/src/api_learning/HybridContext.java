package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.contexts.Contexts;
import src.contexts.WaitMoreThanOneContext;
import src.driver.DriverFactory;
import src.driver.Platform;

import java.util.ArrayList;
import java.util.List;

public class HybridContext {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {

            // Navigate to webview
            MobileElement navWebviewBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Webview"));
            navWebviewBtnElem.click();

            WebDriverWait wait = new WebDriverWait(appiumDriver, 5L);
            wait.until(new WaitMoreThanOneContext(appiumDriver));

            // Print all available contexts
            for (String contextHandle : appiumDriver.getContextHandles()) {
                System.out.println("Context: " + contextHandle);
            }

            // Switch Webview context
            appiumDriver.context(Contexts.WEB_VIEW);
            WebElement navToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToggleBtnElem.click();

            List<MobileElement> listItemsElem = appiumDriver.findElementsByCssSelector(".menu__list-item");

            if (listItemsElem.isEmpty()) {
                throw new RuntimeException("[ERR] There is no list item!");
            }
            List<MenuItemData> menuItemData = new ArrayList<>();

            for (MobileElement itemElem : listItemsElem) {
                String itemText = itemElem.findElementByCssSelector(".menu__link").getText();
                String itemHref = itemElem.findElementByCssSelector(".menu__link").getAttribute("href");
                if (itemText.isEmpty()) {
                    menuItemData.add(new MenuItemData("Github",itemHref));
                } else {
                    menuItemData.add(new MenuItemData(itemText,itemHref));
                }
            }

            System.out.println(menuItemData);

            // Switch back to Native context
            appiumDriver.context(Contexts.NATIVE_APP);
            appiumDriver.findElement(MobileBy.AccessibilityId("Home")).click();

            // Debug purpose ONLY
            Thread.sleep(4000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

    public static class MenuItemData{
        private final String name;
        private final String href;

        public MenuItemData(String name, String href) {
            this.name = name;
            this.href = href;
        }

        public String getName() {
            return name;
        }

        public String getHref() {
            return href;
        }

        @Override
        public String toString() {
            return "MenuItemData{" +
                    "name='" + name + '\'' +
                    ", href='" + href + '\'' +
                    '}';
        }
    }
}
