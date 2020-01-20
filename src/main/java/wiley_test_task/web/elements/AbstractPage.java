package wiley_test_task.web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

    protected WebElement sleep2o(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by);
    }

    protected String getXPathSubMenu(String tab) {
        return "//a[contains(text(),'" + tab.toUpperCase() + "')]";
    }

    protected String getXPathSection(String section) {
        return "//ul//*[contains(text(),'" + section + "')]";
    }
}
