package uk.co.ribot.Knacket.ui.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.ui.fragment.NavigationButtonsFragment;
import uk.co.ribot.Knacket.ui.fragment.ProfilePicFragment;
import uk.co.ribot.Knacket.ui.fragment.ProfileVidFragment;

public class BuyerProfile extends AppCompatActivity implements NavigationButtonsFragment.OnFragmentInteractionListener, ProfilePicFragment.OnFragmentInteractionListener, ProfileVidFragment.OnFragmentInteractionListener{
    @Bind(R.id.toolbar) Toolbar toolbar;

    ViewPager mViewPager;
    CirclePageIndicator pageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_profile);
        ButterKnife.bind(this);

        setToolbar();
        setUpTabs();
    }

    public void setToolbar(){
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

    public void setUpTabs(){
        mViewPager = (ViewPager) findViewById(R.id.container);
        pageIndicator = (CirclePageIndicator) findViewById(R.id.pageIndicator);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(), this);

        mViewPager.setAdapter(tabsAdapter);
        pageIndicator.setViewPager(mViewPager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    public class TabsAdapter extends FragmentPagerAdapter {
        Context context;

        public TabsAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ProfilePicFragment();
                case 1:
                    return new ProfileVidFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}