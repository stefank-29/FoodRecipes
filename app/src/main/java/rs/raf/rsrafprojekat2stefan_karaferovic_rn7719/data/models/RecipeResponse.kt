package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResponse(
    val imageUrl: String,
    val socialUrl: String,
    val publisher: String,
    val sourceUrl: String,
    val id: String,
    val publishedId: String,
    val title: String,
)
