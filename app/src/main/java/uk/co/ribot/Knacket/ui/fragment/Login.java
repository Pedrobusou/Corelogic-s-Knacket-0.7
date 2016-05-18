package uk.co.ribot.Knacket.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import uk.co.ribot.Knacket.R;

public class Login extends Fragment {
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
        else
            Toast.makeText(getContext(), "Perfect, Log in", Toast.LENGTH_SHORT).show();
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that activity.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}