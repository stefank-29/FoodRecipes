package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Recipe
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.ActivityDetailsBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.ActivityMainBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.contract.MainContract
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.viewmodel.MainViewModel
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
        mainViewModel.getDetails(recipe.id)
        mainViewModel.fetchDetails(recipe.id)
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
            val intent = Intent(this, SaveMealActivity::class.java)
            intent.putExtra(MainActivity.MESSAGE_KEY_RECIPE, recipe)
            startActivity(intent)
        }
    }

    private fun initObservers() {
        mainViewModel.detailsState.observe(this, Observer {
            renderIngredientState(it)
        })
    }
}