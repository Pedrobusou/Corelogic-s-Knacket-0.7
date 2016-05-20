package uk.co.ribot.Knacket.service;

import android.app.IntentService;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.ribot.Knacket.BoilerplateApplication;
import uk.co.ribot.Knacket.ExceptionHandler;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.data.api.model.request.AdRequest;
import uk.co.ribot.Knacket.data.model.Ad;
import rx.Subscriber;
import rx.Subscription;
import uk.co.ribot.Knacket.event.AdSavedEvent;

public class AdService extends IntentService {
    @Inject
    DataManager dataManager;
    Subscription subscription;
    ExceptionHandler exceptionHandler;

    public AdService() {
        super("AdService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BoilerplateApplication.getComponent().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        AdRequest request = new AdRequest("","","","","","","","","","","");

        subscription = dataManager.api().createAd(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        try {
                            List<Ad> ads = dataManager.db().getAdList();
                            for(Ad ad : ads) dataManager.db().saveAd(ad);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        EventBus.getDefault().post(new AdSavedEvent(AdSavedEvent.PARAM_DELETE));
                    }

                    @Override
                    public void onError(Throwable e) {
                        //exceptionHandler.onException(e, true);
                    }

                    @Override
                    public void onNext(Void aVoid) { }
                });
    }

    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
        super.onDestroy();
    }
}