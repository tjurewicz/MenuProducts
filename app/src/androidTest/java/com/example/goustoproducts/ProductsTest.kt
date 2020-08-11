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
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.goustoproducts.ui.products.ProductListAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductsTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickingOnItemInListOpensDetailView() {

        Thread.sleep(1500)
        val productTitle = "Borsao Macabeo"
        val productDescription =
            "A flavoursome Summer wine made from the indigenous Macabeo grape in northern Spain. A balanced, modern white with flavours of ripe peach, zesty lemon and nutty undertones, it leaves the palate with a clean and fruity finish."
        onView(withId(R.id.product_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<ProductListAdapter.ViewHolder>(
                    hasDescendant(withText(productTitle)), click()
                )
            );
        onView(withId(R.id.detail_title)).check(matches(withText(productTitle)))
        onView(withId(R.id.detail_description)).check(matches(withText(productDescription)))
    }

    @Test
    fun searchingTextReturnsItemInList() {

        val searchText = "dolci"
        val productTitle = "Dolci Colline Prosecco (200ml bottle)"
        val productPrice = "£3.99"

        Thread.sleep(1500)

        onView(withId(R.id.product_search))
            .perform(clickSearch())
            .perform(typeText(searchText), closeSoftKeyboard())

        onView(withId(R.id.product_recycler_view)).check(matches(hasChildCount(1)))
        onView(withId(R.id.product_recycler_view)).check(matches(hasDescendant(withText(productTitle))))
        onView(withId(R.id.product_recycler_view)).check(matches(hasDescendant(withText(productPrice))))
    }

    private fun clickSearch(): ViewAction {
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