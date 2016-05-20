package uk.co.ribot.Knacket.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import javax.inject.Inject;

import uk.co.ribot.Knacket.injection.scope.PerApplication;

@PerApplication
public class PreferencesManager {
    private final static String PREFERENCES_FILE_NAME = "app_preferences";

    private final static String TOKEN = "token";

    private SharedPreferences preferences;

    @Inject
    public PreferencesManager(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Nullable
    public String getToken() {
        return preferences.getString(TOKEN, null);
    }

    public void setToken(@Nullable String token) {
        preferences.edit().putString(TOKEN, "Bearer " + token).apply();
    }
}