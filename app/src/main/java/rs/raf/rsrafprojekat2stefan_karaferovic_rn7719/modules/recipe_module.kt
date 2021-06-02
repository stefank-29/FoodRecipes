package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.local.RecipeDataBase
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.remote.RecipesService
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.repositories.RecipeRepository
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.repositories.RecipeRepositoryImpl

val recipeModule = module {

    viewModel { MainViewModel(movieRepository = get()) }

    single<RecipeRepository> {
        RecipeRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }

    // Dao
    single { get<RecipeDataBase>().getMovieDao() }

    // Service
    single<RecipesService> { create(retrofit = get()) }

}