package uk.co.ribot.Knacket.ui.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.ui.fragment.FragmentNavigationButtons;

public class EditProfile extends AppCompatActivity implements FragmentNavigationButtons.OnFragmentInteractionListener {
    @Bind(R.id.toolbar) Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        setUpContent();
    }

    public void setUpContent() {
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

    @Override
    public void onFragmentInteraction(Uri uri) {}
}
