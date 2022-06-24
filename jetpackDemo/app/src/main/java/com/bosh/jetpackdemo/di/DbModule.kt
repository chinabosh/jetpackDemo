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
            .addMigrations(migration_1_2, migration_2_3)
            .build()
    }

    private val migration_1_2 = Migration(1,2) {
        //升级操作
        it.execSQL("create table `oil_price` (" +
                "`id` INTEGER KEY AUTOINCREMENT NOT NULL, " +
                "`city` TEXT NOT NULL," +
                "`ninetyTwo` TEXT NOT NULL," +
                "`ninetyFive` TEXT NOT NULL," +
                "`ninetyEight` TEXT NOT NULL, " +
                "`zero` TEXT NOT NULL, " +
                "`time` TEXT NOT NULL, " +
                "PRIMARY KEY(`id`)" +
                ")")
        it.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_oil_price_city_time` ON `oil_price` (`city`, `time`)")
    }

    private val migration_2_3 = Migration(2, 3) {
        it.execSQL("CREATE TABLE IF NOT EXISTS `oil_history` (" +
                "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "`isInit` INTEGER NOT NULL, " +
                "`mile` INTEGER NOT NULL, " +
                "`OilLeft` REAL NOT NULL, " +
                "`addOil` REAL NOT NULL, " +
                "`addPrice` REAL NOT NULL, " +
                "`time` TEXT NOT NULL)")
    }
}