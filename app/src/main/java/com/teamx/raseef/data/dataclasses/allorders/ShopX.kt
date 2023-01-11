package com.teamx.raseef.data.dataclasses.allorders

data class ShopX(
    val __v: Int,
    val _id: String,
    val address: Address,
    val balance: BalanceX,
    val cover_image: String,
    val createdAt: String,
    val deleted: Boolean,
    val description: String,
    val is_active: Boolean,
    val logo: String,
    val name: String,
    val orders_count: Int,
    val owner: String,
    val products_count: Int,
    val rating: Int,
    val settings: SettingsX,
    val slug: String,
    val staffs: List<String>,
    val updatedAt: String
)