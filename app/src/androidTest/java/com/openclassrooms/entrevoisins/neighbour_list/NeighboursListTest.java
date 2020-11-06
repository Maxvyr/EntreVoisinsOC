
package com.openclassrooms.entrevoisins.neighbour_list;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neigbour_profil.ProfilActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.ViewPagerActions.scrollRight;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class NeighboursListTest {

    // This is fixed
    private static final int ITEMS_COUNT = 12;

    //Variable
    private ListNeighbourActivity mActivity;
    private final List<Neighbour> neighbourListTest = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule<>(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        onView(withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we click on an item, the user profile is shown
     */
    @Test
    public void myNeighboursList_clickNeighbour_openProfilActivity() {
        //Variable
        final int positionItem = 1;
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(positionItem,click()));
        onView(withId(R.id.coordinatorLayoutProfil)).check(matches(isDisplayed()));
    }

    /**
     * When we click on an item, check if the good user is show (name of the user)
     */
    @Test
    public void myNeighbourList_clikcNeighbour_shouldShowingGoodNeighbourData() {
        //Variable
        final int positionItem = 0;
        Neighbour neighbourSelected = this.neighbourListTest.get(positionItem);
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(positionItem,click()));
        onView(withId(R.id.namenNeighbourWhite))
                .check(matches(withText(neighbourSelected.getName())));

        onView(withId(R.id.namenNeighbourBlack))
                .check(matches(withText(neighbourSelected.getName())));
    }

    /**
     * When we click on the favorite button, the user is shown in the favorite list
     */
    @Test
    public void myNeighboursList_clickNeighbour_addToFavorite_shouldBeFavorite() {
        //Variable
        final int positionItemListNeighbour = 10;
        final int positionItemListFavNeighbour = 0;
        final int sizelistFav = 1;

        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(positionItemListNeighbour,click()));
        onView(ViewMatchers.withId(R.id.floatingActionButtonProfil))
                .perform((ViewAction) click());
        onView(ViewMatchers.withId(R.id.container))
                .perform(scrollRight());
        onView(ViewMatchers.withId(R.id.list_fav_neighbours))
                .check(withItemCount(sizelistFav));
        onView(ViewMatchers.withId(R.id.list_fav_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(positionItemListFavNeighbour, click()));
        onView(ViewMatchers.withId(R.id.namenNeighbourBlack))
                .check(matches(withText(this.neighbourListTest.get(positionItemListNeighbour).getName())));
        onView(ViewMatchers.withId(R.id.namenNeighbourWhite))
                .check(matches(withText(this.neighbourListTest.get(positionItemListNeighbour).getName())));
    }


    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        //Given
        final int itemsCount = this.neighbourListTest.size();
        final int positionItem = itemsCount - 1;
        onView(withId(R.id.list_neighbours)).check(withItemCount(itemsCount));
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(positionItem, new DeleteViewAction()));
        onView(withId(R.id.list_neighbours)).check(withItemCount(itemsCount - 1));
    }
}