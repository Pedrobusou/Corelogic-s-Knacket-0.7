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
import uk.co.ribot.Knacket.data.model.Buyer;
import uk.co.ribot.Knacket.data.model.Ribot;
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
        Buyer buyer1 = TestDataFactory.makeBuyer();
        Buyer buyer2 = TestDataFactory.makeBuyer();
        List<Buyer> buyers = Arrays.asList(buyer1, buyer2);

        TestSubscriber<Buyer> result = new TestSubscriber<>();
        mDatabaseHelper.setBuyers(buyers).subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(buyers);

        Cursor cursor = mDatabaseHelper.getBriteDb()
                .query("SELECT * FROM " + Db.BuyerTable.TABLE_NAME);
        assertEquals(2, cursor.getCount());
        for (Buyer buyer : buyers) {
            cursor.moveToNext();
            assertEquals(buyer, Db.BuyerTable.parseCursor(cursor));
        }
    }

    @Test
    public void getRibots() {
        Buyer buyer1 = TestDataFactory.makeBuyer();
        Buyer buyer2 = TestDataFactory.makeBuyer();
        List<Buyer> buyers = Arrays.asList(buyer1, buyer2);

        mDatabaseHelper.setBuyers(buyers).subscribe();

        TestSubscriber<List<Buyer>> result = new TestSubscriber<>();
        mDatabaseHelper.getBuyers().subscribe(result);
        result.assertNoErrors();
        result.assertValue(buyers);
    }
}