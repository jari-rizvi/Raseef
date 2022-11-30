package com.teamx.raseef.data.models.productBySlug

import androidx.annotation.Keep


@Keep
data class Address(
    val _id: String,
    val city: String,
    val country: String,
    val state: String,
    val street_address: String,
    val zip: String
)