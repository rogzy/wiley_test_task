package wiley_test_task;

import api.retrofit.AutStep;
import com.google.inject.Inject;
import name.falgout.jeffrey.testing.junit.guice.IncludeModule;
import web.config.WebCfg;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import web.core.WebModule;
import web.pages.MainPage;
import web.pages.SearchResultPage;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@IncludeModule(WebModule.class)
@Tag("web")
class WebTests {

    WebDriver driver;

    @Inject
    AutStep step;

    @Inject
    WebCfg webCfg;

    @BeforeEach
    void setUp() {
        driver = step.getWebDriver(webCfg);
    }

    @Test
    @DisplayName("Проверка подменю Who We Serve")
    void checkItemsSubMenu() {
        MainPage mainPage = new MainPage(driver);
        List expectedList = Arrays.asList(
                "Students",
                "Instructors",
                "Book Authors",
                "Professionals",
                "Researchers",
                "Institutions",
                "Librarians",
                "Corporations",
                "Societies",
                "Journal Editors",
                "Government");
        List<WebElement> tabItems = mainPage.pointToTab("Who We Serve").getItems();
        List<String> actualList = tabItems.stream().map(WebElement::getText).collect(toList());
        assertThat(actualList).containsAll(expectedList);
    }

    @Test
    @DisplayName("Search functionality [preview]")
    void checkSearchPreview(WebCfg cfg) {
        MainPage mainPage = new MainPage(driver);
        WebElement result = mainPage.inputText(cfg.textForSearch()).getResult();
        assertThat(result.isDisplayed()).isTrue();
    }

    @Test
    @DisplayName("Search functionality")
    void checkResultSearch(WebCfg cfg) {
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
        List<String> expected = Arrays.asList(
                "Assessment, Evaluation Methods",
                "Classroom Management",
                "Conflict Resolution & Mediation",
                "Curriculum Tools",
                "Education & Public Policy",
                "Educational Research",
                "General Education",
                "Higher Education",
                "Information & Library Science",
                "Special Education",
                "Special Topics",
                "Vocational Technology");
        List<WebElement> actualSubList = mainPage.pointToSubMenuWithSection("Subjects", "Education").getItems();
        List<String> actual = actualSubList.stream().map(WebElement::getText).collect(toList());
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
