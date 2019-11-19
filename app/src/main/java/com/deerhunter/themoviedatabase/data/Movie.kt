package com.deerhunter.themoviedatabase.data

import java.util.*

data class MovieBrief(private val id: Int, private val title: String, private val originalTitle: String, private val posterPath: String, private val adult: Boolean, private val genreIds: List<Int>, private val voteAverage: Float, private val releaseDate: Date, private val overview: String)