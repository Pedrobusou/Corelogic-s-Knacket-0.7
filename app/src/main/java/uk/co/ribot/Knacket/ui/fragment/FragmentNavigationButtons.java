package uk.co.ribot.Knacket.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.Bind;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.data.PreferencesManager;
import uk.co.ribot.Knacket.ui.main.LoginRegister;
import uk.co.ribot.Knacket.ui.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.Knacket.ui.main.MyJobs;
import uk.co.ribot.Knacket.ui.main.MyProfile;
import uk.co.ribot.Knacket.ui.main.NewAd;

public class FragmentNavigationButtons extends Fragment {
    @Bind(R.id.llAll) LinearLayout fragment;

    private Intent intent;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_buttons, container);
        ButterKnife.bind(this, view);


        PreferencesManager preferences = new PreferencesManager(getContext());
        token = preferences.getToken();

        /*if(TextUtils.isEmpty(token))
            fragment.setBackgroundColor(Color.parseColor("#BABABA"));
        else
            Toast.makeText(getContext(), "there's token", Toast.LENGTH_SHORT).show();*/

        return view;
    }

    @OnClick(R.id.btnExplore) void clickExplore(){
        /*if(TextUtils.isEmpty(token)){
            Intent intent = new Intent(getContext(), LoginRegister.class);
            startActivity(intent);
        }
        else */if(!getActivity().getLocalClassName().contains("MainActivity")){
            intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @OnClick(R.id.btnJobs) void clickJobs(){
        /*if(TextUtils.isEmpty(token)){
            Intent intent = new Intent(getContext(), LoginRegister.class);
            startActivity(intent);
        }
        else */if(!getActivity().getLocalClassName().contains("MyJobs")){
            intent = new Intent(getContext(), MyJobs.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @OnClick(R.id.btnAdd) void clickNewAd(){
        /*if(TextUtils.isEmpty(token)){
            Intent intent = new Intent(getContext(), LoginRegister.class);
            startActivity(intent);
        }
        else */if(!getActivity().getLocalClassName().contains("NewAd")){
            intent = new Intent(getContext(), NewAd.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @OnClick(R.id.btnChat) void clickChat(){
        Toast.makeText(getContext(), "Work in progress", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnProfile) void clickProfile(){
        /*if(TextUtils.isEmpty(token)){
            Intent intent = new Intent(getContext(), LoginRegister.class);
            startActivity(intent);
        }
        else */if(!getActivity().getLocalClassName().contains("MyProfile")){
            intent = new Intent(getContext(), MyProfile.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}