package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.repositories

import io.reactivex.Observable
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.local.RecipeDao
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.remote.RecipesService
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Recipe
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.RecipeEntity
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Resource
import timber.log.Timber

class RecipeRepositoryImpl(
    private val localDataSource: RecipeDao, // local
    private val remoteDataSource: RecipesService // remote
) : RecipeRepository {


    override fun fetchRecipes(recipe: String): Observable<Resource<Unit>> {
        return remoteDataSource
            .getRecipes(recipe, "1")
            .doOnNext {
                Timber.e("Insert into db")

                val entities = it.recipes.map {
                    RecipeEntity(
                        it.id,
                        it.title,
                        it.imageUrl
                    )
                }

                localDataSource.deleteAndInsertAll(entities)

            }
            .map {
                // uspesno upisani podaci u kes
                Resource.Success(Unit) // vratim success poruku
            }
    }

    override fun getRecipes(recipe: String): Observable<List<Recipe>> {
        return localDataSource
            .getRecipes(recipe)
            .map {
                it.map {
                    Recipe(
                        id = it.id,
                        title = it.title,
                        imageUrl = it.imageUrl,
                        ingredients = listOf()
                    )
                }
            }
    }


}