package uk.co.ribot.Knacket.ui.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.data.model.Seller;
import uk.co.ribot.Knacket.ui.main.SellerProfile;

public class SellersAdapter extends RecyclerView.Adapter<SellersAdapter.SellerViewHolder> {
    @Bind(R.id.rbSellerRating) RatingBar ratingBar;
    private List<Seller> mSeller;

    @Inject
    public SellersAdapter() {
        mSeller = new ArrayList<>();
    }

    public void setBuyers(List<Seller> sellers) {
        mSeller = sellers;
    }

    @Override
    public SellerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seller, parent, false);
        ButterKnife.bind(this, itemView);

        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#FACC2E"), PorterDuff.Mode.SRC_ATOP);

        return new SellerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SellerViewHolder holder, int position) {
        Seller seller = mSeller.get(position);

        holder.touchAreaS.setTag(seller.getId());

        //holder.ivProfilePicS.setImageBitmap(seller.setProfilePic);
        holder.tvSellerName.setText(seller.getName());
        holder.tvSellerDesc.setText(seller.getDescription());
        holder.rbSellerRating.setProgress(seller.getRating());
        holder.tvSellerPrice.setText(seller.getPrice());
        holder.touchAreaS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SellerProfile.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSeller.size();
    }

    class SellerViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivProfilePicS) CircleImageView ivProfilePicS;
        @Bind(R.id.tvSellerName) TextView tvSellerName;
        @Bind(R.id.tvSellerDesc) TextView tvSellerDesc;
        @Bind(R.id.rbSellerRating) RatingBar rbSellerRating;
        @Bind(R.id.tvSellerPrice) TextView tvSellerPrice;
        @Bind(R.id.touchAreaS) LinearLayout touchAreaS;

        public SellerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}