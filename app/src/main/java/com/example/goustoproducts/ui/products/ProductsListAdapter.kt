package com.example.goustoproducts.ui.products

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.goustoproducts.MainActivity
import com.example.goustoproducts.R
import com.example.goustoproducts.api.products.model.Image
import com.example.goustoproducts.api.products.model.ProductInformation
import kotlinx.android.synthetic.main.product_list_item.view.*


class ProductListAdapter(private val productList: List<ProductInformation>, private val activity: MainActivity) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

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
        if (this.images.isNotEmpty()) {
            holder.updateImage(this.images)
        }
    }

    private fun setClickListeners(holder: ViewHolder, model: ProductInformation) {
        holder.itemView.setOnClickListener { holder.openProductPage(model, activity) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal fun updateImage(image: List<Image>) {
            val imageButtonId = itemView.findViewById<ImageButton>(R.id.product_image)
            if (image.isNotEmpty()) {
                val imageId = itemView.resources.getIdentifier(image[0].imageDetails.src, "drawable", itemView.context.packageName)
                if (imageId != 0)
                    imageButtonId.setImageResource(imageId)
            } else
                imageButtonId.setImageResource(R.drawable.ic_launcher_background)
        }

        internal fun updateProductTitle(title: String) {
            itemView.product_title.text = title
        }

        internal fun updateProductPrice(price: String) {
            val priceWithPound = "£$price"
            itemView.product_price.text = priceWithPound
        }

        internal fun openProductPage(product: ProductInformation, activity: MainActivity) {
            val detailFragment = ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ProductDetailFragment.ARG_PRODUCT_ID, product.id)
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
