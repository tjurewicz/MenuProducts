package com.example.goustoproducts.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.goustoproducts.MainActivity
import com.example.goustoproducts.R
import com.example.goustoproducts.api.products.model.ProductData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_list_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class ProductListAdapter(
    private val productList: List<ProductData>,
    private val activity: MainActivity
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    var productFilterList: List<ProductData> = ArrayList<ProductData>()

    init {
        productFilterList = productList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_item, parent, false).run {
                ViewHolder(this)
            }

    override fun getItemCount(): Int = productFilterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        productFilterList[position].run {
            setClickListeners(holder, this)
            this.title?.let { holder.updateProductTitle(it) }
            this.listPrice?.let { holder.updateProductPrice(it) }
            if (this.images?.imageDetails != null && this.images.imageDetails.src.isNotEmpty()) {
                holder.updateImage(this.images.imageDetails.src)
            } else {
                holder.updateImage("")
            }
        }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                productFilterList = if (charSearch.isEmpty()) {
                    productList
                } else {
                    productFilterList.filter { it.title?.toLowerCase(Locale.ROOT)?.contains(charSearch.toLowerCase(Locale.ROOT)) ?: false }
                }
                val filterResults = FilterResults()
                filterResults.values = productFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                productFilterList = results?.values as List<ProductData>
                notifyDataSetChanged()
            }
        }
    }

    private fun setClickListeners(holder: ViewHolder, model: ProductData) {
        holder.itemView.setOnClickListener { holder.openProductPage(model, activity) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal fun updateImage(src: String) {
            val imageView = itemView.findViewById<ImageView>(R.id.product_image)
            if (src.isNotEmpty()) {
                Picasso.get().load(src).into(imageView)
            } else
                imageView.setImageResource(R.drawable.ic_image_not_found)
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
                    putSerializable(ProductDetailFragment.ARG_PRODUCT, product)
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
