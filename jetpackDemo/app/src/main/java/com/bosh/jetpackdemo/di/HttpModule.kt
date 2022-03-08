package com.bosh.jetpackdemo.di

import com.bosh.jetpackdemo.BuildConfig
import com.bosh.jetpackdemo.di.qualifier.CommonRetrofit
import com.bosh.jetpackdemo.di.qualifier.OilRetrofit
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author lzq
 * @date  2022/2/18
 */
@Module
@InstallIn(SingletonComponent::class)
object HttpModule {

    const val url = "https://www.baidu.com"

    @Provides
    @Singleton
    fun providerGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providerOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            callTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = when (BuildConfig.DEBUG) {
                    true -> HttpLoggingInterceptor.Level.BODY
                    false -> HttpLoggingInterceptor.Level.NONE
                }
            })
        }
            .build()
    }

    @CommonRetrofit
    @Provides
    @Singleton
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @OilRetrofit
    @Provides
    @Singleton
    fun providerOilRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://apis.juhe.cn/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}