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
import uk.co.ribot.Knacket.data.model.Buyer;

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

    public Observable<Buyer> setBuyers(final Collection<Buyer> newBuyers) {
        return Observable.create(new Observable.OnSubscribe<Buyer>() {
            @Override
            public void call(Subscriber<? super Buyer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Db.BuyerTable.TABLE_NAME, null);
                    for (Buyer buyer : newBuyers) { //INSERT DATA IN DATABASE
                        //long result = mDb.insert(Db.BuyerTable.TABLE_NAME, Db.BuyerTable.toContentValues(buyer), SQLiteDatabase.CONFLICT_REPLACE);
                        //if (result >= 0) subscriber.onNext(buyer);
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<List<Buyer>> getBuyers() {
        return mDb.createQuery(Db.BuyerTable.TABLE_NAME,
                "SELECT * FROM " + Db.BuyerTable.TABLE_NAME)
                .mapToList(new Func1<Cursor, Buyer>() {
                    @Override
                    public Buyer call(Cursor cursor) {
                        return new Buyer(Db.BuyerTable.parseCursor(cursor));
                    }
                });
    }
}