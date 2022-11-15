package com.teamx.raseef.data.dataclasses.coupouns

import androidx.annotation.Keep

 
@Keep
data class Doc(
    val __v: Int,
    val _id: String,
    val active_from: String,
    val amount: Int,
    val code: String,
    val createdAt: String,
    val description: String,
    val expire_at: String,
    val image: String,
    val is_valid: Boolean,
    val type: String,
    val updatedAt: String
)