package com.teamx.raseef.dataclasses.notification

import androidx.annotation.Keep


@Keep
data class Doc(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val description: String,
    val notification_type: String,
    val order_id: String,
    val title: String,
    val unread: Boolean,
    val updatedAt: String,
    val user: String
)