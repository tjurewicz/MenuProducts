package com.example.goustoproducts.application

import com.example.goustoproducts.BuildConfig
import com.example.goustoproducts.api.products.IProductsAPI
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }

    single { get<Retrofit>().create(IProductsAPI::class.java)}
}