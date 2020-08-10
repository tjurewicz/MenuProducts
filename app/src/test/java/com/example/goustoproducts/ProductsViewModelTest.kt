package com.example.goustoproducts

import com.example.goustoproducts.api.products.IProductsAPI
import com.example.goustoproducts.api.products.model.ProductsResponse
import com.example.goustoproducts.ui.products.ProductsViewModel
import com.nhaarman.mockitokotlin2.*
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class ProductsViewModelTest {


    private val mockResponse: ProductsResponse = mock()
    private lateinit var mockProductsApi: IProductsAPI
    private lateinit var productsViewModel: ProductsViewModel

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        mockProductsApi = mock {
            on { fetchProductList() } doReturn Single.just(mockResponse)
        }

        productsViewModel = ProductsViewModel(mockProductsApi)
    }

    @Test
    fun `Get products calls products API`() {

        productsViewModel.getProducts()
        verify(mockProductsApi).fetchProductList()
    }
}