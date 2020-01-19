package config;

import org.aeonbits.owner.Config;

public interface Cfg extends Config {

    @DefaultValue("C:\\WebDrivers\\chromedriver.exe")
    String chromeDriver();

    @DefaultValue("C:\\WebDrivers\\geckodriver.exe")
    String geckoDriver();
}
