package wiley_test_task.web.elements;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchResultElement {
    By resultList = By.xpath("//aside[contains(@class, 'ui-widget')]/section/div/*");

    WebDriver driver;
    WebElement parentElement;

    public SearchResultElement(WebDriver driver, WebElement parentElement) {
        this.driver = driver;
        this.parentElement = parentElement;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (!parentElement.findElement(resultList).isDisplayed()) {
            throw new RuntimeException("");
        }
    }

    public WebElement getResult() {
        return parentElement.findElement(resultList);
    }
}
