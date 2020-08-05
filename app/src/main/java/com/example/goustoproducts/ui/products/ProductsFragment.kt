package com.example.goustoproducts.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.goustoproducts.R
import kotlinx.android.synthetic.main.product_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception


class ProductsFragment : Fragment() {

    private val viewModel: ProductsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.product_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            viewModel.requestProducts()
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }

    }


    companion object {
        fun newInstance() = ProductsFragment()
    }

}
