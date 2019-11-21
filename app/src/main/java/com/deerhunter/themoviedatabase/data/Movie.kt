package com.deerhunter.themoviedatabase.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "popular_movie_briefs")
data class PopularMovieBrief(
    @PrimaryKey
    val id: Int,
    val title: String,
    val originalTitle: String,
    val posterPath: String,
    val adult: Boolean,
    val genreIds: List<Int>,
    val voteAverage: Float,
    val releaseDate: Date,
    val overview: String
)