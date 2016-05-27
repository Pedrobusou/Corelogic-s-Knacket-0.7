package uk.co.ribot.Knacket.ui.main;

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

    private PreferencesManager preferences;
    private String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getActivityComponent().inject(this);  commented due to problems
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        preferences = new PreferencesManager(this); //Save user token on sharedPrefs. If there isn't, app will understand that user hasn't login
        token = preferences.getToken();

        if(!TextUtils.isEmpty(token)){
            startService(new Intent(this, AdService.class)); //If there's token/user is logged, it app will load info from server
            Toast.makeText(this, "service ok", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "service not ok", Toast.LENGTH_SHORT).show(); //WHY I CANT COMMENT THIS?

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); //prevent keyboard from default showing
        setUpTabs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferences = new PreferencesManager(this);
        token = preferences.getToken();
        if(!TextUtils.isEmpty(token)){
            startService(new Intent(this, AdService.class));
            Toast.makeText(this, "service ok", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "service not ok", Toast.LENGTH_SHORT).show(); //WHY I CANT COMMENT THIS?

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        setUpTabs();
    }

    @Override
    protected MainActivityPresenter getPresenter() {
        return null;
    }

    private void setUpTabs(){
        setSupportActionBar(toolbar);

        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        toolbar_title.setText("Ads");
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { //Changes page title depending on tab
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

    @OnClick(R.id.btnDone) void doneClicked(){ //Close navDrawer, must refresh list
        drawer.closeDrawer(GravityCompat.END);
    }

    @Override
    public void onBackPressed() { //Will close navDrawer if its open. If not, will work normally
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
    public boolean onOptionsItemSelected(MenuItem item) { //Shows navDrawer when clicking on the menu
        drawer.openDrawer(GravityCompat.END);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    public class TabsAdapter extends FragmentPagerAdapter { //Load lists on the tabs
        final String[] tabTitles = new String[] { "Ads", "Sellers"};

        public TabsAdapter(FragmentManager fm) {
            super(fm);
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