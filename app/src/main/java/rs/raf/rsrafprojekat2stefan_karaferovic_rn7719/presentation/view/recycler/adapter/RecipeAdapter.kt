package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Category
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Recipe
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.CategoryItemBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.RecipeItemBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.diff.CategoryDiffCallback
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.diff.RecipeDiffCallback
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.viewholder.CategoryViewHolder
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.viewholder.RecipeViewHolder

class RecipeAdapter(
    private val recipeDiffCallback: RecipeDiffCallback,
    private val onRecipeClicked: (Recipe) -> Unit
) : ListAdapter<Recipe, RecipeViewHolder>(recipeDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding =
            RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(parent.context, itemBinding) {
            onRecipeClicked(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}