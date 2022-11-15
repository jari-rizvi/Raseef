package com.teamx.raseef.dataclasses.feedback

import androidx.annotation.Keep

 
@Keep
data class User(
    val __v: Int,
    val _id: String,
    val address: List<Any>,
    val createdAt: String,
    val email: String,
    val is_active: Boolean,
    val name: String,
    val password: String,
    val roles: List<String>,
    val shops: List<Any>,
    val updatedAt: String
)