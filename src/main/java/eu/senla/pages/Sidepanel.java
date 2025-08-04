package eu.senla.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Sidepanel {

  private WebDriver driver;

  private final By pim = By.cssSelector("ul > li:nth-child(2) > a.oxd-main-menu-item");

  public Sidepanel(WebDriver driver) {
    this.driver = driver;
  }

  public PimPage clickPimOption() {
    driver.findElement(pim).click();
    return new PimPage(driver);
  }
}
