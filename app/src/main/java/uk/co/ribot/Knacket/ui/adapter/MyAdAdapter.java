package uk.co.ribot.Knacket.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.data.model.Ad;
import uk.co.ribot.Knacket.ui.main.AdInfo;

public class MyAdAdapter extends RecyclerView.Adapter<MyAdAdapter.BuyerViewHolder> {
    private List<Ad> mAd;

    @Inject
    public MyAdAdapter() {
        mAd = new ArrayList<>();
    }

    public void setAds(List<Ad> ads) {
        mAd = ads;
    }

    @Override
    public BuyerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ad, parent, false);
        return new BuyerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BuyerViewHolder holder, int position) {
        Ad ad = mAd.get(position);

        holder.touchArea.setTag(ad.getId());

        holder.rlProfilePic.setVisibility(View.GONE);
        holder.tvAdCategory.setText(ad.getCategory());
        holder.tvAdDate.setText(ad.getTime());
        holder.tvAdDesc.setText(ad.getText());
        holder.rbAdRating.setProgress(ad.getRating());
        holder.tvAdPrice.setText(ad.getPrice());

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
        return mAd.size();
    }

    class BuyerViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.rlProfilePic) RelativeLayout rlProfilePic;
        @Bind(R.id.tvAdCategory) TextView tvAdCategory;
        @Bind(R.id.tvAdDate) TextView tvAdDate;
        @Bind(R.id.tvAdDesc) TextView tvAdDesc;
        @Bind(R.id.rbAdRating) RatingBar rbAdRating;
        @Bind(R.id.tvAdPrice) TextView tvAdPrice;
        @Bind(R.id.touchArea) LinearLayout touchArea;

        public BuyerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}