package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Category

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.title == newItem.title
                && oldItem.imageUrl == newItem.imageUrl
    }
}