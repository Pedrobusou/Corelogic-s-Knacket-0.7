package uk.co.ribot.Knacket.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

import uk.co.ribot.Knacket.data.local.model.AdDatabase;
import uk.co.ribot.Knacket.data.local.model.TagDatabase;
import uk.co.ribot.Knacket.data.local.model.UserDatabase;
import uk.co.ribot.Knacket.data.local.model.UserProfileDatabase;
import uk.co.ribot.Knacket.data.model.Ad;
import uk.co.ribot.Knacket.data.model.Booking;
import uk.co.ribot.Knacket.data.model.Seller;
import timber.log.Timber;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper{
    private static final String DATABASE_NAME = "knacket";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Timber.d("Creating database.");
        createTables(connectionSource);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Timber.d("Upgrading database from version " + oldVersion + " to " + newVersion + ".");
    }

    public void clearTable(Class clazz) throws SQLException {
        TableUtils.clearTable(getConnectionSource(), clazz);
    }

    private void createTables(ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, AdDatabase.class);
            TableUtils.createTable(connectionSource, TagDatabase.class);
            TableUtils.createTable(connectionSource, UserDatabase.class);
            TableUtils.createTable(connectionSource, UserProfileDatabase.class);
         //   TableUtils.createTable(connectionSource, Booking.class);
         //   TableUtils.createTable(connectionSource, Seller.class);
        } catch (SQLException e) {
            //Timber.e("Fatal error, cannot create database!", e);
        }
    }
}