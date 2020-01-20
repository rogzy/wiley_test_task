package wiley_test_task.api.core;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.aeonbits.owner.ConfigCache;
import wiley_test_task.api.config.ApiCfg;
import wiley_test_task.api.retrofit.AutStep;

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
