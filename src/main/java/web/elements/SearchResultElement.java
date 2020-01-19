package web.elements;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchResultElement extends Element {
    By resultList = By.xpath("//aside[contains(@class, 'ui-widget')]/section/div/*");

    public SearchResultElement(WebDriver driver, WebElement lastElement) {
        super(driver, lastElement);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (!lastElement.findElement(resultList).isDisplayed()) {
            throw new RuntimeException("");
        }
    }

    public WebElement getResult() {
        return lastElement.findElement(resultList);
    }
}
