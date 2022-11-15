package com.teamx.raseef.data.dataclasses.dashboard

data class RatingCount(
    val _id: String,
    val abusive_reports_count: Int,
    val createdAt: String,
    val negative_feedbacks_count: Int,
    val positive_feedbacks_count: Int,
    val rating: Int,
    val updatedAt: String,
    val user: String
)