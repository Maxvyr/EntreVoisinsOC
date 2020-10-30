
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
        // First scroll to the position that needs to be matched and click on it.
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
        // When : we perform a click on an item of the list at the positionItem
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(positionItem,click()));

        // Then : the user profile is shown
        onView(withId(R.id.coordinatorLayoutProfil)).check(matches(isDisplayed()));
    }

    /**
     * When we click on an item, check if the good user is show (name of the user)
     */
    @Test
    public void myNeighbourList_clikcNeighbour_shouldShowingGoodNeighbourData() {
        //Variable
        final int positionItem = 0;
        // Retrieves the neighbour at positionItem
        Neighbour neighbourSelected = this.neighbourListTest.get(positionItem);

        // When : we perform a click on an item of the list at the positionItem
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(positionItem,click()));

        // Then : the user profile is shown and it displays the good personal data
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

        // When : We perform a click on favorite button of ProfileActivity
        for (int i=0 ; i<this.neighbourListTest.size() ; i++) {
            // Clicks on item of RecyclerView
            onView(withId(R.id.list_neighbours))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i,click()));

            // Clicks on FAB of ProfileActivity
            onView(ViewMatchers.withId(R.id.floatingActionButtonProfil))
                    .perform((ViewAction) click());
        }

        // Swipe from NeighbourFragment to FavoritesFragment of ViewPager
        onView(ViewMatchers.withId(R.id.container))
                .perform(scrollRight());

        // Then: Checks if the RecyclerView contains all neighbour of the list
        onView(ViewMatchers.withId(R.id.list_fav_neighbours))
                .check(withItemCount(this.neighbourListTest.size()));

        // Checks if the neighbour are the good neighbour
        for (int i=0 ; i<this.neighbourListTest.size() ; i++) {
            // Clicks on item of RecyclerView
            onView(ViewMatchers.withId(R.id.list_fav_neighbours))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));

            // Then : the user profile is shown and it displays the good personal data
            onView(ViewMatchers.withId(R.id.namenNeighbourBlack))
                    .check(matches(withText(this.neighbourListTest.get(i).getName())));

            onView(ViewMatchers.withId(R.id.namenNeighbourWhite))
                    .check(matches(withText(this.neighbourListTest.get(i).getName())));

            // Clicks on the Up button of the ToolBar [or pressBack()]
            onView(withContentDescription("Navigate up"))
                    .perform(click());

            // Swipe from NeighbourFragment to FavoritesFragment of ViewPager
            onView(ViewMatchers.withId(R.id.container))
                    .perform(scrollRight());
        }
    }


    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        //Given
        final int itemsCount = this.neighbourListTest.size();
        final int positionItem = itemsCount - 1;

        //When  list size
        onView(withId(R.id.list_neighbours)).check(withItemCount(itemsCount));

        //Then Click on a delete icon at the first position of the list
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(positionItem, new DeleteViewAction()));

        // Check if list size itemsCount - 1
        onView(withId(R.id.list_neighbours)).check(withItemCount(itemsCount - 1));
    }
}