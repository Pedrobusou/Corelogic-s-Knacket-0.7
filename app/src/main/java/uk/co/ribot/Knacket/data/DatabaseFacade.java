package uk.co.ribot.Knacket.data;

import android.content.Context;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import uk.co.ribot.Knacket.data.local.DatabaseHelper;
import uk.co.ribot.Knacket.injection.scope.PerApplication;

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


    public List<Ad> getAdList() throws SQLException {
        return helper.getDao(Ad.class).queryForAll();
    }

    public List<Seller> getSellerList() throws SQLException {
        return helper.getDao(Seller.class).queryForAll();
    }

    public List<Booking> getBookingList() throws SQLException {
        return helper.getDao(Booking.class).queryForAll();
    }


    public void saveAd (Ad ad) throws SQLException {
        Ad mAd = new Ad (ad);
        helper.getDao(Ad.class).createOrUpdate(mAd);
    }

    public void saveSeller (Seller seller) throws SQLException {
        Seller mSeller = new Seller(seller);
        helper.getDao(Seller.class).createOrUpdate(mSeller);
    }

    public void saveBooking (Booking booking) throws SQLException {
        Booking mBooking = new Booking (booking);
        helper.getDao(Booking.class).createOrUpdate(mBooking);
    }


    public void clearTable(Class clazz) throws SQLException {
        helper.clearTable(clazz);
    }
}