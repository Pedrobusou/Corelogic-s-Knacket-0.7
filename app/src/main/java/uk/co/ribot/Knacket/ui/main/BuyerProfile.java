package uk.co.ribot.Knacket.ui.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.List;
import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.ribot.Knacket.R;
import uk.co.ribot.Knacket.data.local.model.AdDatabase;
import uk.co.ribot.Knacket.presenter.activity.BuyerProfilePresenter;
import uk.co.ribot.Knacket.ui.base.BasePresenterActivity;
import uk.co.ribot.Knacket.ui.fragment.FragmentNavigationButtons;
import uk.co.ribot.Knacket.ui.fragment.FragmentProfilePic;
import uk.co.ribot.Knacket.ui.fragment.FragmentProfileVid;
import uk.co.ribot.Knacket.ui.fragment.FragmentThingsICanDo;

public class BuyerProfile extends BasePresenterActivity<BuyerProfilePresenter> implements FragmentNavigationButtons.OnFragmentInteractionListener, FragmentProfilePic.OnFragmentInteractionListener, FragmentProfileVid.OnFragmentInteractionListener, FragmentThingsICanDo.OnFragmentInteractionListener{
    @Inject BuyerProfilePresenter presenter;
    @Bind(R.id.pageIndicator) CirclePageIndicator pageIndicator;
    @Bind(R.id.containerHeader) ViewPager headerView;
    @Bind(R.id.containerFooter) ViewPager footerView;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.toolbar_title) TextView toolbar_title;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Bind(R.id.etLocation) EditText location;
    @Bind(R.id.etRating) EditText rating;
    @Bind(R.id.tvProfileDescription) TextView description;

    private AdDatabase mAd; //OrmLite class

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_profile);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        try {
            List<AdDatabase> mAdList = presenter.getDataManager().db().getAdList();
            //Gets all the ads from server using presenter and search for the one we were previously reading its info
            //then fill the Ui with its info from server

            for(AdDatabase ad : mAdList)
                if(ad.getId() == extras.getLong("adId")) mAd = ad;

            location.setText(mAd.getUserProfile().getLocation());
            rating.setText(mAd.getUser().getRating());
            description.setText(mAd.getUserProfile().getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }

        toolbar_title.setText(mAd.getUser().getName()); //Use userName as toolbarTitle
        setUpContent();
    }

    @Override
    protected BuyerProfilePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    private void setUpContent(){ //Sets toolbar and tabs for header and footer
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TabsAdapterHeader tabsAdapterHeader = new TabsAdapterHeader(getSupportFragmentManager());

        headerView.setAdapter(tabsAdapterHeader);
        pageIndicator.setViewPager(headerView);

        TabsAdapterFooter tabsAdapterFooter = new TabsAdapterFooter(getSupportFragmentManager());

        footerView.setAdapter(tabsAdapterFooter);
        tabLayout.setupWithViewPager(footerView);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    public class TabsAdapterHeader extends FragmentPagerAdapter {

        public TabsAdapterHeader(FragmentManager fm) {
            super(fm);
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
        final String[] tabTitles = new String[] { "My ads", "Reviews"};

        public TabsAdapterFooter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentThingsICanDo();
                case 1:
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