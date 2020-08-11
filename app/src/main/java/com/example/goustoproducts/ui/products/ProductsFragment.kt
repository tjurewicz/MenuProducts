package com.example.goustoproducts.ui.products

import android.os.Bundle
import android.provider.Contacts
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.goustoproducts.MainActivity
import com.example.goustoproducts.R
import com.example.goustoproducts.api.products.model.ProductData
import com.example.goustoproducts.database.AppDatabase
import kotlinx.android.synthetic.main.product_detail_fragment.*
import kotlinx.android.synthetic.main.product_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsFragment : Fragment() {

    private val viewModel: ProductsViewModel by viewModel()

    private lateinit var listAdapter: ProductListAdapter
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.product_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDatabase()
        setupSearchTextListener()
        getProducts()
    }

    private fun getProducts() {
        if (viewModel.products == null)
            viewModel.getProducts()
                .subscribe({
                    setupRecyclerView(it.data)
                    if (appDatabase.productDao().getAll().isEmpty()) {
                        viewModel.populateDatabase(appDatabase.productDao(), it.data)
                    }
                }, { handleError(appDatabase) })
        else {
            setupRecyclerView(viewModel.products!!)
        }
    }

    private fun setupSearchTextListener() {
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

    private fun setupDatabase() {
        appDatabase = Room.databaseBuilder(requireActivity().applicationContext, AppDatabase::class.java, "gousto-products")
            .allowMainThreadQueries()
            .build()
    }

    private fun handleError(appDatabase: AppDatabase) {
        if (appDatabase.productDao().getAll().isEmpty()) {
            Toast.makeText(context, "Failed to retrieve data from server and no data on device", Toast.LENGTH_LONG).show()
        } else {
            val data = getProductDataFromDatabase()
            setupRecyclerView(data)
        }
    }

    private fun getProductDataFromDatabase(): ArrayList<ProductData> {
        val dbProductData = viewModel.getProductsFromDatabase(appDatabase.productDao())
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
        return data
    }

    private fun setupRecyclerView(data: List<ProductData>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.product_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this.context)
        listAdapter = ProductListAdapter(data, this.activity as MainActivity)
        recyclerView?.adapter = listAdapter
    }
}
