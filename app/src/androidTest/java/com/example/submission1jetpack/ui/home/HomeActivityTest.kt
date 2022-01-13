package com.example.submission1jetpack.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.submission1jetpack.R
import com.example.submission1jetpack.ui.home.config.RecyclerViewItemCountAssertion
import com.example.submission1jetpack.utils.DataDummy
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovies = DataDummy.generateMovieData()
    private val dummyTvShows = DataDummy.generateTvShowsData()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovies[0].movieTitle)))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(withText(dummyMovies[0].movieRelease)))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(withText(dummyMovies[0].movieDuration)))
        onView(withId(R.id.tv_category)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_category)).check(matches(withText("Movie")))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovies[0].movieOverview)))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvshows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadDetailTvShows() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvshows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyTvShows[0].tvShowTitle)))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(withText(dummyTvShows[0].tvShowRelease)))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(withText(dummyTvShows[0].tvShowDuration)))
        onView(withId(R.id.tv_category)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_category)).check(matches(withText("TV Show")))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyTvShows[0].tvShowOverview)))
    }

    //memastikan data movies tidak kosong
    @Test
    fun countListMovies() {
        onView(withId(R.id.rv_movies)).check(RecyclerViewItemCountAssertion(10))
    }

    //memastikan data tv shows tidak kosong
    @Test
    fun countListTvShows() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvshows)).check(RecyclerViewItemCountAssertion(10))
    }

    //memastikan data gambar movies detail berhasil dimuat
    @Test
    fun checkLoadMoviesImages() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_poster)).check(matches(withTagValue(equalTo(dummyMovies[0].moviePoster))))
    }

    //memastikan data gambar tv shows detail berhasil dimuat
    @Test
    fun checkLoadTvShowImages() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvshows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_poster)).check(matches(withTagValue(equalTo(dummyTvShows[0].tvShowPoster))))
    }
}