package wiley_test_task.web.core;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.aeonbits.owner.ConfigCache;
import wiley_test_task.web.config.WebCfg;

public class WebModule extends AbstractModule {

    @Provides
    WebCfg getCfg() {
        return ConfigCache.getOrCreate(WebCfg.class, System.getenv(), System.getProperties());
    }

}
