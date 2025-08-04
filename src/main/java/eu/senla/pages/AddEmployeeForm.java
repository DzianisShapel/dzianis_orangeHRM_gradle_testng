package eu.senla.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AddEmployeeForm {

  private WebDriver driver;
  private WebDriverWait wait;

  private final By firstNameField = By.name("firstName");
  private final By lastNameField = By.name("lastName");
  private final By middleNameField = By.name("middleName");
  private final By submitButton = By.xpath("//button[@type='submit']");
  private final By spinner = By.cssSelector(".oxd-loading-spinner");
  private final By successPopUp = By.cssSelector(".oxd-toast-content--success");

  public AddEmployeeForm(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
  }

  public AddEmployeeForm setFirstName(String firstName) {
    wait
            .until(ExpectedConditions.visibilityOfElementLocated(firstNameField))
            .sendKeys(firstName);
    return this;
  }

  public AddEmployeeForm setLastName(String lastName) {
    driver.findElement(lastNameField).sendKeys(lastName);
    return this;
  }

  public AddEmployeeForm setMiddleName(String middleName) {
    driver.findElement(middleNameField).sendKeys(middleName);
    return this;
  }

  public PersonalDetailsPage submit() {
    driver.findElement(submitButton).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(successPopUp));
    return new PersonalDetailsPage(driver);
  }
}
