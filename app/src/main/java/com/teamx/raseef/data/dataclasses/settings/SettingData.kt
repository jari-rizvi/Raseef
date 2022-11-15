package com.teamx.raseef.dataclasses.settings

import androidx.annotation.Keep

 
@Keep
data class SettingData(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val options: Options,
    val updatedAt: String
)