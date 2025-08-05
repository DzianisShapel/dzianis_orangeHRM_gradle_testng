package eu.senla;


import com.github.javafaker.Faker;
import eu.senla.entities.Employee;
import eu.senla.pages.LoginPage;
import eu.senla.pages.PersonalDetailsPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AddEmployeeTest extends BaseTest {

  private Employee employee;


  @BeforeTest
  void generateTestData() {
    Faker faker = new Faker();
    employee = Employee.builder()
            .firstName(faker.name().firstName())
            .lastName(faker.name().lastName())
            .middleName(faker.name().username())
            .build();
  }

  @Test
  public void addUserTest() {

    PersonalDetailsPage personalDetailsPage = new LoginPage(driver)
        .loadPage("/auth/login")
        .loginAs("Admin", "admin123")
        .getSidepanel()
        .clickPimOption()
        .clickAddEmployeeLink()
        .addEmployee(employee);
  }
}
