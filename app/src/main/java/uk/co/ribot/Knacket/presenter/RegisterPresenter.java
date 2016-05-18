package uk.co.ribot.Knacket.presenter;

import android.content.Context;
import android.telephony.TelephonyManager;
import javax.inject.Inject;

import rx.Subscription;
import uk.co.ribot.Knacket.ExceptionHandler;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.injection.scope.PerFragment;
import uk.co.ribot.Knacket.presenter.fragment.BasePresenter;
import uk.co.ribot.Knacket.ui.fragment.Register;

@PerFragment
public class RegisterPresenter extends BasePresenter<Register> {
    ExceptionHandler exceptionHandler;
    Subscription subscription;
    DataManager dataManager;
    Context context;

    @Inject
    public RegisterPresenter(DataManager dataManager, ExceptionHandler exceptionHandler, Context context) {
        this.exceptionHandler = exceptionHandler;
        this.dataManager = dataManager;
        this.context = context;
    }

    /*public void register(String name, String email, String pass) {
        subscription = dataManager.api().register(new RegisterRequest(name, email, pass))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegisterResponse>() {
                    @Override
                    public void onCompleted() {
                        context.startService(new Intent(context, ContactSyncIntentService.class));
                        //context.startService(new Intent(context, GroupSyncIntentService.class));
                        getFragment().dismissLoadingDialog();
                        getFragment().isAdded();
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
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }


    private String getDeviceId() {
        String androidId = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        if (androidId != null)
            return androidId;

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
}