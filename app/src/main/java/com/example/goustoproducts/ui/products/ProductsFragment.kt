package com.example.goustoproducts.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.goustoproducts.MainActivity
import com.example.goustoproducts.R
import com.example.goustoproducts.api.products.model.ProductData
import com.example.goustoproducts.database.AppDatabase
import kotlinx.android.synthetic.main.product_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsFragment : Fragment() {

    private val viewModel: ProductsViewModel by viewModel()

    private lateinit var listAdapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.product_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Room.databaseBuilder(
            requireActivity().applicationContext,
            AppDatabase::class.java, "gousto-products"
        )
            .allowMainThreadQueries()
            .build()

        if (viewModel.products == null)
            viewModel.getProducts()
                .subscribe({
                    setupRecyclerView(it.data)
                    if (db.productDao().getAll().isEmpty()) {
                        viewModel.populateDatabase(db.productDao(), it.data)
                    }
                },
                    {
                        val dbProductData = viewModel.getProductsFromDatabase(db.productDao())
                        val data = ArrayList<ProductData>()
                        dbProductData.forEach {
                            val productData = ProductData(
                                id = it.id,
                                title = it.title,
                                listPrice = it.listPrice,
                                description = it.description
                            )
                            data.add(productData)
                        }
                        setupRecyclerView(data)
                    })
        else {
            setupRecyclerView(viewModel.products!!)
        }

        product_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listAdapter.getFilter().filter(newText)
                return false
            }
        })
    }

    private fun setupRecyclerView(data: List<ProductData>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.product_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this.context)
        listAdapter = ProductListAdapter(data, this.activity as MainActivity)
        recyclerView?.adapter = listAdapter
    }
}
