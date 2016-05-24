package uk.co.ribot.Knacket.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.data.PreferencesManager;
import uk.co.ribot.Knacket.presenter.activity.MainActivityPresenter;
import uk.co.ribot.Knacket.service.AdService;
import uk.co.ribot.Knacket.ui.adapter.AdAdapter;
import uk.co.ribot.Knacket.ui.base.BasePresenterActivity;
import uk.co.ribot.Knacket.ui.fragment.ListAds;
import uk.co.ribot.Knacket.ui.fragment.FragmentFilter;
import uk.co.ribot.Knacket.ui.fragment.FragmentNavigationButtons;
import uk.co.ribot.Knacket.ui.fragment.ListSellers;

public class MainActivity extends BasePresenterActivity<MainActivityPresenter>
        implements FragmentNavigationButtons.OnFragmentInteractionListener, FragmentFilter.OnFragmentInteractionListener {
    @Inject AdAdapter mAdAdapter;

    @Bind(R.id.container) ViewPager mViewPager;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Bind(R.id.toolbar_title) TextView toolbar_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getActivityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        PreferencesManager preferences = new PreferencesManager(this);
        String token = preferences.getToken();
        if(!TextUtils.isEmpty(token)){
            startService(new Intent(this, AdService.class));
            Toast.makeText(this, "service ok", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "service not ok", Toast.LENGTH_SHORT).show(); //WHY I CANT COMMENT THIS?

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        setUpTabs();
    }

    @Override
    protected void onResume() {
        PreferencesManager preferences = new PreferencesManager(this);
        String token = preferences.getToken();
        if(!TextUtils.isEmpty(token)){
            startService(new Intent(this, AdService.class));
            Toast.makeText(this, "service ok", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "service not ok", Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    protected MainActivityPresenter getPresenter() {
        return null;
    }

    private void setUpTabs(){
        setSupportActionBar(toolbar);

        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(), this);

        mViewPager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        toolbar_title.setText("Ads");
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                toolbar_title.setText(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    @OnClick(R.id.btnDone) void doneClicked(){
        drawer.closeDrawer(GravityCompat.END);
        //REFRESH LIST
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.END)) drawer.closeDrawer(GravityCompat.END);
        else moveTaskToBack(true);
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds FILTER ICON, which pops up FILTER MENU (navigationDrawer).
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        drawer.openDrawer(GravityCompat.END);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    public class TabsAdapter extends FragmentPagerAdapter {
        final String[] tabTitles = new String[] { "Ads", "Sellers"};
        final Context context;

        public TabsAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new ListAds();
                case 1:
                    return new ListSellers();
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