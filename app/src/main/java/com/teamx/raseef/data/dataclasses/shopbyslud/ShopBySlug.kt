package com.teamx.raseef.dataclasses.shopbyslud

import com.teamx.raseef.dataclasses.Address
import com.teamx.raseef.dataclasses.Balance

import androidx.annotation.Keep


@Keep
data class ShopBySlug(
    val __v: Int,
    val _id: String,
    val address: Address,
    val balance: Balance,
    val cover_image: String,
    val createdAt: String,
    val description: String,
    val is_active: Boolean,
    val logo: String,
    val name: String,
    val orders_count: Int,
    val owner: String,
    val products_count: Int,
    val settings: Settings,
    val slug: String,
    val staffs: List<Any>,
    val updatedAt: String
)