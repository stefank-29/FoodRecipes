package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.withTimeout
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.R
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Category
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.ActivityMainBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.contract.MainContract
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.adapter.CategoryAdapter
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.adapter.RecipeAdapter
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.diff.CategoryDiffCallback
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.diff.RecipeDiffCallback
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.states.RecipeState
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.viewmodel.MainViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var recipeAdapter: RecipeAdapter

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

    companion object {
        const val DETAILS_KEY = "recipe"
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
            renderState(it)
        })

    }

    private fun initRecyclers() {
        // category
        binding.categoryRv.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoryAdapter(CategoryDiffCallback()) {
            mainViewModel.getRecipes(it.title)
            mainViewModel.fetchRecipes(it.title)

        }
        binding.categoryRv.adapter = categoryAdapter
        categoryAdapter.submitList(categories)

        // recipes
        binding.recipeRv.layoutManager = LinearLayoutManager(this)
        recipeAdapter = RecipeAdapter(RecipeDiffCallback()) {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DETAILS_KEY, it)
            startActivity(intent)
        }
        binding.recipeRv.adapter = recipeAdapter

    }

    private fun renderState(state: RecipeState) {
        when (state) {
            is RecipeState.Success -> {
                showProgressBar(false)
                recipeAdapter.submitList(state.recipes)
            }
            is RecipeState.Error -> {
                showProgressBar(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is RecipeState.DataFetched -> {
                showProgressBar(true)
            }
            is RecipeState.Loading -> {
                showProgressBar(true)
            }

        }
    }

    private fun showProgressBar(loading: Boolean) {
        if (loading) {
            binding.loadingPb.visibility = View.VISIBLE
            binding.categoryRv.visibility = View.GONE
            binding.recipeRv.visibility = View.GONE


        } else {
            Handler().postDelayed({ // flicker
                binding.categoryRv.visibility = View.GONE
                binding.recipeRv.visibility = View.VISIBLE
                binding.loadingPb.visibility = View.GONE
            }, 2000)

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val search = menu?.findItem(R.id.action_search)

        search?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                binding.categoryRv.visibility = View.VISIBLE
                binding.recipeRv.visibility = View.GONE
                return true
            }

        })

        val searchView: SearchView = search?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text != null) {
                    mainViewModel.fetchRecipes(text)
                    mainViewModel.getRecipes(text)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                return false
            }

        })

        val categories = menu.findItem(R.id.categories)
        val itemsSaved = menu.findItem(R.id.savedMenus)

        categories.setOnMenuItemClickListener {
            binding.categoryRv.visibility = View.VISIBLE
            binding.recipeRv.visibility = View.GONE
            true
        }

        itemsSaved.setOnMenuItemClickListener {
            binding.categoryRv.visibility = View.GONE
            binding.recipeRv.visibility = View.GONE
            true
        }

        return true
    }


}