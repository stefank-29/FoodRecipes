package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailsResponse(
    val id: String,
    val title: String,
    val ingredients: List<String>,
    val imageUrl: String
//    val socialUrl: String,
//    val publisher: String,
//    val publishedId: String,
//    val sourceUrl: String,
)