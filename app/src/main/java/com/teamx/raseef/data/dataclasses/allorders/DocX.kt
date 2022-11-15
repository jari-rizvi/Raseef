package com.teamx.raseef.dataclasses.allorders

import androidx.annotation.Keep


@Keep
data class DocX(
    val __v: Int,
    val _id: String,
    val amount: Int,
    val billing_address: BillingAddressX,
    val children: List<Any>,
    val createdAt: String,
    val customer: Customer,
    val customer_contact: String,
    val delivery_fee: Int,
    val delivery_time: String,
    val discount: Int,
    val paid_total: Double,
    val payment_gateway: String,
    val products: List<ProductX>,
    val sales_tax: Int,
    val shipping_address: ShippingAddressX,
    val shop: Shop,
    val status: StatusX,
    val total: Int,
    val updatedAt: String
)