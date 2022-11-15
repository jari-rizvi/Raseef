package com.teamx.raseef.dataclasses.productid

import androidx.annotation.Keep


@Keep
data class Tag(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val details: String,
    val icon: String,
    val image: String,
    val name: String,
    val products: List<Any>,
    val slug: String,
    val type: String,
    val updatedAt: String
)