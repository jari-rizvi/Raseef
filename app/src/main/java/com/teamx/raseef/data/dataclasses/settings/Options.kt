package com.teamx.raseef.dataclasses.settings

import androidx.annotation.Keep


@Keep
data class Options(
    val _id: String,
    val contactDetails: ContactDetails,
    val currency: String,
    val deliveryTime: List<DeliveryTime>,
    val logo: String,
    val minimumOrderAmount: Any,
    val seo: Seo,
    val siteSubtitle: String,
    val siteTitle: String
)