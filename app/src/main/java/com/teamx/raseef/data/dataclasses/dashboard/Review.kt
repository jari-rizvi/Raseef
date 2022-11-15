package com.teamx.raseef.data.dataclasses.dashboard

data class Review(
    val __v: Int,
    val _id: String,
    val abusive_reports_count: Int,
    val comment: String,
    val createdAt: String,
    val feedbacks: List<Feedback>,
    val negative_feedbacks_count: Int,
    val photos: List<String>,
    val positive_feedbacks_count: Int,
    val product: String,
    val rating: Int,
    val shop: String,
    val updatedAt: String,
    val user: String
)