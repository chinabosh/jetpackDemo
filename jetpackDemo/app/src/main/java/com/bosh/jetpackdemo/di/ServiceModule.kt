package com.bosh.jetpackdemo.di

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
    fun providerUserService(retrofit: Retrofit) : UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun providerServiceManager(userService: UserService) : ServiceManager {
        return ServiceManager(userService)
    }
}