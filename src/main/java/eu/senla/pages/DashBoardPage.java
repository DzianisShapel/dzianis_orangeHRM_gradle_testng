package eu.senla.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class DashBoardPage extends BasePage {

    private Sidepanel sidepanel;
    private final By header = By.cssSelector(".oxd-topbar-header-breadcrumb-module");

    public DashBoardPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(header));
        this.sidepanel = new Sidepanel(driver);
    }

    @Override
    protected <T> T loadPage(String url) {
        return null;
    }
}
