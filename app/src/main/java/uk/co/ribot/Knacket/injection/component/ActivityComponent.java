package uk.co.ribot.Knacket.injection.component;

import dagger.Component;
import uk.co.ribot.Knacket.injection.PerActivity;
import uk.co.ribot.Knacket.injection.module.ActivityModule;
import uk.co.ribot.Knacket.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
