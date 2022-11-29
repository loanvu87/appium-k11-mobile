package src.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import src.driver.DriverFactory;
import src.driver.Platform;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {

    private static List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    private String udid;
    private String systemPort;

    protected AppiumDriver<MobileElement> getDriver() {
return driverThread.get().getDriver(Platform.ANDROID, udid, systemPort);
    }

    @BeforeTest
    @Parameters({"udid", "systemPort"})
    public void initAppiumSession(String udid, String systemPort) {
        this.udid = udid;
        this.systemPort = systemPort;
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory driverThread = new DriverFactory();
            driverThreadPool.add(driverThread);
            return driverThread;
        });

    }

    @AfterTest(alwaysRun = true)
    public void quitAppiumSession() {
        driverThread.get().quitAppiumSession();
    }

    @AfterMethod
    public void captureScreenshot(ITestResult testResult){
        if(testResult.getStatus() == ITestResult.FAILURE){
            // 1. Get method name
            String testMethodName = testResult.getName();

            // 2. Get taken time | y-m-d-hr-mn-s
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH)+1;
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            int s = calendar.get(Calendar.SECOND);
            String takenTime = y + "-" + m + "-" + d + "-" + hr + "-" + min + "-" + s;

//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(y);
//            stringBuilder.append("-");
//            stringBuilder.append(m);
//            stringBuilder.append("-");
//            stringBuilder.append(d);
//            stringBuilder.append("-");
//            stringBuilder.append(hr);
//            stringBuilder.append("-");
//            stringBuilder.append(min);
//            stringBuilder.append("-");
//            stringBuilder.append(s);
//            String takenTime = stringBuilder.toString();

            // 3. File location to save
            String fileName = testMethodName + "-" + takenTime + ".png";
            String fileLocation = System.getProperty("user.dir") + "/screenshots/" + fileName;

            // 4. Capture and attach into report
            File screenshotBase64 = getDriver().getScreenshotAs(OutputType.FILE);
            Path screenshotPath = Paths.get(fileLocation);
            try{
                FileUtils.copyFile(screenshotBase64,new File(fileLocation));
                InputStream inputStream = Files.newInputStream(screenshotPath);
                Allure.addAttachment(testMethodName, inputStream);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}
