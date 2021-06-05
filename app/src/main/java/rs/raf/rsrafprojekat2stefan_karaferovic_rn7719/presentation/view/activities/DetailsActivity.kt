package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Recipe
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.ActivityDetailsBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.ActivityMainBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.contract.MainContract
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.viewmodel.MainViewModel

class DetailsActivity : AppCompatActivity() {

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    private lateinit var binding: ActivityDetailsBinding

    private lateinit var recipeId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recipeId = intent.getStringExtra(MainActivity.DETAILS_KEY)
        mainViewModel.getDetails(recipeId)
        mainViewModel.fetchDetails(recipeId)
        init()
    }

    private fun init() {
        initUI()
        initObservers()
        initListeners()
    }

    private fun initUI() {

    }

    private fun initListeners() {}

    private fun initObservers() {

    }
}