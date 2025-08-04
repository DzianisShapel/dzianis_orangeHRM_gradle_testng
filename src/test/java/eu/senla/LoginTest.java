package eu.senla;


import static org.testng.Assert.assertTrue;

import eu.senla.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

  @Test
  public void successfulLoginTest() {

    new LoginPage(driver).loadPage("/auth/login").loginAs("Admin", "admin123");

    assertTrue(driver.getCurrentUrl().contains("dashboard/index"));
  }

  @Test
  public void wrongCredentialsTest(String username, String password, String description) {

    String actualAlert =
        new LoginPage(driver)
            .loadPage("/auth/login")
            .enterUsername(username)
            .enterPassword(password)
            .submit()
            .getInvalidCredentialsAlertText();
  }
}
