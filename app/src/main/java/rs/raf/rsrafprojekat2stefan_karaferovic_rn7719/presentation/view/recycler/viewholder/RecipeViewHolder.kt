package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Category
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

    fun bind(category: Category) {
        itemBinding.categoryTv.text = category.title
        Glide.with(context).load(category.imageUrl).circleCrop().into(itemBinding.categoryIv)
    }
}