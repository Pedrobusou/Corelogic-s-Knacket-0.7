package uk.co.ribot.Knacket.data;

import javax.inject.Inject;
import uk.co.ribot.Knacket.data.api.RestService;
import uk.co.ribot.Knacket.injection.scope.PerApplication;

@PerApplication
public class DataManager {
    private final PreferencesManager preferencesManager;
    private final DatabaseFacade databaseFacade;
    private final RestService restService;

    @Inject
    public DataManager(DatabaseFacade databaseFacade, PreferencesManager preferencesManager , RestService restService) {
        this.preferencesManager = preferencesManager;
        this.databaseFacade = databaseFacade;
        this.restService = restService;
    }

    public PreferencesManager getPreferences() {
        return preferencesManager;
    }

    public DatabaseFacade db() {
        return databaseFacade;
    }

    public RestService api() {
        return restService;
    }
}