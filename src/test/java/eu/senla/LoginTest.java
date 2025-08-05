package eu.senla;


import static org.testng.Assert.assertTrue;

import eu.senla.pages.LoginPage;
import net.bytebuddy.utility.RandomString;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

  @Test(description = "Successful login with valid credentials")
  public void successfulLoginTest() {

    new LoginPage(driver)
            .loadPage("/auth/login")
            .loginAs("Admin", "admin123");

    assertTrue(driver.getCurrentUrl().contains("dashboard/index"));
  }

  @Test(dataProvider = "getCredentials", description = "Failed login with invalid credentials")
  public void wrongCredentialsTest(String username, String password) {

    String actualAlert =
        new LoginPage(driver)
            .loadPage("/auth/login")
            .enterUsername(username)
            .enterPassword(password)
            .submit()
            .getInvalidCredentialsAlertText();
  }

  @DataProvider(name = "getCredentials")
  public Object[][] getCredentials() {
    return new Object[][] {
            { "Admin", RandomString.make()},
            { RandomString.make(), "admin123"},
            { RandomString.make(), RandomString.make()}
    };
  }
}
