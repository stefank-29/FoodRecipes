package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipesResponse(
    val count: String,
    val recipes: List<RecipeResponse>
)
