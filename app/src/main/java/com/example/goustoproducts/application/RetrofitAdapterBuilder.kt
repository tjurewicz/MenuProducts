package com.example.goustoproducts.application

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class RetrofitAdapterBuilder(val baseUrl: String, val gson: Gson) {

    fun newBuilder(baseUrl: String, gson: Gson) : RetrofitAdapterBuilder =
        RetrofitAdapterBuilder(
            baseUrl,
            gson
        )

    fun build() : Retrofit {
        val builder: OkHttpClient.Builder

        return Retrofit.Builder()
            .client(OkHttpClient())
    }

}