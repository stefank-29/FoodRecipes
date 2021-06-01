package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class RecipeEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    @ColumnInfo(name = "recipe_id")
    val recipeId: String,
)