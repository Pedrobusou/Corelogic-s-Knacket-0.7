package uk.co.ribot.Knacket.ui.main;

import android.content.Intent;
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
import uk.co.ribot.Knacket.data.model.Buyer;

public class BuyersAdapter extends RecyclerView.Adapter<BuyersAdapter.BuyerViewHolder> {
    private List<Buyer> mbuyer;

    @Inject
    public BuyersAdapter() {
        mbuyer = new ArrayList<>();
    }

    public void setBuyers(List<Buyer> buyers) {
        mbuyer = buyers;
    }

    @Override
    public BuyerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buyer, parent, false);
        return new BuyerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BuyerViewHolder holder, int position) {
        Buyer buyer = mbuyer.get(position);

        holder.touchArea.setTag(buyer.getId());

        //holder.ivProfilePic.setImageBitmap(buyer.setProfilePic);
        holder.tvBuyerName.setText(buyer.getName());
        holder.tvBuyerCategory.setText(buyer.getCategory());
        holder.tvBuyerDate.setText(buyer.getDate());
        holder.tvBuyerDesc.setText(buyer.getDescription());
        holder.rbBuyerRating.setProgress(buyer.getRating());
        holder.tvBuyerPrice.setText(buyer.getPrice());

        holder.touchArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SellerProfile.class); //TO BUYER PROFILE
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mbuyer.size();
    }

    class BuyerViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivProfilePic) CircleImageView ivProfilePic;
        @Bind(R.id.tvBuyerName) TextView tvBuyerName;
        @Bind(R.id.tvBuyerCategory) TextView tvBuyerCategory;
        @Bind(R.id.tvBuyerDate) TextView tvBuyerDate;
        @Bind(R.id.tvBuyerDesc) TextView tvBuyerDesc;
        @Bind(R.id.rbBuyerRating) RatingBar rbBuyerRating;
        @Bind(R.id.tvBuyerPrice) TextView tvBuyerPrice;
        @Bind(R.id.touchArea) LinearLayout touchArea;

        public BuyerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}