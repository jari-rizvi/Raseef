package com.teamx.raseef.dataclasses.productallreviews

import com.teamx.raseef.dataclasses.Profile

import androidx.annotation.Keep

 
@Keep
data class User(
    val __v: Int,
    val _id: String,
    val address: List<String>,
    val createdAt: String,
    val email: String,
    val is_active: Boolean,
    val name: String,
    val password: String,
    val profile: Profile,
    val roles: List<String>,
    val shops: List<Any>,
    val updatedAt: String
)