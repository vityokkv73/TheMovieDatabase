package com.deerhunter.themoviedatabase.data

data class MoviesList(val page: Int, val totalResults: Int, val totalPages: Int, val results: List<MovieBrief>)