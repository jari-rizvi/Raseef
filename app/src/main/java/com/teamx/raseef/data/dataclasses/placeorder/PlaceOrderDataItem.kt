package com.teamx.raseef.dataclasses.placeorder

import com.teamx.raseef.dataclasses.createorder.Product

import androidx.annotation.Keep


@Keep
data class PlaceOrderDataItem(
    val __v: Int,
    val _id: String,
    val amount: Int,
    val billing_address: BillingAddress,
    val children: List<Any>,
    val createdAt: String,
    val customer: String,
    val customer_contact: String,
    val delivery_fee: Int,
    val delivery_time: String,
    val discount: Int,
    val paid_total: Int,
    val payment_gateway: String,
    val products: List<Product>,
    val sales_tax: Int,
    val shipping_address: ShippingAddress,
    val shop: String,
    val status: Status,
    val total: Int,
    val updatedAt: String
)