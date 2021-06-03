package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.recycler.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.models.Category
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.ActivityMainBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.CategoryItemBinding

class CategoryViewHolder(
    private val context: Context,
    private val itemBinding: CategoryItemBinding,
    onItemClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.root.setOnClickListener { onItemClicked(absoluteAdapterPosition) }
    }

    fun bind(category: Category) {
        itemBinding.categoryTv.text = category.title
        Glide.with(context).load(category.imageUrl).into(itemBinding.categoryIv)
    }
}
