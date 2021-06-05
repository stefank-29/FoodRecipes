package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Category
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Recipe
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.CategoryItemBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.RecipeItemBinding

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
        itemBinding.recipeReview.text = "100"
        Glide.with(context).load(recipe.imageUrl).circleCrop().into(itemBinding.recipeIv)
    }
}