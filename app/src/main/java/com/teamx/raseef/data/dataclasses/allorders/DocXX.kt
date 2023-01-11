package com.teamx.raseef.data.dataclasses.allorders

import com.teamx.raseef.dataclasses.allorders.ProductX

data class DocXX(
    val __v: Int,
    val _id: String,
    val amount: Int,
    val children: List<Any>,
    val createdAt: String,
    val customer: CustomerX,
    val customer_contact: String,
    val delivery_fee: Int,
    val discount: Int,
    val order_status: String,
    val paid_total: Int,
    val payment_gateway: String,
    val paypal_payment_id: String,
    val products: List<ProductX>,
    val riding_status: String,
    val sales_tax: Int,
    val shipping_address: ShippingAddressXX,
    val shop: ShopX,
    val status: StatusXX,
    val stripe_payment_id: String,
    val total: Int,
    val updatedAt: String,
    val used_points: Int,
    val user_riding: Boolean
)