package com.art.studio.weather.data.api.model.locationModel

data class Location(
    val Version: Int,
    val Key: String,
    val Type: String,
    val Rank: Int,
    val LocalizedName: String,
    val EnglishName: String,
    val PrimaryPostalCode: String?,
    val Region: Region,
    val Country: Country,
    val AdministrativeArea: AdministrativeArea,
    val TimeZone: TimeZone,
    val GeoPosition: GeoPosition,
    val IsAlias: Boolean,
    val SupplementalAdminAreas: List<Any>,
    val DataSets: List<String>
)
