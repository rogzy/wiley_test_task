package web.elements;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WhoWeServeElement extends AbstractPage {
    By items = By.xpath("./parent::*/div/ul/li/a");
    By hover = By.xpath("//li[@class='dropdown-submenu hover']");

    WebDriver driver;
    WebElement parentElement;

    public WhoWeServeElement(WebDriver driver, WebElement parentElement) {
        this.driver = driver;
        this.parentElement = parentElement;
        sleep2o(driver, hover);
    }

    public List<WebElement> getItems() {
        return parentElement.findElements(items);
    }
}
