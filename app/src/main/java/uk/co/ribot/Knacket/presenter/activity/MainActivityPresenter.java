package uk.co.ribot.Knacket.presenter.activity;

import android.content.Context;

import javax.inject.Inject;

import uk.co.ribot.Knacket.ExceptionHandler;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.injection.scope.PerActivity;
import uk.co.ribot.Knacket.ui.main.MainActivity;

@PerActivity
public class MainActivityPresenter extends BasePresenter<MainActivity> {
    DataManager dataManager;
    Context context;
    ExceptionHandler exceptionHandler;

    @Inject
    public MainActivityPresenter(DataManager dataManager, Context context, ExceptionHandler exceptionHandler) {
        this.dataManager = dataManager;
        this.context = context;
        this.exceptionHandler = exceptionHandler;
    }
}