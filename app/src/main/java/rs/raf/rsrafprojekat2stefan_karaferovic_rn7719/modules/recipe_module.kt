package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.local.RecipeDataBase
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.remote.RecipesService
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.repositories.RecipeRepository
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.repositories.RecipeRepositoryImpl
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.viewmodel.MainViewModel

val recipeModule = module {

    viewModel { MainViewModel(recipeRepository = get()) }

    single<RecipeRepository> {
        RecipeRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }

    // Dao
    single { get<RecipeDataBase>().getRecipeDao() }

    // Service
    single<RecipesService> { create(retrofit = get()) }

}