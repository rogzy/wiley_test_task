package wiley_test_task.api.config;

import org.aeonbits.owner.Config;

public interface ApiCfg extends Config {

    @DefaultValue("https://www.wiley.com")
    String wileyUrl();

    @DefaultValue("https://www.httpbin.org/")
    String delayUrl();

    @DefaultValue("build/tmp/image.jpg")
    String dirImage();

    @DefaultValue("Java")
    String textForSearch();
}
