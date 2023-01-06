package com.teamx.raseef.data.models.productBySlug

import androidx.annotation.Keep


@Keep
data class PaymentInfo(
    val _id: String,
    val account: String,
    val bank: String,
    val email: String,
    val name: String
)