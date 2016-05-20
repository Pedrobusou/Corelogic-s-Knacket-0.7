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
import uk.co.ribot.Knacket.data.api.model.request.RegisterRequest;
import uk.co.ribot.Knacket.data.api.model.response.TokenResponse;
import uk.co.ribot.Knacket.injection.scope.PerFragment;
import uk.co.ribot.Knacket.presenter.fragment.BasePresenter;
import uk.co.ribot.Knacket.ui.fragment.Register;

@PerFragment
public class RegisterPresenter extends BasePresenter<Register> {
    private final ExceptionHandler exceptionHandler;
    private final DataManager dataManager;
    private final Context context;
    private Subscription subscription;

    @Inject
    public RegisterPresenter(DataManager dataManager, ExceptionHandler exceptionHandler, Context context) {
        this.exceptionHandler = exceptionHandler;
        this.dataManager = dataManager;
        this.context = context;
    }

    public void register(String name, String email, String password,String repeatPass) {
        subscription = dataManager.api().register(new RegisterRequest(name, email, password,repeatPass))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TokenResponse>() {
                    @Override
                    public void onCompleted() {
                        getFragment().dismissLoadingDialog();
                        Toast.makeText(context, "Registration success", Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
    }
}