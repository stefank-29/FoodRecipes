package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.DetailsResponse
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.RecipeResponse
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.RecipesResponse

interface RecipesService {

    @GET("recipes")
    fun getRecipes(
        @Query("q") recipe: String,
        @Query("page") page: String = "1"
    ): Observable<RecipesResponse>

    @GET("recipes/{id}")
    fun getRecipeDetails(@Path("id") id: String): Observable<DetailsResponse>

}