package com.example.goustoproducts

import android.content.res.Resources
import android.view.KeyEvent
import androidx.test.espresso.Espresso.onView
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

    private lateinit var stringToBetyped: String

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickingOnItemInListOpensDetailView() {

        Thread.sleep(2000)
        val productTitle = "Borsao Macabeo"
        val productDescription = ""
            onView(withId(R.id.product_recycler_view))
                .perform(
                    RecyclerViewActions.actionOnItem<ProductListAdapter.ViewHolder>(
                        hasDescendant(withText(productTitle)), click()
                    )
                );
        onView(withId(R.id.detail_title)).check(matches(withText(productTitle)))
    }

    @Test
    fun searchingTextReturnsItemInList() {

        val searchText = "dolci"
        val productTitle = "Dolci Colline Prosecco (200ml bottle)"

        Thread.sleep(2000)

        onView(withId(R.id.product_search))
            .perform(click())
            .perform(typeText(searchText), closeSoftKeyboard())

        Thread.sleep(2000)

        onView(withId(R.id.product_recycler_view)).check(matches(hasChildCount(1)))
        onView(withId(R.id.product_recycler_view)).check(matches(hasDescendant(withText(productTitle))))
    }
}