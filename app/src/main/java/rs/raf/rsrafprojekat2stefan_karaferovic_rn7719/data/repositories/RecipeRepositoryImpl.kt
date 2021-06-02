package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.repositories

import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.local.RecipeDao
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.remote.RecipesService

class RecipeRepositoryImpl(
    private val localDataSource: RecipeDao, // local
    private val remoteDataSource: RecipesService // remote
) : RecipeRepository {
}