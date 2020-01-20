package wiley_test_task.api.retrofit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wiley_test_task.web.config.WebCfg;

import java.io.IOException;

public class AutStep {

    public <T> T createClient(String baseUrl, Class<T> zClass) {

        Retrofit client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return client.create(zClass);
    }

    public <T> Response<T> sendRequest(Call<T> call) {
        try {
            return call.execute();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public WebDriver getWebDriver(WebCfg webCfg) {
        System.setProperty("webdriver.chrome.driver", webCfg.chromeDriver());
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(webCfg.initUrl());
        return driver;
    }
}
