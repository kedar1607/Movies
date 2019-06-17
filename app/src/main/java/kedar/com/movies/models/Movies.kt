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
data class Movies(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<Result>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)