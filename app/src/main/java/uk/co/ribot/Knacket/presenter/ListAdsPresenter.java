package uk.co.ribot.Knacket.presenter;

import android.content.Context;
import android.widget.Toast;
import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.ribot.Knacket.ExceptionHandler;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.data.api.model.request.LoginRequest;
import uk.co.ribot.Knacket.data.api.model.response.TokenResponse;
import uk.co.ribot.Knacket.injection.scope.PerFragment;
import uk.co.ribot.Knacket.presenter.fragment.BasePresenter;
import uk.co.ribot.Knacket.ui.fragment.ListAds;

@PerFragment
public class ListAdsPresenter extends BasePresenter<ListAds> {
    private final ExceptionHandler exceptionHandler;
    private final DataManager dataManager;
    private final Context context;
    private Subscription subscription;

    @Inject
    public ListAdsPresenter(DataManager dataManager, ExceptionHandler exceptionHandler, Context context) {
        this.exceptionHandler = exceptionHandler;
        this.dataManager = dataManager;
        this.context = context;
    }

    /*public void login(String email, String password) {
        subscription = dataManager.api().login(new LoginRequest(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TokenResponse>() {
                    @Override
                    public void onCompleted() {
                        getFragment().dismissLoadingDialog();
                        Toast.makeText(context, "Logged in", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getFragment().dismissLoadingDialog();
                        exceptionHandler.onException(e, true);
                    }

                    @Override
                    public void onNext(TokenResponse token) {
                        dataManager.getPreferences().setToken(token.getToken());
                        getFragment().getActivity().finish();
                    }
                });
    }*/

    public DataManager getDataManager(){
        return dataManager;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
    }
}