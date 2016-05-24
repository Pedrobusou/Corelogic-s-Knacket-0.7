package uk.co.ribot.Knacket.service;

import android.app.IntentService;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.Knacket.BoilerplateApplication;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.data.api.model.response.AdsResponse;
import uk.co.ribot.Knacket.data.local.model.AdDatabase;
import uk.co.ribot.Knacket.data.local.model.TagDatabase;
import uk.co.ribot.Knacket.data.local.model.UserDatabase;
import uk.co.ribot.Knacket.data.local.model.UserProfileDatabase;
import uk.co.ribot.Knacket.data.model.Ad;
import rx.Subscriber;
import rx.Subscription;
import uk.co.ribot.Knacket.event.AdSyncFinishedEvent;

public class AdService extends IntentService {
    @Inject
    DataManager dataManager;
    Subscription subscription;

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
        Timber.i("onHandleIntent");
        subscription = dataManager.api().getAdsWeekend(dataManager.getPreferences().getToken(), "10.0", "10.0", 100, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AdsResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.i("onCompleted");
                        EventBus.getDefault().post(new AdSyncFinishedEvent());
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
                        Timber.i("!onError "+e.getMessage());
                        Timber.i("onError "+e.getCause());
                    }

                    @Override
                    public void onNext(AdsResponse ads) {
                        Timber.i("onNext"+ads.toString());
                        int i=0;
                        for(Ad ad : ads.getBuyerAds()){
                            Timber.i("i: "+i);
                            i++;
                            TagDatabase tagDatabase=new TagDatabase(ad.getTag());
                            try {
                                dataManager.db().saveTagDatabase(tagDatabase);  //Save ads on Phone database, right?
                            } catch (java.sql.SQLException e) {
                                e.printStackTrace();
                            }
                            UserDatabase userDatabase=new UserDatabase(ad.getUser());
                            try {
                                dataManager.db().saveUserDatabase(userDatabase);  //Save ads on Phone database, right?
                            } catch (java.sql.SQLException e) {
                                e.printStackTrace();
                            }
                            UserProfileDatabase userProfileDatabase=new UserProfileDatabase(ad.getUser_profile());
                            try {
                                dataManager.db().saveUserProfileDatabase(userProfileDatabase);  //Save ads on Phone database, right?
                            } catch (java.sql.SQLException e) {
                                e.printStackTrace();
                            }


                            AdDatabase adDatabase = new AdDatabase(ad,userDatabase,userProfileDatabase,tagDatabase);


                           // Timber.i("ad: "+ad.toString());
                           try {
                                dataManager.db().saveAd(adDatabase);  //Save ads on Phone database, right?
                            } catch (java.sql.SQLException e) {
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