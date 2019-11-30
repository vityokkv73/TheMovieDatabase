package com.deerhunter.themoviedatabase.data

data class Configuration(
    val images: Images,
    val changeKeys: List<String>
)

data class Images(
    val baseUrl: String,
    val secureBaseUrl: String,
    val backdropSizes: List<String>,
    val logoSizes: List<String>,
    val posterSizes: List<String>,
    val profileSizes: List<String>,
    val stillSizes: List<String>
)