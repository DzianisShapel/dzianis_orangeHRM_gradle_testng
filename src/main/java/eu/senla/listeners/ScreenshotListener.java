package eu.senla.listeners;

import eu.senla.core.DriverManager;
import eu.senla.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            ScreenshotUtil.takeScreenshot(driver);
        } else {
            System.out.println("WebDriver is null. Screenshot not taken.");
        }
    }
}
