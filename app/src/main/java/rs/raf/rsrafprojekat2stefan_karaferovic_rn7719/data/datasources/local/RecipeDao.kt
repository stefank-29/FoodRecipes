package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.DetailsEntity
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.RecipeEntity

@Dao
abstract class RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<RecipeEntity>): Completable


    @Query("SELECT * FROM recipes WHERE title == :title")
    abstract fun getRecipes(title: String): Observable<List<RecipeEntity>>

    @Query("DELETE FROM recipes")
    abstract fun deleteAll()

    // atomicno (kao 1 query)
    @Transaction
    open fun deleteAndInsertAll(entities: List<RecipeEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()  // blokiranje threada
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRecipeDetails(ingredient: DetailsEntity): Completable

    @Query("SELECT * FROM details WHERE id == :recipeId")
    abstract fun getRecipeDetails(recipeId: String): Observable<DetailsEntity>


}