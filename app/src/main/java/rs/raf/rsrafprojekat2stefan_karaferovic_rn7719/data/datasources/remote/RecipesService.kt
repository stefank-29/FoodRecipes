package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.RecipeDetailsResponse
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.RecipeResponse

interface RecipesService {

    @GET("recipes")
    fun getPage(
        @Query("q") recipe: String,
        @Query("page") page: String = "1"
    ): Observable<RecipeResponse>

    @GET("recipes/{id}")
    fun getRecipeDetails(@Path("id") id: String): Observable<RecipeDetailsResponse>

}