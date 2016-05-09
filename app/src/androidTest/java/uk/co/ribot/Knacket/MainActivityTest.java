package uk.co.ribot.Knacket;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.List;

import rx.Observable;
import uk.co.ribot.Knacket.data.model.Ad;
import uk.co.ribot.Knacket.test.common.TestDataFactory;
import uk.co.ribot.Knacket.test.common.rules.TestComponentRule;
import uk.co.ribot.Knacket.ui.main.MainActivity;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    public final ActivityTestRule<MainActivity> main =
            new ActivityTestRule<MainActivity>(MainActivity.class, false, false) {
                @Override
                protected Intent getActivityIntent() {
                    // Override the default intent so we pass a false flag for syncing so it doesn't
                    // start a sync service in the background that would affect  the behaviour of
                    // this test.
                    return MainActivity.getStartIntent(
                            InstrumentationRegistry.getTargetContext(), false);
                }
            };

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    public final TestRule chain = RuleChain.outerRule(component).around(main);

    @Test
    public void listOfBuyersShows() {
        List<Ad> testDataAds = TestDataFactory.makeListBuyers(20);
        when(component.getMockDataManager().getBuyers()).thenReturn(Observable.just(testDataAds));

        main.launchActivity(null);

        int position = 0;
        /*for (Ad buyer : testDataAds) {
            onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(position));

            String name = buyer.getName();
            onView(withText(name)).check(matches(isDisplayed()));
            onView(withText(buyer.profile.email)).check(matches(isDisplayed()));
            position++;
        }*/
    }

}