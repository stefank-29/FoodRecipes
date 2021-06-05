package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Category
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Recipe

class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.title == newItem.title
                && oldItem.imageUrl == newItem.imageUrl
    }
}