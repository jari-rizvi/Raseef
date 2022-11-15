package com.teamx.raseef.dataclasses.products

import androidx.annotation.Keep

 
@Keep
data class Variation(
    val __v: Int,
    val _id: String,
    val attribute: String,
    val createdAt: String,
    val meta: String,
    val updatedAt: String,
    val value: String
)