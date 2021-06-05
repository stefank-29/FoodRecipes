package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.repositories

import io.reactivex.Observable
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Details
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Recipe
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Resource

interface RecipeRepository {

    fun fetchRecipes(recipe: String): Observable<Resource<Unit>>

    fun getRecipes(recipe: String): Observable<List<Recipe>>

    fun fetchDetails(recipeId: String): Observable<Resource<Unit>>
    fun getDetails(recipeId: String): Observable<Details>
}