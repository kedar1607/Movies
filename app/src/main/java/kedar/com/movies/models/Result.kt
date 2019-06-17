package kedar.com.movies.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * This is a very important annotation that needs to be added on top of every data classes otherwise
 * it could mess a lot of things are not downloaded.
 *
 * This data class could be generated from "Generate Kotlin Data Class" plugin - It also annonate all the fields
 * with the JSON key names.
 */
@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
)