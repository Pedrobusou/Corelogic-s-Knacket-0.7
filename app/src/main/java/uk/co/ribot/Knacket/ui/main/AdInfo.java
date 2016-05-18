package uk.co.ribot.Knacket.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.ui.fragment.FragmentNavigationButtons;

public class AdInfo extends AppCompatActivity implements FragmentNavigationButtons.OnFragmentInteractionListener{
    @Bind(R.id.toolbar_title) TextView toolbar_title;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_info);
        ButterKnife.bind(this);

        toolbar_title.setText(R.string.title_activity_ad_info);
        setUpContent();
    }

    @OnClick(R.id.rlBuyerProfile) void toBuyerProfile(){
        Intent intent = new Intent(this, BuyerProfile.class);
        startActivity(intent);
    }

    private void setUpContent(){
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
}