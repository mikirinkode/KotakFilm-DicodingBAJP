package com.mikirinkode.kotakfilmlatihan.ui.home

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import com.mikirinkode.kotakfilmlatihan.R
import com.mikirinkode.kotakfilmlatihan.ui.detail.DetailCatalogueActivity
import com.mikirinkode.kotakfilmlatihan.utils.EspressoIdlingResource
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)
    @get:Rule
    val intentsTestRule = ActivityTestRule(DetailCatalogueActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }


    @Test
    fun loadMovies(){
        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_quote)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_detail_poster_bg)).check(matches(isDisplayed()))
    }

//    @Test
//    fun addFavoriteMovie(){
//        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onView(withId(R.id.toggle_favorite)).check(matches(isDisplayed()))
//        onView(withId(R.id.toggle_favorite)).perform(click())
//        onView(withId(R.id.btn_back)).check(matches(isDisplayed()))
//        onView(withId(R.id.btn_back)).perform(click())
//    }

    @Test
    fun addAndRemoveFavoriteMovie(){
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.toggle_favorite)).perform(click())
        onView(withId(R.id.btn_back)).perform(click())

        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_detail_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.toggle_favorite)).perform(click())
        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.label_no_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.label_no_favorite_desc)).check(matches(isDisplayed()))
    }

//    @Test
//    fun removeFavoriteMovie(){
//        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onView(withId(R.id.toggle_favorite)).check(matches(isDisplayed()))
//        onView(withId(R.id.toggle_favorite)).perform(click())
//        onView(withId(R.id.btn_back)).check(matches(isDisplayed()))
//        onView(withId(R.id.btn_back)).perform(click())
//
//        onView(withId(R.id.nav_favorite)).perform(click())
//        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onView(withId(R.id.toggle_favorite)).check(matches(isDisplayed()))
//        onView(withId(R.id.toggle_favorite)).perform(click())
//        onView(withId(R.id.btn_back)).check(matches(isDisplayed()))
//        onView(withId(R.id.btn_back)).perform(click())
//        onView(withId(R.id.label_no_favorite)).check(matches(isDisplayed()))
//    }

    @Test
    fun validateIntentShare(){
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_share)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_share)).perform(click())

        val shareIntent  = allOf(
            hasAction(Intent.ACTION_SEND),
//            hasExtra(Intent.EXTRA_TEXT, "Watch ${dummyMovie[0].title} on ${R.string.app_name}")
        )
        val expectedIntent  = allOf(
            hasAction(Intent.ACTION_CHOOSER),
            hasExtra(equalTo(Intent.EXTRA_INTENT), shareIntent),
            hasExtra(`is`(Intent.EXTRA_TITLE), `is`("Share To:"))
        )
        intended(expectedIntent)
    }

//    @Test
//    fun validateBtnBack(){
//        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onView(withId(R.id.btn_back)).check(matches(isDisplayed()))
//        onView(withId(R.id.btn_back)).perform(click())
//    }

    @Test
    fun loadTvShow(){
        onView(withId(R.id.nav_tv_show)).perform(click())
        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadDetailTvShow(){
        onView(withId(R.id.nav_tv_show)).perform(click())
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_quote)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_detail_poster_bg)).check(matches(isDisplayed()))
    }

//    @Test
//    fun addFavoriteTvShow(){
//        onView(withId(R.id.nav_tv_show)).perform(click())
//        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onView(withId(R.id.toggle_favorite)).check(matches(isDisplayed()))
//        onView(withId(R.id.toggle_favorite)).perform(click())
//        onView(withId(R.id.btn_back)).check(matches(isDisplayed()))
//        onView(withId(R.id.btn_back)).perform(click())
//    }

    @Test
    fun addAndRemoveFavoriteTvShow(){
        onView(withId(R.id.nav_tv_show)).perform(click())
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.toggle_favorite)).perform(click())
        onView(withId(R.id.btn_back)).perform(click())


//        onView(withId(R.id.nav_tv_show)).perform(click())
//        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onView(withId(R.id.toggle_favorite)).perform(click())

        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_detail_poster)).check(matches(isDisplayed()))


        onView(withId(R.id.toggle_favorite)).perform(click())
        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.label_no_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.label_no_favorite_desc)).check(matches(isDisplayed()))
    }

//    @Test
//    fun removeFavoriteTvShow(){
////        onView(withId(R.id.nav_tv_show)).perform(click())
////        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
////        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
////        onView(withId(R.id.toggle_favorite)).check(matches(isDisplayed()))
////        onView(withId(R.id.toggle_favorite)).perform(click())
////        onView(withId(R.id.btn_back)).check(matches(isDisplayed()))
////        onView(withId(R.id.btn_back)).perform(click())
//
//        onView(withId(R.id.nav_favorite)).perform(click())
//        onView(withText("TV SHOW")).perform(click())
//        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onView(withId(R.id.toggle_favorite)).check(matches(isDisplayed()))
//        onView(withId(R.id.toggle_favorite)).perform(click())
//        onView(withId(R.id.btn_back)).check(matches(isDisplayed()))
//        onView(withId(R.id.btn_back)).perform(click())
//        onView(withId(R.id.label_no_favorite)).check(matches(isDisplayed()))
//    }

    @After
    fun tearDown(){
        Intents.release()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
}
