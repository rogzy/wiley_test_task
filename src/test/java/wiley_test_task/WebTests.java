package wiley_test_task;

import config.Cfg;
import org.aeonbits.owner.ConfigCache;
import org.junit.jupiter.api.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import web.Expected;
import web.pages.MainPage;
import web.pages.SearchResultPage;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("web")
public class WebTests {

    WebDriver driver;
    Cfg cfg = ConfigCache.getOrCreate(Cfg.class, System.getenv(), System.getProperties());

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", cfg.chromeDriver());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.wiley.com/en-us");
    }

    @Test
    @DisplayName("Items under Who We Serve for sub-header")
    void checkItemsUnderSubHeader() {
        MainPage mainPage = new MainPage(driver);
        List<WebElement> tabItems = mainPage.pointToTab("Who We Serve").getItems();
        List<String> actualList = tabItems.stream().map(WebElement::getText).collect(toList());
        assertThat(actualList).containsAll(Expected.firstTaskExpectedList());
    }

    @Test
    @DisplayName("Search functionality [preview]")
    void checkSearchPreview() {
        MainPage mainPage = new MainPage(driver);
        WebElement result = mainPage.inputText(cfg.textForSearch()).getResult();
        assertThat(result.isDisplayed()).isTrue();
    }

    @Test
    @DisplayName("Search functionality")
    void checkSearch() {
        MainPage mainPage = new MainPage(driver);
        SearchResultPage searchResultPage = mainPage.inputAndSubmitText(cfg.textForSearch());
        for (int i = 1; i <= 10; i++) {
            assertThat(searchResultPage.getProduct(i).getTitle()).contains(cfg.textForSearch());
        }
        assertThat(searchResultPage.getProducts()).hasSize(10);

        for (int i = 1; i <= 10; i++) {
            List<WebElement> tabs = searchResultPage.getProduct(i).getTabs();
            for (WebElement tab : tabs) {
                if (tab.getAttribute("class").equals("active")) {
                    checkProduct(tab);
                } else {
                    tab.click();
                    checkProduct(tab);
                }
            }
        }
    }

    @Test
    @DisplayName("Subjects in Education")
    void checkItemsSectionEducation() {
        MainPage mainPage = new MainPage(driver);
        List<WebElement> actualSubList = mainPage.pointToSubMenuWithSection("Subjects", "Education").getItems();
        List<String> actual = actualSubList.stream().map(WebElement::getText).collect(toList());
        List<String> expected = Expected.fourthTaskExpectedList();
        assertThat(actual).containsAll(expected);

    }

    @AfterEach
    void tearDown() {
        driver.close();
    }

    private void checkProduct(WebElement t) {
        WebElement activeTab = t.findElement(By.xpath("./a/div[1]"));
        switch (activeTab.getText()) {
            case "E-BOOK":
            case "PRINT":
                WebElement activeTabButton = t.findElement(By.xpath("./../..//div[@class='tab-pane active']//button"));
                assertThat(activeTabButton.getText()).isEqualToIgnoringCase("ADD TO CART");
                break;
            case "O-BOOK":
                WebElement activeTabView = t.findElement(By.xpath("./../..//div[@class='tab-pane active']//a[contains(@class, 'learn-more-button')]"));
                assertThat(activeTabView.getText()).isEqualToIgnoringCase("View on Wiley Online Library");
                break;
        }
    }


}
