package uk.co.ribot.Knacket.injection.component;

import android.content.Context;
import dagger.Component;
import uk.co.ribot.Knacket.BoilerplateApplication;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.data.DatabaseFacade;
import uk.co.ribot.Knacket.injection.module.ApplicationModule;
import uk.co.ribot.Knacket.injection.scope.PerApplication;
import uk.co.ribot.Knacket.service.AdService;

@PerApplication
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BoilerplateApplication app);

    BoilerplateApplication app();

    Context applicationContext();

    DataManager dataManager();

    DatabaseFacade databaseFacade();

    void inject(AdService adService);
}