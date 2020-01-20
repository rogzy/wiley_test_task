package wiley_test_task.web.pages;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import wiley_test_task.web.elements.AbstractPage;
import wiley_test_task.web.elements.SearchResultElement;
import wiley_test_task.web.elements.SubjectsElement;
import wiley_test_task.web.elements.WhoWeServeElement;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainPage extends AbstractPage {

    WebDriver driver;

    By search = By.xpath("//input[@type='search']");
    By closeWindow = By.xpath("//form[@class='country-location-form']//button[@class='close']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        sleep2o(driver, closeWindow).click();
    }

    public WhoWeServeElement pointToTab(String name) {
        By xpath = By.xpath(getXPathSubMenu(name));
        WebElement currentTab = driver.findElement(xpath);
        new Actions(driver).moveToElement(currentTab).perform();
        return new WhoWeServeElement(driver, currentTab);
    }

    public SubjectsElement pointToSubMenuWithSection(String name, String section) {
        WebElement currentTab = driver.findElement(By.xpath(getXPathSubMenu(name)));
        new Actions(driver).moveToElement(currentTab).perform();

        By sectionXpath = By.xpath(getXPathSection(section));
        sleep2o(driver, sectionXpath);
        WebElement educationSection = driver.findElement(sectionXpath);

        new Actions(driver).moveToElement(educationSection).perform();
        return new SubjectsElement(driver, educationSection);
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




}
