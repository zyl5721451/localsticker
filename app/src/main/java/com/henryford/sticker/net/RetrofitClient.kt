package com.henryford.sticker.net

import android.text.TextUtils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private lateinit var defaultClient:Retrofit
    const val BASE_HOST = "https://firebase.google.com"


    private fun getOkHttpClient():OkHttpClient{
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        return builder.build()
    }

    fun getDefaultRetrofit(): Retrofit {
        if (defaultClient == null) {
            defaultClient = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(getOkHttpClient()).build()
        }
        return defaultClient
    }
}