package uk.co.ribot.Knacket.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.data.model.Seller;
import uk.co.ribot.Knacket.ui.adapter.SellersAdapter;

public class ListSellers extends Fragment {
    public ListSellers() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);

        SellersAdapter adapter = new SellersAdapter();
        adapter.setBuyers(new Seller().add6Seller());
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }
}