package com.zopsmart.mymovies.api

import dagger.Provides
import okhttp3.Credentials
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

object RetrofitHelper {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

     const val API_KEY = "65db5aebb7dc29d77c7b00443904e829"

    val requestInterceptor  = Interceptor {  chain ->

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

    val okHttpClient = OkHttpClient.Builder()
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