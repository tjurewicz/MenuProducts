package com.example.goustoproducts.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.goustoproducts.R
import com.example.goustoproducts.api.products.model.ProductData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_detail_fragment.*

class ProductDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.product_detail_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_button.setOnClickListener { requireActivity().onBackPressed() }
        val productData = requireArguments().get(ARG_PRODUCT) as ProductData
        setupViews(productData)
    }

    private fun setupViews(product: ProductData) {
        detail_title.text = product.title
        detail_description.text = product.description
        if (product.images?.imageDetails != null) {
            Picasso.get().load(product.images.imageDetails.src).into(detail_image)
        } else {
            detail_image.setImageResource(R.drawable.ic_image_not_found)
        }
    }

    companion object {
        const val ARG_PRODUCT = "product"
    }
}