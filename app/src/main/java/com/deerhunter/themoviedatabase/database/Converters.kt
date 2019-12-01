package com.deerhunter.themoviedatabase.database

import androidx.room.TypeConverter
import com.deerhunter.themoviedatabase.data.Company
import com.deerhunter.themoviedatabase.data.Country
import com.deerhunter.themoviedatabase.data.Genre
import com.deerhunter.themoviedatabase.data.Language
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class RoomConverters {
    @TypeConverter
    fun readListOfIntFromString(genreIds: String): List<Int> {
        return genreIds.split(",").mapNotNull { it.trim().toIntOrNull() }
    }

    @TypeConverter
    fun writeStringFromListOfInt(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun readDateFromLong(time: Long): Date {
        return Date(time)
    }

    @TypeConverter
    fun writeLongFromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun readListOfGenreFromString(genres: String): List<Genre> {
        return Gson().fromJson(genres, object : TypeToken<List<Genre>>() {}.type);
    }

    @TypeConverter
    fun writeStringFromListOfGenres(genres: List<Genre>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun readListOfCompanyFromString(companies: String): List<Company> {
        return Gson().fromJson(companies, object : TypeToken<List<Company>>() {}.type);
    }

    @TypeConverter
    fun writeStringFromListOfCompanies(companies: List<Company>): String {
        return Gson().toJson(companies)
    }

    @TypeConverter
    fun readListOfCountriesFromString(companies: String): List<Country> {
        return Gson().fromJson(companies, object : TypeToken<List<Country>>() {}.type);
    }

    @TypeConverter
    fun writeStringFromListOfCountries(countries: List<Country>): String {
        return Gson().toJson(countries)
    }

    @TypeConverter
    fun readListOfLanguagesFromString(languages: String): List<Language> {
        return Gson().fromJson(languages, object : TypeToken<List<Language>>() {}.type);
    }

    @TypeConverter
    fun writeStringFromListOfLanguages(languages: List<Language>): String {
        return Gson().toJson(languages)
    }
}