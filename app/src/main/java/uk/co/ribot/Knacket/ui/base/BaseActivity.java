package uk.co.ribot.Knacket.ui.base;

import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import uk.co.ribot.Knacket.injection.component.DaggerActivityComponent;
import uk.co.ribot.Knacket.injection.component.ActivityComponent;
import uk.co.ribot.Knacket.injection.module.ActivityModule;
import uk.co.ribot.Knacket.BoilerplateApplication;

public class BaseActivity extends AppCompatActivity {
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        inject();
    }

    public void showUserMessage(String msg, int color) {
        if (getCurrentFocus() != null) {
            Snackbar snackbar = Snackbar.make(getCurrentFocus(), msg, Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(color);
            snackbar.show();
        } else
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public ActivityComponent getComponent() {
        return mActivityComponent;
    }

    private void initializeInjector() {
        if (mActivityComponent == null)
            mActivityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(BoilerplateApplication.getComponent())
                    .activityModule(getActivityModule())
                    .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected void inject() {}
}