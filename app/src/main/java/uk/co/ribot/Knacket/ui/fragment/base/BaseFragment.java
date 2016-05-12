package uk.co.ribot.Knacket.ui.fragment.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;

import butterknife.ButterKnife;
import de.toliart.babbler.injection.component.DaggerFragmentComponent;
import de.toliart.babbler.injection.component.FragmentComponent;
import de.toliart.babbler.injection.module.FragmentModule;
import de.toliart.babbler.ui.activity.BaseActivity;

public abstract class BaseFragment extends Fragment {
    private FragmentComponent fragmentComponent;

    AlertDialog alertDialog;

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    @Override
    public void onDestroyView() {
        dismissLoadingDialog();
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    protected void inject() {
        //no injection by default
    }

    private void initDagger() {
        fragmentComponent = DaggerFragmentComponent.builder()
                .activityComponent(((BaseActivity) getActivity()).getComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    public final FragmentComponent getComponent() {
        return fragmentComponent;
    }

    protected void onAttachToContext(Context context) {
        initDagger();
        inject();
    }

    public void showLoadingDialog(int titleResId, int messageResId) {
        showLoadingDialog(getString(titleResId), getString(messageResId));
    }

    public void showLoadingDialog(String title, String message) {
        dismissLoadingDialog();

        alertDialog = new ProgressDialog.Builder(getContext())
                .setCancelable(false)
                .setTitle(title)
                .setMessage(message)
                .show();

//        alertDialog = new AlertDialog.Builder(getContext())
//                .setCancelable(false)
//                .setTitle(title)
//                .setMessage(message)
//                .setView(R.layout.alert_loading_content)
//                .show();
    }

    public void dismissLoadingDialog() {
        if (alertDialog != null) {
            alertDialog.dismiss();
            alertDialog = null;
        }
    }
}
