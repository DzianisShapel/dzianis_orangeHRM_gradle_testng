package eu.senla.pages;

import eu.senla.entities.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PimPage extends BasePage {

  private AddEmployeeForm addEmployeeForm;

  private final By header = By.cssSelector(".oxd-topbar-header-breadcrumb-module");
  private final By addEmployeeLink =
      By.cssSelector("ul > li:nth-child(3) > a.oxd-topbar-body-nav-tab-item");

  public PimPage(WebDriver driver) {
    super(driver);
    wait.until(ExpectedConditions.textToBe(header, "PIM"));
    this.addEmployeeForm = new AddEmployeeForm(driver);
  }

  public PersonalDetailsPage addEmployee(Employee employee) {
    return addEmployeeForm
        .setFirstName(employee.getFirstName())
        .setLastName(employee.getLastName())
        .setMiddleName(employee.getMiddleName())
        .submit();
  }

  public PimPage clickAddEmployeeLink() {
    wait.until(ExpectedConditions.elementToBeClickable(addEmployeeLink)).click();
    return this;
  }

  @Override
  protected <T> T loadPage(String url) {
    return null;
  }
}
