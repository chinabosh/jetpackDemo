package com.bosh.jetpackdemo.di

import com.bosh.jetpackdemo.di.qualifier.CommonRetrofit
import com.bosh.jetpackdemo.di.qualifier.OilRetrofit
import com.bosh.jetpackdemo.net.OilService
import com.bosh.jetpackdemo.net.ServiceManager
import com.bosh.jetpackdemo.net.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author lzq
 * @date  2022/2/18
 */
@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Provides
    @Singleton
    fun providerUserService(@CommonRetrofit retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun providerServiceManager(userService: UserService, oilService: OilService): ServiceManager {
        return ServiceManager(userService, oilService)
    }

    @Provides
    @Singleton
    fun providerOilService(@OilRetrofit retrofit: Retrofit): OilService {
        return retrofit.create(OilService::class.java)
    }
}