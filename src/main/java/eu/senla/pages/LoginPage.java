package eu.senla.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

  private final By usernameField = By.name("username");
  private final By passwordField = By.name("password");
  private final By submitButton = By.xpath("//button[@type='submit']");
  private final By invalidCredentialsAlert =
      By.cssSelector("p.oxd-text.oxd-text--p.oxd-alert-content-text");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public LoginPage enterUsername(String username) {
    driver.findElement(usernameField).sendKeys(username);
    return this;
  }

  public LoginPage enterPassword(String password) {
    driver.findElement(passwordField).sendKeys(password);
    return this;
  }

  public LoginPage submit() {
    driver.findElement(submitButton).click();
    return this;
  }

  public DashBoardPage loginAs(String username, String password) {
    enterUsername(username).enterPassword(password).submit();
    return new DashBoardPage(driver);
  }

  public String getInvalidCredentialsAlertText() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(invalidCredentialsAlert));
    return driver.findElement(invalidCredentialsAlert).getText();
  }

  public LoginPage loadPage(String url) {
    driver.get(BASE_URL + url);
    wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
    return this;
  }
}
