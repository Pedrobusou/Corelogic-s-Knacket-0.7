package uk.co.ribot.Knacket.presenter;

/**
 * Created by pedroramos on 12.05.16.
 */
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import javax.inject.Inject;

import rx.Subscriber;
import uk.co.ribot.Knacket.ExceptionHandler;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.data.api.model.request.RegisterRequest;
import uk.co.ribot.Knacket.data.api.model.response.RegisterResponse;
import uk.co.ribot.Knacket.injection.scope.PerFragment;
import uk.co.ribot.Knacket.ui.base.BasePresenter;
import uk.co.ribot.Knacket.ui.fragment.Register;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@PerFragment
public class RegisterPresenter extends BasePresenter<Register> {
    DataManager dataManager;
    ExceptionHandler exceptionHandler;
    Context context;
    Subscription subscription;

    @Inject
    public RegisterPresenter(DataManager dataManager, ExceptionHandler exceptionHandler, Context context) {
        this.dataManager = dataManager;
        this.exceptionHandler = exceptionHandler;
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    public void register(String name, String email, String pass) {
        subscription = dataManager.api().register(new RegisterRequest(name, email, pass))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegisterResponse>() {
                    @Override
                    public void onCompleted() {
                        context.startService(new Intent(context, ContactSyncIntentService.class));
                        //context.startService(new Intent(context, GroupSyncIntentService.class));
                        getFragment().goToNextStape();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getFragment().dismissLoadingDialog();
                        exceptionHandler.onException(e, true);
                    }

                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        dataManager.getPreferences().setToken(registerResponse.getApplicationHash());
                    }
                });
    }

    private String getDeviceId() {
        String androidId = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        if (androidId != null) {
            return androidId;
        }

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
}
