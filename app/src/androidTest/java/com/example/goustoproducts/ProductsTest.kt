package com.example.goustoproducts

import android.view.InputDevice
import android.view.MotionEvent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.goustoproducts.ui.products.ProductListAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductsTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        // Making sure that the list renders before the test checks for views
        Thread.sleep(2000)
    }

    @Test
    fun clickingOnItemInListOpensDetailView() {

        val productTitle = "Borsao Macabeo"
        val productPrice = "£6.95"
        val productDescription =
            "A flavoursome Summer wine made from the indigenous Macabeo grape in northern Spain. A balanced, modern white with flavours of ripe peach, zesty lemon and nutty undertones, it leaves the palate with a clean and fruity finish."

        onView(withId(R.id.product_recycler_view)).check(matches(hasDescendant(withText(productTitle))))
        onView(withId(R.id.product_recycler_view)).check(matches(hasDescendant(withText(productPrice))))
        onView(withText(productDescription)).check(doesNotExist())

        onView(withId(R.id.product_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<ProductListAdapter.ViewHolder>(
                    hasDescendant(withText(productTitle)), click()
                )
            );

        onView(withId(R.id.detail_title)).check(matches(withText(productTitle)))
        onView(withId(R.id.detail_description)).check(matches(withText(productDescription)))
        onView(withText(productPrice)).check(doesNotExist())
    }

    @Test
    fun clickingOnBackButtonInDetailViewReturnsToListView() {

        val productTitle = "Borsao Macabeo"
        val productPrice = "£6.95"

        onView(withId(R.id.product_recycler_view)).check(matches(hasDescendant(withText(productTitle))))
        onView(withId(R.id.product_recycler_view)).check(matches(hasDescendant(withText(productPrice))))

        onView(withId(R.id.product_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<ProductListAdapter.ViewHolder>(
                    hasDescendant(withText(productTitle)), click()
                )
            );

        onView(withId(R.id.detail_title)).check(matches(withText(productTitle)))
        onView(withText(productPrice)).check(doesNotExist())

        onView(withId(R.id.back_button)).perform(click())

        onView(withId(R.id.product_recycler_view)).check(matches(hasDescendant(withText(productTitle))))
        onView(withId(R.id.product_recycler_view)).check(matches(hasDescendant(withText(productPrice))))
    }

    @Test
    fun searchingTextReturnsItemInList() {

        val searchText = "dolci"
        val productTitle = "Dolci Colline Prosecco (200ml bottle)"
        val productPrice = "£3.99"

        onView(withText(productTitle)).check(doesNotExist())
        onView(withId(R.id.product_search))
            .perform(clickSearch())
            .perform(typeText(searchText), closeSoftKeyboard())

        onView(withId(R.id.product_recycler_view)).check(matches(hasChildCount(1)))
        onView(withId(R.id.product_recycler_view)).check(matches(hasDescendant(withText(productTitle))))
        onView(withId(R.id.product_recycler_view)).check(matches(hasDescendant(withText(productPrice))))
    }

    private fun clickSearch(): ViewAction {
        // custom click function as the standard one was clicking in the wrong place
        return actionWithAssertions(
            GeneralClickAction(
                Tap.SINGLE,
                GeneralLocation.CENTER_LEFT,
                Press.FINGER,
                InputDevice.SOURCE_UNKNOWN,
                MotionEvent.BUTTON_PRIMARY
            )
        )
    }
}