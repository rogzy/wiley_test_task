package web.elements;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductElement extends Element {
    By title = By.xpath("//h3//span");
    By tabs = By.xpath(".//ul[@role='tablist']/li");

    public ProductElement(WebDriver driver, WebElement lastElement) {
        super(driver, lastElement);
    }

    public String getTitle() {
        return lastElement.findElement(title).getText();
    }

    public List<WebElement> getTabs() {
        return lastElement.findElements(tabs);
    }
}
