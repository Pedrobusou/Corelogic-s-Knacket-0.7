package uk.co.ribot.Knacket;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;
import uk.co.ribot.Knacket.injection.component.ApplicationComponent;
import uk.co.ribot.Knacket.injection.component.DaggerApplicationComponent;
import uk.co.ribot.Knacket.injection.module.ApplicationModule;

public class BoilerplateApplication extends Application  {
    private static ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        initializeDagger();
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Fabric.with(this, new Crashlytics());
        }
    }

    public static BoilerplateApplication get(Context context) {
        return (BoilerplateApplication) context.getApplicationContext();
    }

    public static ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    private void initializeDagger() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}