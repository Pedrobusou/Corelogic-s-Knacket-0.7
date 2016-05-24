package uk.co.ribot.Knacket.ui.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.http.Url;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.data.local.model.AdDatabase;
import uk.co.ribot.Knacket.ui.main.AdInfo;

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.BuyerViewHolder> {
    private List<AdDatabase> mAd;

    @Inject
    public AdAdapter() {
        mAd = new ArrayList<>();
    }

    public void setBuyers(List<AdDatabase> ads) {
        mAd = ads;
    }

    @Override
    public BuyerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ad, parent, false);
        return new BuyerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BuyerViewHolder holder, int position) {
        AdDatabase ad = mAd.get(position);
        holder.touchArea.setTag(ad.getId());

        /*if(!TextUtils.isEmpty(ad.getUserProfile().getPicture_url())) {  GET PROFILE PIC, PROBLEMS WITH URL
            URL url = null;
            try {
                url = new URL(ad.getUserProfile().getPicture_url());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            Bitmap image = null;
            try {
                image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                holder.ivProfilePic.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        holder.tvBuyerName.setText(ad.getUser().getName());
        holder.tvAdCategory.setText(ad.getTag().getName());
        holder.tvAdDate.setText(ad.getTime());
        holder.tvAdDesc.setText(ad.getText());

        if(!TextUtils.isEmpty(ad.getPrice()))
            holder.tvAdPrice.setText(ad.getPrice());

        if(!TextUtils.isEmpty(ad.getUser().getRating()))
            holder.rbAdRating.setRating(Float.parseFloat(ad.getUser().getRating()));

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
        @Bind(R.id.ivProfilePic) CircleImageView ivProfilePic;
        @Bind(R.id.tvBuyerName) TextView tvBuyerName;
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