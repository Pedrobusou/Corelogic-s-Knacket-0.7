package uk.co.ribot.Knacket.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.sql.SQLException;
import javax.inject.Inject;

import timber.log.Timber;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.event.AdSyncFinishedEvent;
import uk.co.ribot.Knacket.presenter.ListAdsPresenter;
import uk.co.ribot.Knacket.ui.adapter.AdAdapter;
import uk.co.ribot.Knacket.ui.fragment.base.BasePresenterFragment;

public class ListAds extends BasePresenterFragment<ListAdsPresenter> {
    @Inject ListAdsPresenter presenter;
    private AdAdapter adapter = new AdAdapter();

    public ListAds() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Creates recycleView and fills it with the server data at AdInfo, using the AdItem template

        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onContactSyncFinished(AdSyncFinishedEvent event) {
        try {
            Timber.i("Size: " + presenter.getDataManager().db().getAdList().size());
            adapter.setBuyers(presenter.getDataManager().db().getAdList()); //Load adapter with data from server
            adapter.notifyDataSetChanged(); //Refresh adapter
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected ListAdsPresenter getPresenter() { return presenter; }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }
}