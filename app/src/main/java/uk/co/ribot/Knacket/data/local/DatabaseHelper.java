package uk.co.ribot.Knacket.data.local;

import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import uk.co.ribot.Knacket.data.model.Ad;

@Singleton
public class DatabaseHelper {

    private final BriteDatabase mDb;

    @Inject
    public DatabaseHelper(DbOpenHelper dbOpenHelper) {
        mDb = SqlBrite.create().wrapDatabaseHelper(dbOpenHelper);
    }

    public BriteDatabase getBriteDb() {
        return mDb;
    }

    /**
     * Remove all the data from all the tables in the database.
     */
    public Observable<Void> clearTables() {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    Cursor cursor = mDb.query("SELECT name FROM sqlite_master WHERE type='table'");
                    while (cursor.moveToNext()) {
                        mDb.delete(cursor.getString(cursor.getColumnIndex("name")), null);
                    }
                    cursor.close();
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<Ad> setBuyers(final Collection<Ad> newAds) {
        return Observable.create(new Observable.OnSubscribe<Ad>() {
            @Override
            public void call(Subscriber<? super Ad> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Db.BuyerTable.TABLE_NAME, null);
                    for (Ad ad : newAds) { //INSERT DATA IN DATABASE
                        //long result = mDb.insert(Db.BuyerTable.TABLE_NAME, Db.BuyerTable.toContentValues(ad), SQLiteDatabase.CONFLICT_REPLACE);
                        //if (result >= 0) subscriber.onNext(ad);
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<List<Ad>> getBuyers() {
        return mDb.createQuery(Db.BuyerTable.TABLE_NAME,
                "SELECT * FROM " + Db.BuyerTable.TABLE_NAME)
                .mapToList(new Func1<Cursor, Ad>() {
                    @Override
                    public Ad call(Cursor cursor) {
                        return new Ad(Db.BuyerTable.parseCursor(cursor));
                    }
                });
    }
}