package com.deerhunter.themoviedatabase.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String?,
    val imdbId: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val productionCompanies: List<Company>,
    val productionCountries: List<Country>,
    val releaseDate: Date,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<Language>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Int
)

data class Genre(val id: Int, val name: String)
data class Language(@SerializedName("iso_639_1") val iso: String, val name: String)
data class Country(@SerializedName("iso_3166_1") val iso: String, val name: String)
data class Company(val id: Int, val logoPath: String, val name: String, val origin_country: String)