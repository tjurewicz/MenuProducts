package com.example.goustoproducts.application

import com.example.goustoproducts.BuildConfig
import com.example.goustoproducts.api.products.IProductsAPI
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.gson.GsonConverterFactory.create

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }

    single { get<Retrofit>().create(IProductsAPI::class.java)}
}