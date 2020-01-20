package wiley_test_task.web.pages;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wiley_test_task.web.elements.AbstractPage;
import wiley_test_task.web.elements.ProductElement;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchResultPage extends AbstractPage {

    WebDriver driver;

    By closeWindow = By.xpath("//form[@class='country-location-form']//button[@class='close']");
    By productList = By.xpath("//div[@class='products-list']/section");

    By getXPathProduct(Integer id) {
        return By.xpath("//div[@class='products-list']/section[" + id + "]");
    }

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        sleep2o(driver, closeWindow).click();
    }

    public ProductElement getProduct(Integer id) {
        WebElement product = driver.findElement(getXPathProduct(id));
        return new ProductElement(driver, product);
    }

    public List<WebElement> getProducts() {
        return driver.findElements(productList);
    }
}
