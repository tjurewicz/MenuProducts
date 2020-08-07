package com.example.goustoproducts.ui.products

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.goustoproducts.MainActivity
import com.example.goustoproducts.R
import com.example.goustoproducts.api.products.model.ProductData
import kotlinx.android.synthetic.main.product_list_item.view.*
import java.io.InputStream
import java.net.URL


class ProductListAdapter(
    private val productList: List<ProductData>,
    private val activity: MainActivity
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_item, parent, false).run {
                ViewHolder(this)
            }
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = productList[position].run {
        setClickListeners(holder, this)
        holder.updateProductTitle(this.title)
        holder.updateProductPrice(this.listPrice)
        if (this.images.imageDetails != null && this.images.imageDetails.src.isNotEmpty()) {
            holder.updateImage(this.images.imageDetails.src)
        }
    }

    private fun setClickListeners(holder: ViewHolder, model: ProductData) {
        holder.itemView.setOnClickListener { holder.openProductPage(model, activity) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal fun updateImage(src: String) {
            val imageView = itemView.findViewById<ImageView>(R.id.product_image)
            if (src.isNotEmpty()) {
                DownloadImageTask(imageView).execute(src)
            } else
                imageView.setImageResource(R.drawable.ic_launcher_background)
        }

        internal fun updateProductTitle(title: String) {
            itemView.product_title.text = title
        }

        internal fun updateProductPrice(price: String) {
            val priceWithPound = "£$price"
            itemView.product_price.text = priceWithPound
        }

        internal fun openProductPage(product: ProductData, activity: MainActivity) {
            val detailFragment = ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ProductDetailFragment.ARG_PRODUCT_ID, product)
                }
            }
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, detailFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}

class DownloadImageTask(var bmImage: ImageView) : AsyncTask<String?, Void?, Bitmap?>() {
    override fun doInBackground(vararg p0: String?): Bitmap? {
        val urldisplay = p0[0]
        var mIcon11: Bitmap? = null
        try {
            val stream: InputStream = URL(urldisplay).openStream()
            mIcon11 = BitmapFactory.decodeStream(stream)
        } catch (e: Exception) {
            println("Error $e.message")
            e.printStackTrace()
        }
        return mIcon11
    }

    override fun onPostExecute(result: Bitmap?) {
        bmImage.setImageBitmap(result)
    }

}

