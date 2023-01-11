package com.teamx.raseef.data.dataclasses.allorders

data class SettingsX(
    val _id: String,
    val closes_at: String,
    val contact: String,
    val location: LocationX,
    val opens_at: String,
    val socials: List<List<Social>>,
    val website: String
)