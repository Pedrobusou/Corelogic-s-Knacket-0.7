package uk.co.ribot.Knacket.service;

import android.app.IntentService;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.Knacket.BoilerplateApplication;
import uk.co.ribot.Knacket.ExceptionHandler;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.data.api.model.request.AdRequest;
import uk.co.ribot.Knacket.data.api.model.response.AdsResponse;
import uk.co.ribot.Knacket.data.model.Ad;
import rx.Subscriber;
import rx.Subscription;
import uk.co.ribot.Knacket.event.AdSavedEvent;

public class AdService extends IntentService {
    @Inject
    DataManager dataManager;
    Subscription subscription;
    //ExceptionHandler exceptionHandler;

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
        Timber.i("onhandleintent");
        subscription = dataManager.api().getAdsWeekend(dataManager.getPreferences().getToken(), "10.0", "10.0", 100, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AdsResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.i("onCompleted");
                     /*   try {
                            List<Ad> ads = dataManager.db().getAdList();
                            for(Ad ad : ads) dataManager.db().saveAd(ad); //SHOULD THIS DELETE ADS?
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        EventBus.getDefault().post(new AdSavedEvent(AdSavedEvent.PARAM_DELETE));*/
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.i("onerror");
                      //  exceptionHandler.onException(e, true);
                    }

                    @Override
                    public void onNext(AdsResponse ads) {
                        Timber.i("onNext");
                        for(Ad ad : ads.getBuyerAds()){
                            Timber.i("ad: "+ad.toString());
                            try {
                                dataManager.db().saveAd(ad);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
        super.onDestroy();
    }
}