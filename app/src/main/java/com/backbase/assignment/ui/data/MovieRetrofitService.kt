package com.backbase.assignment.ui.data

import com.backbase.assignment.ui.util.NoConnectivityException
import com.backbase.assignment.ui.util.isNetworkActive
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object MovieRetrofitService {

    const val FIRST_PAGE = 1

    private val client: OkHttpClient =
        OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor {
                if(!isNetworkActive) {
                    throw NoConnectivityException()
                }
                var original = it.request()
                val url = original.url().newBuilder()
                    .addQueryParameter("api_key", "fe2662a00a8a11e716310f36e24b26ac").build()
                original = original.newBuilder().url(url).build()
                it.proceed(original)
            }.build()

    private const val movieBaseUrl = "https://api.themoviedb.org/3/"

    private val moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(movieBaseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}