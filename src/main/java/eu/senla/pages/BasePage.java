package eu.senla.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

  protected static final String BASE_URL =
      "https://opensource-demo.orangehrmlive.com/web/index.php";
  protected WebDriver driver;
  protected WebDriverWait wait;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(30));
  }

  protected abstract <T> T loadPage(String url);
}
