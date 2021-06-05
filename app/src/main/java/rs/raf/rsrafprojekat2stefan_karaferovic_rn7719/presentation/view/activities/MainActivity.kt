package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Category
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.ActivityMainBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.contract.MainContract
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.adapter.CategoryAdapter
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.diff.CategoryDiffCallback
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.states.RecipeState
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.viewmodel.MainViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    private lateinit var categoryAdapter: CategoryAdapter

    private lateinit var searchQuery: String

    private val categories: List<Category> = listOf(
        Category(
            "1",
            "Barbecue",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1575163942/RecipesApi/bbqporsandwich520300x20094cd7af4.jpg"
        ),
        Category(
            "2",
            "Breakfast",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1618257399/RecipesApi/Breakfast.png"
        ),
        Category(
            "3",
            "Chicken",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1618257399/RecipesApi/Chicken.png"
        ),
        Category(
            "4",
            "Beef",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1618257399/RecipesApi/Steak.png"
        ),
        Category(
            "5",
            "Brunch",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1575163942/RecipesApi/1088_MEDIUMdacc.jpg"
        ),
        Category(
            "6",
            "Dinner",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1575163942/RecipesApi/20100505_dt_garlic_soup2c70f.jpg"
        ),
        Category(
            "7",
            "Wine",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1575163942/RecipesApi/3677367a4e.jpg"
        ),
        Category(
            "8",
            "Italian",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1575163942/RecipesApi/5278973957_3f9f9a21c2_o7a1b.jpg"
        )

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initView()
        initListeners()
        initRecyclers()
        initObservers()
    }

    private fun initView() {

    }

    private fun initListeners() {

    }

    private fun initObservers() {
        mainViewModel.recipeState.observe(this, Observer {
            Timber.e(it.toString())
            renderState(it) // renderujem Ui
        })
        // Pravimo subscription kad observablu koji je vezan za getAll iz baze
        // Na svaku promenu tabele, obserrvable ce emitovati na onNext sve elemente
        // koji zadovoljavaju query
        // mainViewModel.getRecipes()  // pokrenem fetchovanje iz kesa (tek tad se odradi izmena MovieState-a)
        // Pokrecemo operaciju dovlacenja podataka sa servera, kada podaci stignu,
        // bice sacuvani u bazi, tada ce se triggerovati observable na koji smo se pretplatili
        // preko metode getAllMovies()
    }

    private fun initRecyclers() {
        // category
        binding.categoryRv.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoryAdapter(CategoryDiffCallback()) {
            binding.categoryRv.visibility = View.GONE
            binding.recipeRv.visibility = View.VISIBLE
            searchQuery = it.title
            mainViewModel.fetchRecipes(it.title)
            mainViewModel.getRecipes(it.title)

        }
        binding.categoryRv.adapter = categoryAdapter
        categoryAdapter.submitList(categories)
    }

    private fun renderState(state: RecipeState) {
        when (state) {
            is RecipeState.Success -> {
                showProgressBar(false)
            }
            is RecipeState.Error -> {
                showProgressBar(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is RecipeState.DataFetched -> {
                showProgressBar(false)
                Toast.makeText(this, "Data fetched from server", Toast.LENGTH_LONG).show()
            }
            is RecipeState.Loading -> {
                showProgressBar(true)
            }

        }
    }

    private fun showProgressBar(loading: Boolean) {
        binding.categoryRv.isVisible = !loading
        binding.recipeRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
        binding.loadingPb.visibility = View.VISIBLE
    }


}