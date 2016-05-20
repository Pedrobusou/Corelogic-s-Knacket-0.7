package uk.co.ribot.Knacket.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.presenter.LoginPresenter;
import uk.co.ribot.Knacket.presenter.RegisterPresenter;
import uk.co.ribot.Knacket.ui.fragment.base.BasePresenterFragment;

public class Login extends BasePresenterFragment<LoginPresenter> {
    @Inject LoginPresenter presenter;

    @Bind(R.id.etLogMail) EditText logMail;
    @Bind(R.id.etLogPass) EditText logPass;

    private String email, pass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login,container,false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.btnLogLogin) void login(){
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass))
            Toast.makeText(getContext(), "Fill fields correctly", Toast.LENGTH_SHORT).show();
        else {
            showLoadingDialog(R.string.loading, R.string.loging);
            getPresenter().login(email, pass);
        }
    }

    @OnTextChanged(R.id.etLogMail) void logMail(){
        if(!TextUtils.isEmpty(logMail.getText().toString()) && (!isEmailValid(logMail.getText().toString()))){
            logMail.setError(Html.fromHtml("<font color='#FFFFFF'>Invalid email</font>"));
            email = "";
        } else
            email = logMail.getText().toString();
    }

    @OnTextChanged(R.id.etLogPass) void regPass(){
        if (!TextUtils.isEmpty(logPass.getText().toString())) {
            if (logPass.getText().toString().length() < 8) {
                logPass.setError(Html.fromHtml("<font color='#FFFFFF'>Min 8 chars</font>"));
                pass = "";
            } else
                pass = logPass.getText().toString();
        }
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    protected LoginPresenter getPresenter() {
        return presenter;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that activity.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }
}