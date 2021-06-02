package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models

data class RecipeDetails(
    val id: String,
    val imageUrl: String,
    val title: String,
    val ingredients: List<String>
)