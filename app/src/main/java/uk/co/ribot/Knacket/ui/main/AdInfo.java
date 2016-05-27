package uk.co.ribot.Knacket.ui.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.fabric.sdk.android.services.concurrency.AsyncTask;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.ui.fragment.FragmentNavigationButtons;

public class AdInfo extends AppCompatActivity implements FragmentNavigationButtons.OnFragmentInteractionListener{
    @Bind(R.id.toolbar_title) TextView toolbar_title;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.civ) CircleImageView profilePic;
    @Bind(R.id.tvUserName) TextView tvUserName;
    @Bind(R.id.rbSellerRating) RatingBar ratingBar;
    @Bind(R.id.tvAdCategory) TextView adCategory;
    @Bind(R.id.tvAdDescription) TextView adDescription;
    @Bind(R.id.tvAdPrice) TextView adPrice;

    private Bundle extras; //To receive the adInfo from the previous activity, which got it from server
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Fills ui with the adInfo from extras
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_info);
        ButterKnife.bind(this);

        extras = getIntent().getExtras();

        new LoadImage().execute(extras.getString("profilePicURL"));
        tvUserName.setText(extras.getString("userName"));
        ratingBar.setRating(extras.getFloat("rating"));
        adCategory.setText(extras.getString("category"));
        adDescription.setText(extras.getString("description"));
        adPrice.setText(extras.getString("price"));

        //Set the color of the RatingBar's stars
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#FACC2E"), PorterDuff.Mode.SRC_ATOP);

        toolbar_title.setText(R.string.title_activity_ad_info);
        setUpToolbar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        extras.clear();
    }

    @OnClick(R.id.rlBuyerProfile) void toBuyerProfile(){
        Intent intent = new Intent(this, BuyerProfile.class);
        intent.putExtra("adId", extras.getLong("adId")); //Sends user's id to the next activity so it can search for his data
        startActivity(intent);
    }

    private void setUpToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.btnSendRequest) void sendRequest(){
        Intent intent = new Intent(this, Request.class);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        //Loads the bitmap for the user's profile picture in new thread. If there isn't image, default one will be showed
        //Problem, loads same image for every user!!

        @Override
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());
            } catch (IOException e) { e.printStackTrace(); }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap image) {
            if(image != null) profilePic.setImageBitmap(image);
        }
    }
}