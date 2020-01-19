package web.pages;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import web.elements.Element;
import web.elements.SearchResultElement;
import web.elements.SubjectsElement;
import web.elements.WhoWeServeElement;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainPage {

    WebDriver driver;

    String header = "//main/header";
    By body = By.xpath("//main/div[contains(@class, 'main-page-container')]");
    By footer = By.xpath("//main/footer");

    By search = By.xpath("//input[@type='search']");
    By closeWindow = By.xpath("//form[@class='country-location-form']//button[@class='close']");

    public MainPage(WebDriver driver) {
        this.driver = driver;

        if (driver.getTitle().contains("Willey")) {
            throw new RuntimeException("It is not ready!");
        }
        sleep(2000);
        driver.findElement(closeWindow).click();
    }

    public WhoWeServeElement pointToTab(String name) {
        WebElement currentTab = driver.findElement(By.xpath(getXPathTab(name)));
        new Actions(driver).moveToElement(currentTab).perform();
        sleep(2000);
        return new WhoWeServeElement(driver, currentTab);
    }

    public SubjectsElement pointToTab2(String name) {
        WebElement currentTab = driver.findElement(By.xpath(getXPathTab(name)));
        new Actions(driver).moveToElement(currentTab).perform();
        sleep(2000);
        return new SubjectsElement(driver, currentTab);
    }

    public SearchResultPage inputAndSubmitText(String text) {
        WebElement item = driver.findElement(search);
        item.sendKeys(text);
        item.submit();
        return new SearchResultPage(driver);
    }

    public SearchResultElement inputText(String text) {
        WebElement input = driver.findElement(search);
        new Actions(driver).moveToElement(input).perform();
        input.sendKeys(text);
        return new SearchResultElement(driver, input);
    }

    //TODO kill me!
    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {

        }
    }

    private String getXPathTab(String tab) {
        return "//a[contains(text(),'" + tab.toUpperCase() + "')]";
    }
}
