package com.teamx.raseef.data.dataclasses.dashboard

data class LocationX(
    val _id: String,
    val city: String,
    val coordinates: List<Double>,
    val country: String,
    val formattedAddress: String,
    val lat: Double,
    val lng: Double,
    val state: String,
    val type: String,
    val zip: String
)