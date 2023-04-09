package com.art.studio.weather.data.model.locationModel

data class Location(
    val Version: Int,
    val Key: String,
    val Type: String,
    val Rank: Int,
    val LocalizedName: String,
    val EnglishName: String,
    val PrimaryPostalCode: String?,
    val Region: com.art.studio.weather.data.model.locationModel.Region,
    val Country: com.art.studio.weather.data.model.locationModel.Country,
    val AdministrativeArea: com.art.studio.weather.data.model.locationModel.AdministrativeArea,
    val TimeZone: com.art.studio.weather.data.model.locationModel.TimeZone,
    val GeoPosition: com.art.studio.weather.data.model.locationModel.GeoPosition,
    val IsAlias: Boolean,
    val SupplementalAdminAreas: List<Any>,
    val DataSets: List<String>
)
