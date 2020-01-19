package web.pages;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import web.elements.ProductElement;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchResultPage {

    WebDriver driver;

    By closeWindow = By.xpath("//form[@class='country-location-form']//button[@class='close']");
    By productList = By.xpath("//div[@class='products-list']/section");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        sleep(2000);
        driver.findElement(closeWindow).click();
    }

    public ProductElement getProduct(Integer id) {
        WebElement product = driver.findElement(getXPath(id));
        return new ProductElement(driver, product);
    }

    public List<WebElement> getProducts() {
        return driver.findElements(productList);
    }


    private By getXPath(Integer id) {
        return By.xpath("//div[@class='products-list']/section[" + id + "]");
    }

    //TODO kill me!
    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {

        }
    }
}
