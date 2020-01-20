package wiley_test_task.web.elements;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductElement {
    By title = By.xpath("//h3//span");
    By tabs = By.xpath(".//ul[@role='tablist']/li");

    WebDriver driver;
    WebElement parentElement;

    public ProductElement(WebDriver driver, WebElement parentElement) {
        this.driver = driver;
        this.parentElement = parentElement;
    }

    public String getTitle() {
        return parentElement.findElement(title).getText();
    }

    public List<WebElement> getTabs() {
        return parentElement.findElements(tabs);
    }
}
