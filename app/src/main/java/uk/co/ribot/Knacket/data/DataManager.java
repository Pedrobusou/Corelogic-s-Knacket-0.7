package uk.co.ribot.Knacket.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;
import uk.co.ribot.Knacket.data.local.DatabaseHelper;
import uk.co.ribot.Knacket.data.local.PreferencesHelper;
import uk.co.ribot.Knacket.data.model.Ad;
import uk.co.ribot.Knacket.data.remote.BuyersService;
import uk.co.ribot.Knacket.util.EventPosterHelper;

@Singleton
public class DataManager {

    private final BuyersService mBuyersService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final EventPosterHelper mEventPoster;

    @Inject
    public DataManager(BuyersService buyersService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, EventPosterHelper eventPosterHelper) {
        mBuyersService = buyersService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mEventPoster = eventPosterHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<Ad> syncBuyers() {
        return mBuyersService.getBuyers()
                .concatMap(new Func1<List <Ad>, Observable<Ad>>() {
                    @Override
                    public Observable<Ad> call(List<Ad> ads) {
                        return mDatabaseHelper.setBuyers(ads);
                    }
                });
    }

    public Observable<List<Ad>> getBuyers() {
        return mDatabaseHelper.getBuyers().distinct();
    }


    /// Helper method to post events from doOnCompleted.
    private Action0 postEventAction(final Object event) {
        return new Action0() {
            @Override
            public void call() {
                mEventPoster.postEventSafely(event);
            }
        };
    }
}