package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
data class RecipeDetailsEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val ingredients: List<String> = listOf()
)
