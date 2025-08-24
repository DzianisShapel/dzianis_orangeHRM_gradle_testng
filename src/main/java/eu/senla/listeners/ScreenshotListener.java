package eu.senla.listeners;

import eu.senla.core.DriverManager;
import eu.senla.utils.ScreenshotUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            ScreenshotUtil.takeScreenshot(driver);
        } else {
            log.error("WebDriver is null. Screenshot not taken.");
        }
    }
}
