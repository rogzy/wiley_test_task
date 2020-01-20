package wiley_test_task.web.config;

import org.aeonbits.owner.Reloadable;

public interface WebCfg extends Reloadable {

    @DefaultValue("C:\\WebDrivers\\chromedriver.exe")
    String chromeDriver();

    @DefaultValue("Java")
    String textForSearch();

    @DefaultValue("https://www.wiley.com/en-us")
    String initUrl();
}
