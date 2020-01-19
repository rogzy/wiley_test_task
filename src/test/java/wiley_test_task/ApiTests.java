package wiley_test_task;

import api.response.DelayResponse;
import api.response.Page;
import api.response.Product;
import api.response.SearchResponse;
import api.retrofit.DelayRetrofit;
import api.retrofit.Tech;
import api.retrofit.WileyRetrofit;
import config.Cfg;
import org.aeonbits.owner.ConfigCache;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("api")
public class ApiTests {

    Cfg cfg = ConfigCache.getOrCreate(Cfg.class, System.getenv(), System.getProperties());

    @Test
    @DisplayName("Проверка атрибутов")
    void checkAttributes() throws IOException {
        String expectedTerm = "<span class=\"search-highlight\">java</span>";
        String expectedProduct = "<span class=\'search-highlight\'>Java</span>";
        WileyRetrofit api = Tech.createClient(cfg.wileyUrl(), WileyRetrofit.class);

        Response<SearchResponse> response = api.search(cfg.textForSearch()).execute();
        assertThat(response.isSuccessful()).isTrue();

        List<String> terms = response.body().getSuggestions().stream().map(el -> el.getTerm()).collect(Collectors.toList());

        assertThat(terms).hasSize(4);
        for (String term : terms) {
            assertThat(term).startsWith(expectedTerm);
        }

        List<Product> productList = response.body().getProducts();
        List<String> productNames = productList.stream().map(Product::getName).collect(Collectors.toList());
        assertThat(productNames).hasSize(4);
        SoftAssertions softly = new SoftAssertions();
        for (String productName : productNames) {
            softly.assertThat(productName).startsWith(expectedProduct);
        }

        List<String> titles = response.body().getPages().stream().map(Page::getTitle).collect(Collectors.toList());
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
    void checkDelay() throws IOException {
        DelayRetrofit api = Tech.createClient(cfg.delayUrl(), DelayRetrofit.class);
        Response<DelayResponse> response = api.delay(1).execute();
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.body().getUrl()).isEqualTo("https://httpbin.org/delay/1");
    }
}
