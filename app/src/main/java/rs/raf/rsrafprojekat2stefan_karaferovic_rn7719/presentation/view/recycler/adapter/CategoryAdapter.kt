package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Category
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.CategoryItemBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.diff.CategoryDiffCallback
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.viewholder.CategoryViewHolder

class CategoryAdapter(
    private val categoryDiffItemCallback: CategoryDiffCallback,
    private val onCategoryClicked: (Category) -> Unit
) : ListAdapter<Category, CategoryViewHolder>(categoryDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemBinding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(parent.context, itemBinding) {
            onCategoryClicked(getItem(it))
        }

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}