package api.core;

import api.config.ApiCfg;
import api.retrofit.AutStep;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.aeonbits.owner.ConfigCache;

public class ApiModule extends AbstractModule {

    @Provides
    ApiCfg getCfg() {
        return ConfigCache.getOrCreate(ApiCfg.class, System.getenv(), System.getProperties());
    }

    @Provides
    AutStep getTech() {
        return new AutStep();
    }
}
