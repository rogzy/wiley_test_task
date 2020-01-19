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
public class WhoWeServeElement extends Element {
    By items = By.xpath("./parent::*/div/ul/li/a");

    public WhoWeServeElement(WebDriver driver, WebElement lastElement) {
        super(driver, lastElement);
    }

    public List<WebElement> getItems() {
        return lastElement.findElements(items);
    }
}
