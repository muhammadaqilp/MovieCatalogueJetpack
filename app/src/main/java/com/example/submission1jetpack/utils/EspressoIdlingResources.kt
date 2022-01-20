package com.example.submission1jetpack.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResources {
    private const val RESOURCE = "GLOBAL"
    val idlingResources = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResources.increment()
    }

    fun decrement() {
        idlingResources.decrement()
    }
}