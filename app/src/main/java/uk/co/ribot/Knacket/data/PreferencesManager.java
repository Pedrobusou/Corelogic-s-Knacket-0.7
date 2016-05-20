package uk.co.ribot.Knacket.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import javax.inject.Inject;

import uk.co.ribot.Knacket.injection.scope.PerApplication;

@PerApplication
public class PreferencesManager {
    private final static String PREFERENCES_FILE_NAME = "app_preferences";

    private final static String FIELD_APP_HASH = "app_hash";

    private SharedPreferences preferences;

    @Inject
    public PreferencesManager(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Nullable
    public String getAppHash() {
        return preferences.getString(FIELD_APP_HASH, null);
    }

    public void setAppHash(@Nullable String app_hash) {
        preferences.edit().putString(FIELD_APP_HASH, app_hash).apply();
    }
}