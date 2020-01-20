package web.config;

import org.aeonbits.owner.Config;

public interface WebCfg extends Config {

    @DefaultValue("C:\\WebDrivers\\chromedriver.exe")
    String chromeDriver();

    @DefaultValue("C:\\WebDrivers\\geckodriver.exe")
    String geckoDriver();

    @DefaultValue("Java")
    String textForSearch();

    @DefaultValue("https://www.wiley.com/en-us")
    String initUrl();
}
