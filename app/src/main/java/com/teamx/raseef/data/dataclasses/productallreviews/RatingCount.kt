package com.teamx.raseef.dataclasses.productallreviews

import androidx.annotation.Keep


@Keep
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