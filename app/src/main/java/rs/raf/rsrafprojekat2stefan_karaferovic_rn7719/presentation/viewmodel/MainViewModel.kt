package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Recipe
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Resource
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.repositories.RecipeRepository
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.contract.MainContract
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.states.RecipeState
import timber.log.Timber

class MainViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()

    override val recipeState: MutableLiveData<RecipeState> = MutableLiveData()

    // debounce
    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {

    }

    override fun fetchRecipes(recipeQuery: String) {
        val subscription = recipeRepository
            .fetchRecipes(recipeQuery)
            .startWith(Resource.Loading()) // kada pocne fetch stanje == loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when (it) {
                        // stanje fetcha
                        is Resource.Loading -> recipeState.value = RecipeState.Loading
                        is Resource.Success -> recipeState.value = RecipeState.DataFetched
                        is Resource.Error -> recipeState.value =
                            RecipeState.Error("Error during fetching data")
                    }

                },
                {
                    Timber.e(it) // ako baci error
                }
            )
        subscriptions.add(subscription)
    }

    override fun getRecipes(recipeQuery: String) {
        val subscription = recipeRepository
            .getRecipes(recipeQuery)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    recipeState.value = RecipeState.Success(it) // recipes list
                },
                {
                    recipeState.value = RecipeState.Error("Error during getting data from database")
                }
            )
        subscriptions.add(subscription)
    }


    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}