package com.art.studio.weather.di.modules

import android.app.Application
import androidx.room.Room
import com.art.studio.weather.data.local.AppDatabase
import com.art.studio.weather.data.local.FragmentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDate(@ApplicationContext application: Application): AppDatabase{
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app-database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFragmentDao(appDatabase: AppDatabase): FragmentDao{
        return appDatabase.fragmentDao()
    }
}
