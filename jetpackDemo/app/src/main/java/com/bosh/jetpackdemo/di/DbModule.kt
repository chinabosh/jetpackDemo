package com.bosh.jetpackdemo.di

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import com.bosh.jetpackdemo.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author lzq
 * @date  2022/2/23
 */
@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun providerDatabase(application: Application) : AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "app")
            .fallbackToDestructiveMigration()
            .addMigrations(migration_1_2)
            .build()
    }

    private val migration_1_2 = Migration(1,2) {
        //升级操作
//        it.execSQL("")
    }
}