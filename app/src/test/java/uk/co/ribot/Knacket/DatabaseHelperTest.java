package uk.co.ribot.Knacket;

import android.database.Cursor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import rx.observers.TestSubscriber;
import uk.co.ribot.Knacket.data.local.DatabaseHelper;
import uk.co.ribot.Knacket.data.local.Db;
import uk.co.ribot.Knacket.data.local.DbOpenHelper;
import uk.co.ribot.Knacket.data.model.Ad;
import uk.co.ribot.Knacket.test.common.TestDataFactory;
import uk.co.ribot.Knacket.util.DefaultConfig;

import static junit.framework.Assert.assertEquals;

/**
 * Unit tests integration with a SQLite Database using Robolectric
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK)
public class DatabaseHelperTest {

    private final DatabaseHelper mDatabaseHelper =
            new DatabaseHelper(new DbOpenHelper(RuntimeEnvironment.application));

    @Before
    public void setUp() {
        mDatabaseHelper.clearTables().subscribe();
    }

    @Test
    public void setRibots() {
        Ad ad1 = TestDataFactory.makeBuyer();
        Ad ad2 = TestDataFactory.makeBuyer();
        List<Ad> ads = Arrays.asList(ad1, ad2);

        TestSubscriber<Ad> result = new TestSubscriber<>();
        mDatabaseHelper.setBuyers(ads).subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(ads);

        Cursor cursor = mDatabaseHelper.getBriteDb()
                .query("SELECT * FROM " + Db.BuyerTable.TABLE_NAME);
        assertEquals(2, cursor.getCount());
        for (Ad ad : ads) {
            cursor.moveToNext();
            assertEquals(ad, Db.BuyerTable.parseCursor(cursor));
        }
    }

    @Test
    public void getRibots() {
        Ad ad1 = TestDataFactory.makeBuyer();
        Ad ad2 = TestDataFactory.makeBuyer();
        List<Ad> ads = Arrays.asList(ad1, ad2);

        mDatabaseHelper.setBuyers(ads).subscribe();

        TestSubscriber<List<Ad>> result = new TestSubscriber<>();
        mDatabaseHelper.getBuyers().subscribe(result);
        result.assertNoErrors();
        result.assertValue(ads);
    }
}