package uk.co.ribot.Knacket.injection.component;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.data.SyncService;
import uk.co.ribot.Knacket.data.local.DatabaseHelper;
import uk.co.ribot.Knacket.data.local.PreferencesHelper;
import uk.co.ribot.Knacket.data.remote.BuyersService;
import uk.co.ribot.Knacket.injection.ApplicationContext;
import uk.co.ribot.Knacket.injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    BuyersService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    Bus eventBus();

}
