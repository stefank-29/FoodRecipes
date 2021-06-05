package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResponse(
    val id: String,
    val title: String,
    val imageUrl: String,
    val socialUrl: String,
    val publisher: String
//    val sourceUrl: String,
//    val publishedId: String,
)
