package uk.co.ribot.Knacket.data;

import android.content.Context;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import uk.co.ribot.Knacket.data.local.DatabaseHelper;
import uk.co.ribot.Knacket.data.local.model.TagDatabase;
import uk.co.ribot.Knacket.data.local.model.UserDatabase;
import uk.co.ribot.Knacket.data.local.model.UserProfileDatabase;
import uk.co.ribot.Knacket.injection.scope.PerApplication;

import uk.co.ribot.Knacket.data.local.model.AdDatabase;
import uk.co.ribot.Knacket.data.model.Ad;
import uk.co.ribot.Knacket.data.model.Seller;
import uk.co.ribot.Knacket.data.model.Booking;
import rx.Observable;

@PerApplication
public class DatabaseFacade {

    private final DatabaseHelper helper;
    @Inject public DatabaseFacade(Context context) {
        helper = new DatabaseHelper(context);
    }

    //TODO remember about reactive model - return observables !

    public Observable<Ad> getAds() throws SQLException {
        return Observable.from(helper.getDao(Ad.class).queryForAll());
    }

    public Observable<Seller> getSellers() throws SQLException {
        return Observable.from(helper.getDao(Seller.class).queryForAll());
    }

    public Observable<Booking> getBookings() throws SQLException {
        return Observable.from(helper.getDao(Booking.class).queryForAll());
    }


    public List<AdDatabase> getAdList() throws SQLException {
        return helper.getDao(AdDatabase.class).queryForAll();
    }

    public List<Seller> getSellerList() throws SQLException {
        return helper.getDao(Seller.class).queryForAll();
    }

    public List<Booking> getBookingList() throws SQLException {
        return helper.getDao(Booking.class).queryForAll();
    }


    public void saveAd(AdDatabase ad) throws SQLException {
        helper.getDao(AdDatabase.class).createOrUpdate(ad);
    }

    public void saveTagDatabase(TagDatabase ad) throws SQLException {
        helper.getDao(TagDatabase.class).createOrUpdate(ad);
    }

    public void saveUserProfileDatabase(UserProfileDatabase ad) throws SQLException {
        helper.getDao(UserProfileDatabase.class).createOrUpdate(ad);
    }

    public void saveUserDatabase(UserDatabase ad) throws SQLException {
        helper.getDao(UserDatabase.class).createOrUpdate(ad);
    }

    public void saveSeller(Seller seller) throws SQLException {
        helper.getDao(Seller.class).createOrUpdate(seller);
    }

    public void saveBooking(Booking booking) throws SQLException {
        helper.getDao(Booking.class).createOrUpdate(booking);
    }


    public void clearTable(Class clazz) throws SQLException {
        helper.clearTable(clazz);
    }

    public void updateAd(List<Ad> ads, int id) throws SQLException {
        Dao<Ad, ?> dao = helper.getDao(Ad.class);
        List<Ad> currentAds = dao.queryForAll();

        List<Ad> adsToBeDeleted = new ArrayList<>();
        List<Ad> adsToBeAdded = new ArrayList<>();

        for (Ad currentAd : currentAds)
            if (!ads.contains(currentAd))
                adsToBeDeleted.add(currentAd);


        for (Ad ad : ads) {
            if (!currentAds.contains(ad))
                if (!(Integer.parseInt(ad.getId()) == id)) adsToBeAdded.add(ad);
        }

        dao.delete(adsToBeDeleted);
        for (Ad ad : adsToBeAdded) dao.create(ad);
    }
}