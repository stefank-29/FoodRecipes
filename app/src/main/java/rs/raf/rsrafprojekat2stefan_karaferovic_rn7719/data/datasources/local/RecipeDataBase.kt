package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.local.converters.DateConverter
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.local.converters.StringListConverter
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.DetailsEntity
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.RecipeEntity


@Database(
    entities = [RecipeEntity::class, DetailsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class, DateConverter::class)
abstract class RecipeDataBase : RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao
}