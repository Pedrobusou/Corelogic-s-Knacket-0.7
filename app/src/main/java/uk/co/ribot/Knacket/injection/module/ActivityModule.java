package uk.co.ribot.Knacket.injection.module;

import android.support.v4.app.FragmentManager;
import dagger.Module;
import dagger.Provides;
import uk.co.ribot.Knacket.injection.scope.PerActivity;
import uk.co.ribot.Knacket.ui.base.BaseActivity;

@Module
public class ActivityModule {
    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public BaseActivity provideActivity() {
        return activity;
    }

    @Provides
    @PerActivity
    public FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }
}