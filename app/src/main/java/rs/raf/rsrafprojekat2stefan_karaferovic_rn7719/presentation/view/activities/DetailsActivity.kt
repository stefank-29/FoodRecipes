package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Recipe
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.ActivityDetailsBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.ActivityMainBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.contract.MainContract
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.states.DetailsState
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.states.RecipeState
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.viewmodel.MainViewModel
import timber.log.Timber
import kotlin.math.round

class DetailsActivity : AppCompatActivity() {

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    private lateinit var binding: ActivityDetailsBinding

    private lateinit var recipe: Recipe


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recipe = intent.getSerializableExtra(MainActivity.DETAILS_KEY) as Recipe
        mainViewModel.fetchDetails(recipe.id)
        mainViewModel.getDetails(recipe.id)
        init()
    }

    private fun init() {
        initUI()
        initObservers()
        initListeners()
    }

    private fun initUI() {
        binding.recipeTitle.text = recipe.title
        binding.recipeReview.text = round(recipe.socialUrl.toDouble()).toString()
        Glide.with(this).load(recipe.imageUrl).centerCrop().into(binding.recipeIv)

    }

    private fun initListeners() {
        binding.saveBtn.setOnClickListener {

        }
    }

    private fun initObservers() {
        mainViewModel.detailsState.observe(this, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: DetailsState) {
        when (state) {
            is DetailsState.Success -> {
                showProgressBar(false)
                Timber.e(state.details.ingredients.toString())
                for (ingredient in state.details.ingredients) {
                    val ingredientTv = TextView(this)
                    ingredientTv.text = ingredient
                    binding.ingredientsLinear.addView(ingredientTv)

                }
            }
            is DetailsState.Error -> {
                showProgressBar(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is DetailsState.DataFetched -> {
                showProgressBar(true)
            }
            is DetailsState.Loading -> {
                showProgressBar(true)
            }

        }
    }

    private fun showProgressBar(loading: Boolean) {
        if (loading) {
            binding.loadingProgress.visibility = View.VISIBLE
            binding.ingredientsSv.visibility = View.GONE


        } else {
            Handler().postDelayed({ // flicker
                binding.loadingProgress.visibility = View.GONE
                binding.ingredientsSv.visibility = View.VISIBLE
            }, 1000)
        }
    }

}