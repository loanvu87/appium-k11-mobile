package src.tests.webview;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import src.contexts.Contexts;
import src.contexts.WaitMoreThanOneContext;
import src.driver.DriverFactory;
import src.driver.Platform;
import src.tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class WebviewTest extends BaseTest {

    @Test
    public void testWebview() {

        // Navigate to webview
        MobileElement navWebviewBtnElem = getDriver().findElement(MobileBy.AccessibilityId("Webview"));
        navWebviewBtnElem.click();

        WebDriverWait wait = new WebDriverWait(getDriver(), 5L);
        wait.until(new WaitMoreThanOneContext(getDriver()));

        // Print all available contexts
        for (String contextHandle : getDriver().getContextHandles()) {
            System.out.println("Context: " + contextHandle);
        }

        // Switch Webview context
        getDriver().context(Contexts.WEB_VIEW);
        WebElement navToggleBtnElem = getDriver().findElementByCssSelector(".navbar__toggle");
        navToggleBtnElem.click();

        List<MobileElement> listItemsElem = getDriver().findElementsByCssSelector(".menu__list-item");

        if (listItemsElem.isEmpty()) {
            throw new RuntimeException("[ERR] There is no list item!");
        }
        List<MenuItemData> menuItemData = new ArrayList<>();

        for (MobileElement itemElem : listItemsElem) {
            String itemText = itemElem.findElementByCssSelector(".menu__link").getText();
            String itemHref = itemElem.findElementByCssSelector(".menu__link").getAttribute("href");
            if (itemText.isEmpty()) {
                menuItemData.add(new MenuItemData("Github", itemHref));
            } else {
                menuItemData.add(new MenuItemData(itemText, itemHref));
            }
        }

        System.out.println(menuItemData);

        // Switch back to Native context
        getDriver().context(Contexts.NATIVE_APP);
        getDriver().findElement(MobileBy.AccessibilityId("Home")).click();
    }

    public static class MenuItemData {
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
