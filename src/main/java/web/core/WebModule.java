package web.core;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.aeonbits.owner.ConfigCache;
import web.config.WebCfg;

public class WebModule extends AbstractModule {

    @Provides
    WebCfg getCfg() {
        return ConfigCache.getOrCreate(WebCfg.class, System.getenv(), System.getProperties());
    }

}
