package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.states

import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Details
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Recipe

sealed class DetailsState {
    object Loading : DetailsState()
    object DataFetched : DetailsState()
    data class Success(val recipes: List<Details>) : DetailsState()
    data class Error(val message: String) : DetailsState()
}
