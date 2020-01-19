package web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Objects;

public class SubjectsElement extends Element {
    By items = By.xpath("./parent::*/div/ul/li/a");

    By education = By.xpath("./following-sibling::*/ul//*[contains(text(),'Education')]");

    By underItems = By.xpath("./following-sibling::*//a");

    public SubjectsElement(WebDriver driver, WebElement lastElement) {
        super(driver, lastElement);
    }

    public List<WebElement> getItems() {
        return lastElement.findElements(items);
    }

    public WebElement getItem(String text) {
        List<WebElement> elements = lastElement.findElements(items);
        return elements.stream().map(m -> {
            if (m.getText().equals(text)) {
                return m;
            } else {
                return null;
            }
        }).filter(Objects::nonNull).findFirst().get();
    }


}
