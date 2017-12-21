package com.meuapttestemobile.data

import com.google.gson.GsonBuilder
import com.meuapttestemobile.BuildConfig
import com.meuapttestemobile.data.remote.APIService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class APIModule {

    @Provides
    @Singleton
    fun provideAPIService(): APIService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .baseUrl(BuildConfig.API_URL)
                .build()

        return retrofit.create(APIService::class.java)
    }
}
