package uk.co.ribot.Knacket.presenter.fragment;

import android.os.Bundle;

import de.toliart.babbler.ui.fragment.base.BasePresenterFragment;


public class BasePresenter<FRAGMENT extends BasePresenterFragment> {
    private FRAGMENT fragment;

    public void onCreate(Bundle savedInstanceState) {
    }

    public void onAttach(FRAGMENT fragment) {
        this.fragment = fragment;
    }

    public void onDetach() {
        fragment = null;
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    protected FRAGMENT getFragment() {
        return fragment;
    }
}
