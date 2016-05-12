package uk.co.ribot.Knacket.ui.fragment;

/**
 * Created by pedroramos on 05.05.16.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.data.model.Booking;
import uk.co.ribot.Knacket.data.model.Seller;
import uk.co.ribot.Knacket.ui.adapter.BookingsAdapter;
import uk.co.ribot.Knacket.ui.adapter.SellersAdapter;

public class ListBookings extends Fragment {

    public ListBookings() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);

        BookingsAdapter adapter = new BookingsAdapter();
        adapter.setBookings(new Booking().add6Bookings());
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }
}