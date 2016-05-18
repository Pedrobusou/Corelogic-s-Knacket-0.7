package uk.co.ribot.Knacket.presenter.activity;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import javax.inject.Inject;

import uk.co.ribot.Knacket.ExceptionHandler;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.injection.scope.PerActivity;
import uk.co.ribot.Knacket.ui.main.MainActivity;
import rx.Subscription;

@PerActivity
public class MainActivityPresenter extends BasePresenter<MainActivity> {
    DataManager dataManager;
    Context context;
    Subscription subscription;
    ExceptionHandler exceptionHandler;

    @Inject
    public MainActivityPresenter(DataManager dataManager, Context context, ExceptionHandler exceptionHandler) {
        this.dataManager = dataManager;
        this.context = context;
        this.exceptionHandler = exceptionHandler;
    }

    /*public void updateToken(EditGcmIdRequest token) {
        subscription = dataManager.api().updateTokenRS(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(Void registrationResponse) {}
                });
    }*/

    public String getRealPathFromURI(Uri uri) {
        // Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }
}