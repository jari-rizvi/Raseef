package com.teamx.raseef.dataclasses.createorder

import androidx.annotation.Keep


@Keep
data class CreateOrderData(
    val amount: Int,
    val billing_address: BillingAddress?,
    val customer_contact: String,
    val delivery_fee: Int,
    val delivery_time: String,
    val discount: Int,
    val paid_total: Int,
    val payment_gateway: String,
    val products: Array<Product>,
    val sales_tax: Int,
    val shipping_address: ShippingAddress?,
    val status: String,
    val total: Int
)