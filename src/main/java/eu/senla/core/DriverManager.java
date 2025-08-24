package eu.senla.core;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

import static java.lang.System.getProperty;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browser = getProperty("browser").toLowerCase();
            if (Boolean.parseBoolean(getProperty("selenoidEnable"))) {
                initRemoteDriver(browser);
            } else {
                initLocalDriver(browser);
            }
        }
        return driver.get();
    }

    private static void initRemoteDriver(String browser) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            switch (browser) {
                case "chrome" -> {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    // required for Docker/Selenoid stability. (info by ChatGPT)
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    if (Boolean.parseBoolean(getProperty("headless"))) {
                        chromeOptions.addArguments("--headless=new");
                    }
                    chromeOptions.setCapability("selenoid:options", Map.of(
                            "enableVNC", true,
                            "enableVideo", true
                    ));
                    capabilities.merge(chromeOptions);
                }
                case "firefox" -> {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (Boolean.parseBoolean(getProperty("headless"))) {
                        firefoxOptions.addArguments("--headless");
                    }
                    firefoxOptions.setCapability("selenoid:options", Map.of(
                            "enableVNC", true,
                            "enableVideo", true
                    ));
                    capabilities.merge(firefoxOptions);
                }
                default -> throw new IllegalArgumentException("Unsupported remote browser: " + browser);
            }
            driver.set(new RemoteWebDriver(
                    new URL(getProperty("selenoidUrl")), capabilities));

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenoid URL", e);
        }
    }

    private static void initLocalDriver(String browser) {
        switch (browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
/*                if (Boolean.parseBoolean(getProperty("headless"))) {
                    options.addArguments("--headless=new");
                }*/
                driver.set(new ChromeDriver(options));
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
/*                if (Boolean.parseBoolean(getProperty("headless"))) {
                    options.addArguments("--headless");
                }*/
                driver.set(new FirefoxDriver(options));
            }
            default -> throw new IllegalArgumentException("Передан неподдерживаемый браузер");
        }
    }


    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}