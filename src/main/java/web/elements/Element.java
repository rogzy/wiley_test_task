package web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Element {

    WebDriver driver;
    WebElement lastElement;

    public Element(WebDriver driver, WebElement lastElement) {
        this(driver);
        this.lastElement = lastElement;
    }

    public Element(WebDriver driver) {
        this.driver = driver;
    }

    public <T> T pointToTab(String name, Class<T> type) {
        WebElement currentTab = lastElement.findElement(By.xpath(getXPathTab(name)));
        new Actions(driver).moveToElement(currentTab).perform();
        sleep(2000);
        return type.cast(new Element(driver, currentTab));
    }

    //TODO kill me!
    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {

        }
    }

    private String getXPathTab(String tab) {
        return ".//a[contains(text(),'" + tab.toUpperCase() + "')]";
    }
}
