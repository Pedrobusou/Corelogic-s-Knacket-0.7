package uk.co.ribot.Knacket.data;

import android.content.Context;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import timber.log.Timber;
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
        Dao<AdDatabase, String> dao = helper.getDao(AdDatabase.class);

        List<AdDatabase> query = dao.queryBuilder().where().eq(AdDatabase.COLUMN_SERVER_ID, ad.getServerId()).query();
        if (query.size() == 0) {
            dao.create(ad); //create group only if there is no one with the same ServerId
        }
    }

    public void saveTagDatabase(TagDatabase tag) throws SQLException {
        Dao<TagDatabase, String> dao = helper.getDao(TagDatabase.class);

        List<TagDatabase> query = dao.queryBuilder().where().eq(TagDatabase.COLUMN_SERVER_ID, tag.getServerId()).query();
        if (query.size() == 0) {
            dao.create(tag); //create group only if there is no one with the same ServerId
        }
    }

    public void saveUserProfileDatabase(UserProfileDatabase userP) throws SQLException {
        Dao<UserProfileDatabase, String> dao = helper.getDao(UserProfileDatabase.class);

        List<UserProfileDatabase> query = dao.queryBuilder().where().eq(UserProfileDatabase.COLUMN_SERVER_ID, userP.getServerId()).query();
        if (query.size() == 0) {
            dao.create(userP); //create group only if there is no one with the same ServerId
        }
    }

    public void saveUserDatabase(UserDatabase userDB) throws SQLException {
        Dao<UserDatabase, String> dao = helper.getDao(UserDatabase.class);

        List<UserDatabase> query = dao.queryBuilder().where().eq(UserDatabase.COLUMN_SERVER_ID, userDB.getServerId()).query();
        if (query.size() == 0) {
            dao.create(userDB); //create group only if there is no one with the same ServerId
        }
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

    public TagDatabase getTagByServerId(String serverId) throws SQLException{
        Dao<TagDatabase,String> dao =helper.getDao(TagDatabase.class);
        return dao.queryBuilder().where().eq(TagDatabase.COLUMN_SERVER_ID,serverId).queryForFirst();
    }
}