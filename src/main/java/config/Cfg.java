package config;

import org.aeonbits.owner.Config;

public interface Cfg extends Config {

    @DefaultValue("C:\\WebDrivers\\chromedriver.exe")
    String chromeDriver();

    @DefaultValue("C:\\WebDrivers\\geckodriver.exe")
    String geckoDriver();

    @DefaultValue("Java")
    String textForSearch();

    @DefaultValue("https://www.wiley.com")
    String wileyUrl();

    @DefaultValue("https://www.httpbin.org/")
    String delayUrl();

    @DefaultValue("build/tmp/image.jpg")
    String dirImage();
}
