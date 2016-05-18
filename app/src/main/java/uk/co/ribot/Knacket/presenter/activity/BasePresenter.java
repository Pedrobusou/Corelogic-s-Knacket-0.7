package uk.co.ribot.Knacket.presenter.activity;

import android.os.Bundle;
import uk.co.ribot.Knacket.ui.base.BasePresenterActivity;

public class BasePresenter<ACTIVITY extends BasePresenterActivity> {
    private ACTIVITY activity;

    public BasePresenter() {}

    public void onCreate(Bundle savedInstanceState) {}

    public void onAttach(ACTIVITY activity) {
        this.activity = activity;
    }

    public void onDetach() {
        activity = null;
    }

    public void onSaveInstanceState(Bundle outState) {}

    protected ACTIVITY getActivity() {
        return activity;
    }
}