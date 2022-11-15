package com.teamx.raseef.dataclasses.allorders

import androidx.annotation.Keep

 
@Keep
data class ProductX(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val has_reviewed: Boolean,
    val order_quantity: Int,
    val product_id: ProductIdX,
    val subtotal: Int,
    val unit_price: Int,
    val updatedAt: String,
    val variation_option_id: String
)