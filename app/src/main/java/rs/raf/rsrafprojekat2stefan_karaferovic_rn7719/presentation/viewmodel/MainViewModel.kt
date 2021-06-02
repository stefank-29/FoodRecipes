package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.repositories.RecipeRepository
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.contract.MainContract

class MainViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()

    // debounce
    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {

    }

}