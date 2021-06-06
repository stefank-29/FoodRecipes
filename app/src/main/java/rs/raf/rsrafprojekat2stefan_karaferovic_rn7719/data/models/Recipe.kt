package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models

import java.io.Serializable


data class Recipe(
    val id: String,
    val title: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val socialUrl: String,
    val publisher: String
) : Serializable
