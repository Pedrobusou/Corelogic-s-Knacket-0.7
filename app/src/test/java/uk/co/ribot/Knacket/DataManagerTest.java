package uk.co.ribot.Knacket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.data.local.DatabaseHelper;
import uk.co.ribot.Knacket.data.local.PreferencesHelper;
import uk.co.ribot.Knacket.data.model.Ad;
import uk.co.ribot.Knacket.data.remote.BuyersService;
import uk.co.ribot.Knacket.test.common.TestDataFactory;
import uk.co.ribot.Knacket.util.EventPosterHelper;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This test class performs local unit tests without dependencies on the Android framework
 * For testing methods in the DataManager follow this approach:
 * 1. Stub mock helper classes that your method relies on. e.g. RetrofitServices or DatabaseHelper
 * 2. Test the Observable using TestSubscriber
 * 3. Optionally write a SEPARATE test that verifies that your method is calling the right helper
 * using Mockito.verify()
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Mock DatabaseHelper mMockDatabaseHelper;
    @Mock PreferencesHelper mMockPreferencesHelper;
    @Mock
    BuyersService mMockBuyersService;
    @Mock EventPosterHelper mEventPosterHelper;
    private DataManager mDataManager;

    @Before
    public void setUp() {
        mDataManager = new DataManager(mMockBuyersService, mMockPreferencesHelper,
                mMockDatabaseHelper, mEventPosterHelper);
    }

    @Test
    public void syncBuyersEmitsValues() {
        List<Ad> ads = Arrays.asList(TestDataFactory.makeBuyer(),
                TestDataFactory.makeBuyer());
        stubSyncBuyersHelperCalls(ads);

        TestSubscriber<Ad> result = new TestSubscriber<>();
        mDataManager.syncBuyers().subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(ads);
    }

    @Test
    public void syncBuyersCallsApiAndDatabase() {
        List<Ad> ads = Arrays.asList(TestDataFactory.makeBuyer(),
                TestDataFactory.makeBuyer());
        stubSyncBuyersHelperCalls(ads);

        mDataManager.syncBuyers().subscribe();
        // Verify right calls to helper methods
        verify(mMockBuyersService).getBuyers();
        verify(mMockDatabaseHelper).setBuyers(ads);
    }

    @Test
    public void syncBuyersDoesNotCallDatabaseWhenApiFails() {
        when(mMockBuyersService.getBuyers())
                .thenReturn(Observable.<List<Ad>>error(new RuntimeException()));

        mDataManager.syncBuyers().subscribe(new TestSubscriber<Ad>());
        // Verify right calls to helper methods
        verify(mMockBuyersService).getBuyers();
        verify(mMockDatabaseHelper, never()).setBuyers(anyListOf(Ad.class));
    }

    private void stubSyncBuyersHelperCalls(List<Ad> ads) {
        // Stub calls to the ribot service and database helper.
        when(mMockBuyersService.getBuyers())
                .thenReturn(Observable.just(ads));
        when(mMockDatabaseHelper.setBuyers(ads))
                .thenReturn(Observable.from(ads));
    }
}