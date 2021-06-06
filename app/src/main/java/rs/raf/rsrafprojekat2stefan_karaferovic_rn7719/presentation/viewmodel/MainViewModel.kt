package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Resource
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.repositories.RecipeRepository
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.contract.MainContract
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.states.DetailsState
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.states.RecipeState
import timber.log.Timber

class MainViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()

    override val recipeState: MutableLiveData<RecipeState> = MutableLiveData()
    override val detailsState: MutableLiveData<DetailsState> = MutableLiveData()

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
                    Timber.e("$it")
                    recipeState.value = RecipeState.Success(it) // recipes list
                },
                {
                    recipeState.value = RecipeState.Error("Error during getting data from database")
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchDetails(recipeId: String) {
        val subscription = recipeRepository
            .fetchDetails(recipeId)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when (it) {
                        is Resource.Loading -> detailsState.value = DetailsState.Loading
                        is Resource.Success -> detailsState.value = DetailsState.DataFetched
                        is Resource.Error -> detailsState.value =
                            DetailsState.Error("Error during fetching data")
                    }
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getDetails(recipeId: String) {
        val subscription = recipeRepository
            .getDetails(recipeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    detailsState.value = DetailsState.Success(it) // recipe details
                },
                {
                    detailsState.value =
                        DetailsState.Error("Error during getting data from database")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }


    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}