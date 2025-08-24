package eu.senla;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import eu.senla.pages.LoginPage;
import io.qameta.allure.*;
import net.bytebuddy.utility.RandomString;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Authentication")
public class LoginTest extends BaseTest {

  @Feature("Login")
  @Story("User is able to login to the app")
  @Test(description = "Successful login with valid credentials")
  @Description("Verify that user is able to login with valid credentials")
  @Severity(SeverityLevel.CRITICAL)
  public void successfulLoginTest() {

    new LoginPage(driver)
            .loadPage("/auth/login")
            .loginAs("Admin", "admin123");

    Allure.step("Validate redirect to dashboard", () -> {
      assertTrue(driver.getCurrentUrl().contains("dashboard/index"));
    });
  }

  @Feature("Login")
  @Story("User is not able to login to the app with wrong credentials")
  @Test(dataProvider = "getCredentials", description = "Failed login with invalid credentials")
  @Description("Verify that user is not able to login with invalid credentials")
  @Severity(SeverityLevel.CRITICAL)
  public void wrongCredentialsTest(String username, String password) {

    String actualAlert =
        new LoginPage(driver)
            .loadPage("/auth/login")
            .enterUsername(username)
            .enterPassword(password)
            .submit()
            .getInvalidCredentialsAlertText();

    Allure.step("Validate alert text", () -> {
      assertEquals(actualAlert, "Invalid credentials",
              "Wrong message");
    });
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
