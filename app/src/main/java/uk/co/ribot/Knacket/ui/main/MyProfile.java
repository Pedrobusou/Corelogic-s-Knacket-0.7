package uk.co.ribot.Knacket.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.ui.fragment.FragmentNavigationButtons;
import uk.co.ribot.Knacket.ui.fragment.FragmentProfilePic;
import uk.co.ribot.Knacket.ui.fragment.FragmentProfileVid;
import uk.co.ribot.Knacket.ui.fragment.FragmentThingsICanDo;

public class MyProfile extends AppCompatActivity implements FragmentNavigationButtons.OnFragmentInteractionListener, FragmentProfilePic.OnFragmentInteractionListener, FragmentProfileVid.OnFragmentInteractionListener, FragmentThingsICanDo.OnFragmentInteractionListener{
    @Bind(R.id.pageIndicator) CirclePageIndicator pageIndicator;
    @Bind(R.id.containerHeader) ViewPager headerView;
    @Bind(R.id.containerFooter) ViewPager footerView;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.toolbar_title) TextView toolbar_title;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        ButterKnife.bind(this);

        toolbar_title.setText(R.string.title_activity_my_profile);
        setUpContent();
    }

    public void setUpContent(){
        setSupportActionBar(toolbar);

        TabsAdapterHeader tabsAdapterHeader = new TabsAdapterHeader(getSupportFragmentManager(), this);

        headerView.setAdapter(tabsAdapterHeader);
        pageIndicator.setViewPager(headerView);

        TabsAdapterFooter tabsAdapterFooter = new TabsAdapterFooter(getSupportFragmentManager(), this);

        footerView.setAdapter(tabsAdapterFooter);
        tabLayout.setupWithViewPager(footerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds FILTER ICON, which pops up FILTER MENU (navigationDrawer).
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    public class TabsAdapterHeader extends FragmentPagerAdapter {
        Context context;

        public TabsAdapterHeader(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentProfilePic();
                case 1:
                    return new FragmentProfileVid();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public class TabsAdapterFooter extends FragmentPagerAdapter {
        String[] tabTitles = new String[] { "My ads", "Reviews", "ThingsICanDo"};
        Context context;

        public TabsAdapterFooter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentThingsICanDo();
                case 1:
                    return new FragmentThingsICanDo();
                case 2:
                    return new FragmentThingsICanDo();
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
