package com.example.payhilt.data.dataclasses.stripe

data class GetCardsStripe(
    val `data`: List<Any>,
    val has_more: Boolean,
    val `object`: String,
    val url: String
)