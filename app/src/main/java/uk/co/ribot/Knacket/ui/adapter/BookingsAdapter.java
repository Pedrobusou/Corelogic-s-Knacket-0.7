package uk.co.ribot.Knacket.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.data.model.Booking;
import uk.co.ribot.Knacket.ui.main.BookingInfo;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.BookingViewHolder> {
    private List<Booking> mBooking;

    @Inject
    public BookingsAdapter() {
        mBooking = new ArrayList<>();
    }

    public void setBookings(List<Booking> bookings) {
        mBooking = bookings;
    }

    @Override
    public BookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookingViewHolder holder, int position) {
        Booking booking = mBooking.get(position);

        holder.touchAreaS.setTag(booking.getId());

        //holder.ivProfilePicS.setImageBitmap(booking.setProfilePic);
        holder.tvBookingName.setText(booking.getName());
        holder.tvBookingDesc.setText(booking.getDescription());
        holder.touchAreaS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BookingInfo.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBooking.size();
    }

    class BookingViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivProfilePicS) CircleImageView ivProfilePicS;
        @Bind(R.id.tvBookingName) TextView tvBookingName;
        @Bind(R.id.tvBookingDesc) TextView tvBookingDesc;
        @Bind(R.id.touchAreaS) LinearLayout touchAreaS;

        public BookingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}