package uk.co.ribot.Knacket;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import javax.inject.Inject;

import de.toliart.babbler.data.DataManager;
import de.toliart.babbler.exception.NoInternetException;
import de.toliart.babbler.injection.scope.PerActivity;
import de.toliart.babbler.ui.activity.BaseActivity;
import timber.log.Timber;

@PerActivity
public class ExceptionHandler {
    BaseActivity activity;
    DataManager dataManager;

    @Inject
    public ExceptionHandler(BaseActivity activity, DataManager dataManager) {
        this.activity = activity;
        this.dataManager = dataManager;
    }

    /**
     * Shows user message on exception using Toast.
     *
     * @param throwable exception.
     */
    public void onException(final Throwable throwable) {
        onException(throwable, false);
    }

    /**
     * Shows user message on exception using Snackbar. If current activity for some reason
     * does not have view, ExceptionHandler will use Toast message.
     *
     * @param throwable   exception
     * @param useSnackbar if true will use Snackbar to show error message.
     */
    public void onException(final Throwable throwable, boolean useSnackbar) {
        Handler handler = new Handler(activity.getMainLooper());
        handler.post(() -> handleException(throwable, useSnackbar));
    }

    private void handleException(Throwable throwable, boolean useSnackbar) {
        String errorMessage = activity.getString(pickMessageForException(throwable));

        Timber.e(throwable, activity.getString(pickMessageForException(throwable)));

        if (useSnackbar && activity != null) {
            final int red = ContextCompat.getColor(activity, R.color.colorAccent);
            activity.showUserMessage(errorMessage, red);
        } else {
            Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private int pickMessageForException(Throwable throwable) {
        if (throwable instanceof NoSuchElementException)
            return R.string.no_record_error;
        else if (throwable instanceof NoInternetException || throwable instanceof UnknownHostException)
            return R.string.no_internet_error;
        else if (throwable instanceof SQLException)
            return R.string.sql_error;

        //TODO more

        return R.string.unknown_error;
    }
}
