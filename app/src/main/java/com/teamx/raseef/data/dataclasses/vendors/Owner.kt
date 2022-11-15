package com.teamx.raseef.dataclasses.vendors

import androidx.annotation.Keep

 
@Keep
data class Owner(
    val __v: Int,
    val _id: String,
    val address: List<Any>,
    val createdAt: String,
    val email: String,
    val is_active: Boolean,
    val managed_shop: String,
    val name: String,
    val password: String,
    val profile: Profile,
    val roles: List<String>,
    val shops: List<String>,
    val updatedAt: String
)