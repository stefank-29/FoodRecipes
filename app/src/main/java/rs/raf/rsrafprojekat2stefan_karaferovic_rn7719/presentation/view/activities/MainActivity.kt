package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.R
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Category
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.ActivityMainBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.contract.MainContract
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.viewmodel.MainViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val recipeViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    private val categories: List<Category> = listOf(
        Category(
            "Barbecue",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1575163942/RecipesApi/bbqporsandwich520300x20094cd7af4.jpg"
        ),
        Category(
            "Breakfast",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1618257399/RecipesApi/Breakfast.png"
        ),
        Category(
            "Chicken",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1618257399/RecipesApi/Chicken.png"
        ),
        Category(
            "Beef",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1618257399/RecipesApi/Steak.png"
        ),
        Category(
            "Brunch",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1575163942/RecipesApi/1088_MEDIUMdacc.jpg"
        ),
        Category(
            "Dinner",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1575163942/RecipesApi/20100505_dt_garlic_soup2c70f.jpg"
        ),
        Category(
            "Wine",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1575163942/RecipesApi/3677367a4e.jpg"
        ),
        Category(
            "Italian",
            "https://res.cloudinary.com/dk4ocuiwa/image/upload/v1575163942/RecipesApi/5278973957_3f9f9a21c2_o7a1b.jpg"
        )

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() {

    }


}