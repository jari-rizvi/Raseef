package com.teamx.raseef.data.dataclasses.allorders

data class CustomerX(
    val __v: Int,
    val _id: String,
    val contact: String,
    val contact_verified: Boolean,
    val createdAt: String,
    val email: String,
    val email_verified: Boolean,
    val gender: String,
    val has_subscribed: Boolean,
    val is_active: Boolean,
    val name: String,
    val password: String,
    val profile: ProfileX,
    val roles: List<String>,
    val shops: List<Any>,
    val stripe_customer_id: String,
    val updatedAt: String,
    val wallet: Wallet
)