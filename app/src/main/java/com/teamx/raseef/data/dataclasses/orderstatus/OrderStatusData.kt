package com.teamx.raseef.dataclasses.orderstatus

import androidx.annotation.Keep

 
@Keep
data class OrderStatusData(
    val __v: Int,
    val _id: String,
    val color: String,
    val name: String,
    val serial: Int
)