package com.deerhunter.themoviedatabase.data

import java.util.*

data class MovieBrief(
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