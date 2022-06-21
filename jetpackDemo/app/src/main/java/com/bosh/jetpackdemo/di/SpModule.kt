package com.bosh.jetpackdemo.di

import com.bosh.jetpackdemo.utils.SpUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author lzq
 * @date  2022/6/21
 */
@Module
@InstallIn(SingletonComponent::class)
object SpModule {

    @Provides
    @Singleton
    fun providerSharedPrefence(): SpUtils {
        return SpUtils.instance!!
    }
}