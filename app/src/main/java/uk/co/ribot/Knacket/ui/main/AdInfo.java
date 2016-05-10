package uk.co.ribot.Knacket.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.ui.fragment.FragmentNavigationButtons;

public class AdInfo extends AppCompatActivity implements FragmentNavigationButtons.OnFragmentInteractionListener{
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        ButterKnife.bind(this);

        setUpContent();
    }

    @OnClick(R.id.llBuyerProfile) void toBuyerProfile(){
        Intent intent = new Intent(this, BuyerProfile.class);
        startActivity(intent);
    }

    public void setUpContent(){
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