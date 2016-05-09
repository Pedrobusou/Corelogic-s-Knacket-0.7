package uk.co.ribot.Knacket.ui.adapter;

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
import uk.co.ribot.Knacket.data.model.Ad;
import uk.co.ribot.Knacket.ui.main.AdInfo;

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.BuyerViewHolder> {
    private List<Ad> mbuyer;

    @Inject
    public AdAdapter() {
        mbuyer = new ArrayList<>();
    }

    public void setBuyers(List<Ad> ads) {
        mbuyer = ads;
    }

    @Override
    public BuyerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ad, parent, false);
        return new BuyerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BuyerViewHolder holder, int position) {
        Ad ad = mbuyer.get(position);

        holder.touchArea.setTag(ad.getId());

        //holder.ivProfilePic.setImageBitmap(ad.setProfilePic);
        holder.tvBuyerName.setText(ad.getName());
        holder.tvBuyerCategory.setText(ad.getCategory());
        holder.tvBuyerDate.setText(ad.getDate());
        holder.tvBuyerDesc.setText(ad.getDescription());
        holder.rbBuyerRating.setProgress(ad.getRating());
        holder.tvBuyerPrice.setText(ad.getPrice());

        holder.touchArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AdInfo.class);
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