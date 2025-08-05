package eu.senla;

import eu.senla.core.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

  protected WebDriver driver;

  @BeforeMethod
  void init() {
    driver = DriverManager.getDriver();
  }

  @AfterMethod
  void tearDown() {
    DriverManager.quitDriver();
  }
}
