package com.teamx.raseef.data.dataclasses.dashboard

import com.teamx.raseef.dataclasses.productid.Attribute


data class Variation(
    val __v: Int,
    val _id: String,
    val attribute: String,
    val createdAt: String,
    val meta: String,
    val updatedAt: String,
    val value: String,
    var boolCheck: Boolean = false, var priceVariation: Float = 0f
)


data class VariationS(
    val __v: Int,
    val _id: String,
    val attribute: Attribute,
    val createdAt: String,
    val meta: String,
    val updatedAt: String,
    val value: String,
    var boolCheck: Boolean = false, var priceVariation: Float = 0f
)