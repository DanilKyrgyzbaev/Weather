package com.art.studio.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.art.studio.weather.data.local.entity.FragmentEntity

@Database(entities = [FragmentEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun fragmentDao(): FragmentDao
}