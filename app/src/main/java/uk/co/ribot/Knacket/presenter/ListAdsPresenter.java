package uk.co.ribot.Knacket.presenter;

import javax.inject.Inject;
import rx.Subscription;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.injection.scope.PerFragment;
import uk.co.ribot.Knacket.presenter.fragment.BasePresenter;
import uk.co.ribot.Knacket.ui.fragment.ListAds;

@PerFragment
public class ListAdsPresenter extends BasePresenter<ListAds> {
    private final DataManager dataManager;
    private Subscription subscription;

    @Inject
    public ListAdsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public DataManager getDataManager(){
        return dataManager;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
    }
}