package com.example.goustoproducts

import com.example.goustoproducts.api.products.IProductsAPI
import com.example.goustoproducts.api.products.model.ProductData
import com.example.goustoproducts.api.products.model.ProductsResponse
import com.example.goustoproducts.database.DBProductData
import com.example.goustoproducts.database.IProductDataDao
import com.example.goustoproducts.ui.products.ProductsViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductsViewModelTest {

    private val mockResponse: ProductsResponse = mock()
    private val mockDao: IProductDataDao = mock()
    private lateinit var mockProductsApi: IProductsAPI
    private lateinit var productsViewModel: ProductsViewModel
    private val mockProductData = ProductData(id = "1", title = "Ocean man", listPrice = "9.99", description = "Ocean man, take me by the hand, lead me to the land that you understand")
    private val mockDbProductData = DBProductData(id = "1", title = "Ocean man", price = "9.99", description = "Ocean man, take me by the hand, lead me to the land that you understand")


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

    @Test
    fun `Validate that VM inserts the data into the database`() {

        productsViewModel.populateDatabase(mockDao, listOf(mockProductData))
        verify(mockDao).insertAll(mockDbProductData)
    }

    @Test
    fun `Validate that getAll from DB returns values`() {
        whenever(mockDao.getAll()).thenReturn(listOf(mockDbProductData))

        val returnedList = productsViewModel.getProductsFromDatabase(mockDao)
        verify(mockDao).getAll()

        assertEquals(mockProductData, returnedList[0])
    }
}