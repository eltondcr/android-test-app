package br.com.digio.androidtest.screens.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.digio.androidtest.screens.main.models.Product
import br.com.digio.androidtest.screens.main.viewHolders.ProductItemViewHolder
import br.com.digio.androidtest.screens.main.callbacks.ProductListDiffCallback
import br.com.digio.androidtest.R

class ProductAdapter : RecyclerView.Adapter<ProductItemViewHolder>() {
    var products = emptyList<Product>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                ProductListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_products, parent, false)
        return ProductItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int =
        products.size
}