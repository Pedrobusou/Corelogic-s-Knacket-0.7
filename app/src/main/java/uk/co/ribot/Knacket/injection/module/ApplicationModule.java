package uk.co.ribot.Knacket.injection.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import uk.co.ribot.Knacket.BoilerplateApplication;
import uk.co.ribot.Knacket.data.api.RestAdapterFactory;
import uk.co.ribot.Knacket.data.api.RestService;
import uk.co.ribot.Knacket.injection.scope.PerApplication;

/**
 * Provide application-level dependencies.
 */

@Module
public class ApplicationModule {
    private final BoilerplateApplication app;

    public ApplicationModule(BoilerplateApplication app) {
        this.app = app;
    }

    @Provides
    @PerApplication
    BoilerplateApplication provideApplication() {
        return app;
    }

    @Provides
    @PerApplication
    Context provideApplicationContext() {
        return app.getApplicationContext();
    }

    @Provides
    @PerApplication
    RestService provideRestService() {
        Retrofit retrofit = RestAdapterFactory.create(app.getApplicationContext());
        return retrofit.create(RestService.class);
    }
}