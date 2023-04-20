package com.art.studio.weather.domain.usecases

import androidx.lifecycle.LiveData
import com.art.studio.weather.data.local.FragmentDao
import com.art.studio.weather.data.local.entity.FragmentEntity
import javax.inject.Inject

class RoomUseCase @Inject constructor(private val fragmentDao: FragmentDao) {
    suspend fun getAllFragmentEntity(): LiveData<List<FragmentEntity>> {
        return fragmentDao.getAllFragments()
    }
}