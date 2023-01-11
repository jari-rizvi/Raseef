package com.teamx.raseef.data.dataclasses.allorders

data class OrderAllData(
    val docs: List<DocXX>,
    val hasNextPage: Boolean,
    val hasPrevPage: Boolean,
    val limit: Int,
    val nextPage: Int,
    val page: Int,
    val pagingCounter: Int,
    val prevPage: Any,
    val totalDocs: Int,
    val totalPages: Int
)