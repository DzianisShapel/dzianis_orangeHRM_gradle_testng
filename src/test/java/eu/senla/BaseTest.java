package eu.senla;

import eu.senla.core.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

  protected WebDriver driver;

  @BeforeTest
  void init() {
    driver = DriverManager.getDriver();
  }

  @AfterTest
  void tearDown() {
    DriverManager.quitDriver();
  }
}
