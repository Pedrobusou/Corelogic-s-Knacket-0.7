package uk.co.ribot.Knacket.injection.component;

import android.content.Context;
import dagger.Component;
import uk.co.ribot.Knacket.BoilerplateApplication;
import uk.co.ribot.Knacket.ExceptionHandler;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.injection.scope.PerActivity;
import uk.co.ribot.Knacket.injection.module.ActivityModule;
import uk.co.ribot.Knacket.ui.base.BaseActivity;
import uk.co.ribot.Knacket.ui.main.BuyerProfile;
import uk.co.ribot.Knacket.ui.main.MainActivity;

@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(BuyerProfile bPActivity);

    BaseActivity activity();

    Context context();

    BoilerplateApplication app();

    ExceptionHandler exceptionHandler();

    DataManager dataManager();


}