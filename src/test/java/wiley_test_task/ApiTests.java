package wiley_test_task;

import name.falgout.jeffrey.testing.junit.guice.IncludeModule;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import wiley_test_task.api.aut.delay.DelayResponse;
import wiley_test_task.api.aut.wiley.Page;
import wiley_test_task.api.aut.wiley.Product;
import wiley_test_task.api.aut.wiley.SearchResponse;
import wiley_test_task.api.aut.wiley.Suggestion;
import wiley_test_task.api.config.ApiCfg;
import wiley_test_task.api.core.ApiModule;
import wiley_test_task.api.retrofit.AutStep;
import wiley_test_task.api.retrofit.DelayRetrofit;
import wiley_test_task.api.retrofit.WileyRetrofit;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@IncludeModule(ApiModule.class)
@Tag("api")
class ApiTests {

    @Test
    @DisplayName("Проверка атрибутов")
    void checkAttributes(ApiCfg cfg, AutStep autStep) throws IOException {
        String expectedTerm = "<span class=\"search-highlight\">java</span>";
        String expectedProduct = "<span class=\'search-highlight\'>Java</span>";
        WileyRetrofit api = autStep.createClient(cfg.wileyUrl(), WileyRetrofit.class);
        Response<SearchResponse> response = autStep.sendRequest(api.search(cfg.textForSearch()));

        SoftAssertions softly = new SoftAssertions();
        assertThat(response.isSuccessful()).isTrue();

        SearchResponse searchResponse = response.body();
        List<Suggestion> suggestions = searchResponse.getSuggestions();
        List<String> terms = suggestions.stream().map(Suggestion::getTerm).collect(Collectors.toList());
        assertThat(terms).hasSize(4);
        for (String term : terms) {
            assertThat(term).startsWith(expectedTerm);
        }

        List<Product> productList = searchResponse.getProducts();
        List<String> productNames = productList.stream().map(Product::getName).collect(Collectors.toList());
        assertThat(productNames).hasSize(4);
        for (String productName : productNames) {
            softly.assertThat(productName).startsWith(expectedProduct);
        }

        List<Page> pages = searchResponse.getPages();
        List<String> titles = pages.stream().map(Page::getTitle).collect(Collectors.toList());
        for (String title : titles) {
            assertThat(title).contains("Wiley");
        }

        String url = productList.get(0).getImages().get(0).getUrl();
        File fileImage = new File(cfg.dirImage());
        FileUtils.copyURLToFile(new URL(url), fileImage);
        BufferedImage bimg = ImageIO.read(fileImage);

        assertThat(bimg.getWidth()).isEqualTo(300);

        softly.assertAll();
    }

    @Test
    @DisplayName("Проверка таймаута")
    void checkDelay(ApiCfg cfg, AutStep autStep) {
        DelayResponse expected = new DelayResponse("https://www.httpbin.org/delay/1");
        DelayRetrofit api = autStep.createClient(cfg.delayUrl(), DelayRetrofit.class);
        Response<DelayResponse> response = autStep.sendRequest(api.delay(1));
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.body()).isEqualTo(expected);
    }
}
