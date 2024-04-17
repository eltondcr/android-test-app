package br.com.digio.androidtest.screens.main.callbacks

import androidx.recyclerview.widget.DiffUtil
import br.com.digio.androidtest.screens.main.models.Product

class ProductListDiffCallback constructor(
    private val oldList: List<Product>,
    private val newList: List<Product>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].name == newList[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        true
}
