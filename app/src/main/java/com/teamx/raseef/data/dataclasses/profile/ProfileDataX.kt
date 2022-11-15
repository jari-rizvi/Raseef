package com.teamx.raseef.dataclasses.profile

import com.teamx.raseef.dataclasses.Profile

import androidx.annotation.Keep


@Keep
data class ProfileDataX(
    val __v: Int,
    val _id: String,
    val address: List<AddresX>,
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

@Keep
data class ProfileData(
    var __v: Int?,
    var _id: String?,
    var address: List<String>?,
    var createdAt: String?,
    var email: String?,
    var is_active: Boolean?,
    var name: String?,
    var password: String?,
    var profile: Profile?,
    var gender: String?,
    var roles: List<String>?,
    var shops: List<Any>?,
    var contact_verified: Boolean?,
    var contact: String?,
    var email_verified: Boolean?,
    var updatedAt: String
)

class UploadModelData : ArrayList<String>()