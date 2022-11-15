package com.teamx.raseef.dataclasses.allorders

import androidx.annotation.Keep

 
@Keep
data class Customer(
    val __v: Int,
    val _id: String,
    val address: List<String>,
    val contact: String,
    val contact_verified: Boolean,
    val createdAt: String,
    val email: String,
    val email_verified: Boolean,
    val is_active: Boolean,
    val managed_shop: String,
    val name: String,
    val password: String,
    val profile: Profile,
    val roles: List<String>,
    val shops: List<String>,
    val updatedAt: String
)