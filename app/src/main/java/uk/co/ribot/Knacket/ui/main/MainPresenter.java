package uk.co.ribot.Knacket.ui.main;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.data.model.Ad;
import uk.co.ribot.Knacket.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadBuyers() {
        checkViewAttached();
        mSubscription = mDataManager.getBuyers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Ad>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the buyers.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Ad> ads) {
                        if (ads.isEmpty()) {
                            getMvpView().showBuyersEmpty();
                        } else {
                            getMvpView().showBuyers(ads);
                        }
                    }
                });
    }
}