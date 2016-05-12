package uk.co.ribot.Knacket.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;

import de.toliart.babbler.presenter.fragment.BasePresenter;
import de.toliart.babbler.ui.fragment.base.BaseFragment;

public abstract class BasePresenterFragment<PRESENTER extends BasePresenter> extends BaseFragment {

    @Override
    protected void onAttachToContext(Context context) {
        super.onAttachToContext(context);
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
