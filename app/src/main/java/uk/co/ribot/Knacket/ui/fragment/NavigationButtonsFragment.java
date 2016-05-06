package uk.co.ribot.Knacket.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.ui.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NavigationButtonsFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private Intent intent;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_navigation_buttons, container);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btnExplore) void clickExplore(){
        if(!getActivity().getLocalClassName().contains("MainActivity")){
            intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @OnClick(R.id.btnJobs) void clickJobs(){

    }

    @OnClick(R.id.btnAdd) void clickAdd(){

    }

    @OnClick(R.id.btnChat) void clickChat(){

    }

    @OnClick(R.id.btnProfile) void clickProfile(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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