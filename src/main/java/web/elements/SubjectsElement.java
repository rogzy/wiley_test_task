package web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SubjectsElement extends AbstractPage {
    By items = By.xpath("./following-sibling::*//a");
    By hover = By.xpath("//li[@class='dropdown-item dropdown-submenu hover']");

    WebDriver driver;
    WebElement parentElement;

    public SubjectsElement(WebDriver driver, WebElement parentElement) {
        this.driver = driver;
        this.parentElement = parentElement;
        sleep2o(driver, hover);
    }

    public List<WebElement> getItems() {
        return parentElement.findElements(items);
    }
}
