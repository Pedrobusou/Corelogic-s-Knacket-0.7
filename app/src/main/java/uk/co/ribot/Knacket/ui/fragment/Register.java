package uk.co.ribot.Knacket.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.presenter.RegisterPresenter;
import uk.co.ribot.Knacket.ui.fragment.base.BasePresenterFragment;

public class Register extends BasePresenterFragment<RegisterPresenter> {
    @Inject RegisterPresenter presenter;

    @Bind(R.id.etRegName) EditText regName;
    @Bind(R.id.etRegMail) EditText regMail;
    @Bind(R.id.etRegPass) EditText regPass;
    @Bind(R.id.etRegRepPass) EditText regRepPass;

    private String name, email, pass, repeatPass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register,container,false);
        ButterKnife.bind(this, rootView);
        //EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btnRegRegister) void register(){
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repeatPass))
            Toast.makeText(getContext(), "Fill fields correctly", Toast.LENGTH_SHORT).show();
        else if(!pass.equals(repeatPass))
            Toast.makeText(getContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
        else {
            showLoadingDialog(R.string.loading, R.string.registering);
            //getPresenter().register(name, email, password);
        }
    }

    @OnTextChanged(R.id.etRegName) void regName(){
        if(!TextUtils.isEmpty(regName.getText().toString())){
            name = regName.getText().toString();
        } else name = "";
    }

    @OnTextChanged(R.id.etRegMail) void regMail(){
        if(!TextUtils.isEmpty(regMail.getText().toString()) && (!isEmailValid(regMail.getText().toString()))){
            regMail.setError(Html.fromHtml("<font color='#FFFFFF'>Invalid email</font>"));
            email = "";
        } else email = regMail.getText().toString();
    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @OnTextChanged(R.id.etRegPass) void regPass(){
        if (!TextUtils.isEmpty(regPass.getText().toString())) {
            if (regPass.getText().toString().length() < 8) {
                regPass.setError(Html.fromHtml("<font color='#FFFFFF'>Min 8 chars</font>"));
                pass = "";
            } else pass = regPass.getText().toString();
        }
    }

    @OnTextChanged(R.id.etRegRepPass) void regRepPass(){
        if (!TextUtils.isEmpty(regRepPass.getText().toString())) {
            if (regRepPass.getText().toString().length() < 8) {
                regRepPass.setError(Html.fromHtml("<font color='#FFFFFF'>Min 8 chars</font>"));
                repeatPass = "";
            } else repeatPass = regRepPass.getText().toString();
        }
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
    protected RegisterPresenter getPresenter() { return presenter; }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }
}