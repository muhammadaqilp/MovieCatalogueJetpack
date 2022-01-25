package com.example.submission1jetpack.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object SortUtils {

    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val RANDOM = "Random"

    fun getSortedQueryMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieentities ")
        when(filter){
            NEWEST -> {
                simpleQuery.append("ORDER BY movieRelease DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY movieRelease ASC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryTv(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tvshowentities ")
        when(filter){
            NEWEST -> {
                simpleQuery.append("ORDER BY tvShowRelease DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY tvShowRelease ASC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

}