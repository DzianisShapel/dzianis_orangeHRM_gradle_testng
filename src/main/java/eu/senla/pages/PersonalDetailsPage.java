package eu.senla.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PersonalDetailsPage extends BasePage {


  private final By personalDetail = By.xpath("//h6[text()='Personal Details']");

  public PersonalDetailsPage(WebDriver driver) {
    super(driver);
    wait.until(ExpectedConditions.visibilityOfElementLocated(personalDetail));
  }

  @Override
  protected <T> T loadPage(String url) {
    return null;
  }
}
