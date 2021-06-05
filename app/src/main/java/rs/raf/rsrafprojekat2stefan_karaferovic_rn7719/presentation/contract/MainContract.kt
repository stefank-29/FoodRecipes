package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.states.DetailsState
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.states.RecipeState

interface MainContract {

    interface ViewModel {

        val recipeState: LiveData<RecipeState>
        val detailsState: LiveData<DetailsState>


        fun fetchRecipes(recipeQuery: String)
        fun getRecipes(recipeQuery: String)

        fun fetchDetails(recipeId: String)
        fun getDetails(recipeId: String)
    }
}