package com.zopsmart.mymovies.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitHelper {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private const val  API_KEY = "65db5aebb7dc29d77c7b00443904e829"

    private val requestInterceptor  = Interceptor { chain ->

        val url : HttpUrl =  chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val request : Request = chain.request()
            .newBuilder()
            .url(url)
            .build()


        return@Interceptor chain.proceed(request)

    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()



    fun getInstance() : Retrofit
    {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}