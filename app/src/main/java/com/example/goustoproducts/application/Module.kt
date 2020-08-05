package com.example.goustoproducts.application

import com.example.goustoproducts.ui.products.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Products ViewModel
    viewModel { ProductsViewModel(get()) }
}