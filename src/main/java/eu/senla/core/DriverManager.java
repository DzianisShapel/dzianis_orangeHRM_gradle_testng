package eu.senla.core;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.lang.System.getProperty;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            switch (getProperty("browser").toLowerCase()) {
                case "chrome" -> driver.set(new ChromeDriver());
                case "firefox" -> driver.set(new FirefoxDriver());
                default -> throw new IllegalArgumentException("Передан неподдерживаемый браузер");
            }

        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}