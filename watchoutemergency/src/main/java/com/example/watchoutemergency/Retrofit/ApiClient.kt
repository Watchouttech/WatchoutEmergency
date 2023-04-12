package com.example.watchoutemergencylibrary.ApiClient

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    fun getClient(url: String?): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient =  OkHttpClient.Builder().addInterceptor(interceptor).readTimeout(30, TimeUnit.SECONDS)
           .connectTimeout(30, TimeUnit.SECONDS).build()
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

}