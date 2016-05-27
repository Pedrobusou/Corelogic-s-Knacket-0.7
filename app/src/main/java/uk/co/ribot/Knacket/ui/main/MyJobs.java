package uk.co.ribot.Knacket.ui.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.ui.base.BaseActivity;
import uk.co.ribot.Knacket.ui.fragment.ListBookings;
import uk.co.ribot.Knacket.ui.fragment.FragmentNavigationButtons;

public class MyJobs extends BaseActivity implements FragmentNavigationButtons.OnFragmentInteractionListener{
    //Simple three-tabbed activity
    //Doesn't get data from server yet
    @Bind(R.id.toolbar_title) TextView toolbar_title;
    @Bind(R.id.container) ViewPager mViewPager;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jobs);
        ButterKnife.bind(this);

        setUpTabs();
    }

    private void setUpTabs(){
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three primary sections of the activity.
        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        toolbar_title.setText(R.string.title_activity_my_jobs);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    public class TabsAdapter extends FragmentPagerAdapter {
        final String[] tabTitles = new String[] {"Bookings", "Requests", "Historic"};

        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new ListBookings();
                case 1:
                    return new ListBookings();
                case 2:
                    return new ListBookings();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}