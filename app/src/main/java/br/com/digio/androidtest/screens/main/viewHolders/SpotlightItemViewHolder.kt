package br.com.digio.androidtest.screens.main.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import br.com.digio.androidtest.R
import br.com.digio.androidtest.screens.main.models.Spotlight
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class SpotlightItemViewHolder constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    private val imageView = itemView.findViewById<ImageView>(R.id.imgMainItem)
    private val progress = itemView.findViewById<ProgressBar>(R.id.progressImage)

    fun bind(product: Spotlight) {

        imageView.contentDescription = product.name
        progress.visibility = View.VISIBLE

        Picasso.get()
            .load(product.bannerURL)
            .error(R.drawable.ic_alert_circle)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    progress.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progress.visibility = View.GONE
                }
            })
    }
}
