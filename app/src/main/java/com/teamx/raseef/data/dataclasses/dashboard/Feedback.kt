package com.teamx.raseef.data.dataclasses.dashboard

data class Feedback(
    val _id: String,
    val createdAt: String,
    val negative: Boolean,
    val positive: Boolean,
    val updatedAt: String,
    val user: String
)