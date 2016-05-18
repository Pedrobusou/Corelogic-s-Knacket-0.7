package uk.co.ribot.Knacket.ui.fragment.base;

import android.os.Bundle;
import uk.co.ribot.Knacket.presenter.fragment.BasePresenter;

public abstract class BasePresenterFragment<PRESENTER extends BasePresenter> extends BaseFragment {

    @Override
    protected void onAttachToContext() {
        super.onAttachToContext();
        getPresenter().onAttach(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().onCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        getPresenter().onDetach();
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getPresenter().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    protected abstract PRESENTER getPresenter();
}