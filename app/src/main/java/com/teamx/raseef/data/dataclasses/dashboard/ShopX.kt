package com.teamx.raseef.data.dataclasses.dashboard

data class ShopX(
    val __v: Int,
    val _id: String,
    val address: Address,
    val balance: BalanceXX,
    val cover_image: String,
    val createdAt: String,
    val description: String,
    val is_active: Boolean,
    val logo: String,
    val name: String,
    val orders_count: Int,
    val owner: String,
    val products_count: Int,
    val rating: Int,
    val settings: SettingsXXX,
    val slug: String,
    val staffs: List<Any>,
    val updatedAt: String
)