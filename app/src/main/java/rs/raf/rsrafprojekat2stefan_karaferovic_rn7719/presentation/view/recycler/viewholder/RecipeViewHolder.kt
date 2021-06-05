package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Category
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Recipe
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.CategoryItemBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.RecipeItemBinding
import kotlin.math.round
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class RecipeViewHolder(
    private val context: Context,
    private val itemBinding: RecipeItemBinding,
    onItemClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.root.setOnClickListener { onItemClicked(adapterPosition) }
    }

    fun bind(recipe: Recipe) {
        itemBinding.recipeTitle.text = recipe.title
        itemBinding.publisherTv.text = recipe.publisher
        itemBinding.recipeReview.text = round(recipe.socialUrl.toDouble()).toString()
        Glide.with(context).load(recipe.imageUrl).centerCrop().into(itemBinding.recipeIv)
    }
}