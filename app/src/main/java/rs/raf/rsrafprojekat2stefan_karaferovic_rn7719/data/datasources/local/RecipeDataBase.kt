package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.local.converters.DateConverter
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.local.converters.StringListConverter
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.RecipeEntity


@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class, StringListConverter::class)
abstract class RecipeDataBase : RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao
}