package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.states

import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Recipe

sealed class RecipeState {
    object Loading : RecipeState()
    object DataFetched : RecipeState()
    data class Success(val movies: List<Recipe>) : RecipeState()
    data class Error(val message: String) : RecipeState()
}
