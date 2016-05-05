package uk.co.ribot.Knacket;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import uk.co.ribot.Knacket.data.DataManager;
import uk.co.ribot.Knacket.data.model.Buyer;
import uk.co.ribot.Knacket.test.common.TestDataFactory;
import uk.co.ribot.Knacket.ui.main.MainMvpView;
import uk.co.ribot.Knacket.ui.main.MainPresenter;
import uk.co.ribot.Knacket.util.RxSchedulersOverrideRule;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock MainMvpView mMockMainMvpView;
    @Mock DataManager mMockDataManager;
    private MainPresenter mMainPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        mMainPresenter = new MainPresenter(mMockDataManager);
        mMainPresenter.attachView(mMockMainMvpView);
    }

    @After
    public void tearDown() {
        mMainPresenter.detachView();
    }

    @Test
    public void loadRibotsReturnsRibots() {
        List<Buyer> buyers = TestDataFactory.makeListBuyers(10);
        doReturn(Observable.just(buyers))
                .when(mMockDataManager)
                .getBuyers();

        mMainPresenter.loadBuyers();
        verify(mMockMainMvpView).showBuyers(buyers);
        verify(mMockMainMvpView, never()).showBuyersEmpty();
        verify(mMockMainMvpView, never()).showError();
    }

    @Test
    public void loadRibotsReturnsEmptyList() {
        doReturn(Observable.just(Collections.emptyList()))
                .when(mMockDataManager)
                .getBuyers();

        mMainPresenter.loadBuyers();
        verify(mMockMainMvpView).showBuyersEmpty();
        verify(mMockMainMvpView, never()).showBuyers(anyListOf(Buyer.class));
        verify(mMockMainMvpView, never()).showError();
    }

    @Test
    public void loadRibotsFails() {
        doReturn(Observable.error(new RuntimeException()))
                .when(mMockDataManager)
                .getBuyers();

        mMainPresenter.loadBuyers();
        verify(mMockMainMvpView).showError();
        verify(mMockMainMvpView, never()).showBuyersEmpty();
        verify(mMockMainMvpView, never()).showBuyers(anyListOf(Buyer.class));
    }
}
