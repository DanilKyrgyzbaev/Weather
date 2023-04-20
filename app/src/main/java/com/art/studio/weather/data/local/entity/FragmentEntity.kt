package com.art.studio.weather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fragment_table")
data class FragmentEntity(
    @PrimaryKey
    val id: Int,
    val localityTv: String,
    val dateTv: String,
    val phraseTv: String,
    val tempTv: String,
    val realFeelGetTv: String,
    val windGustsGetTv: String,
    val windGetTv: String
)
