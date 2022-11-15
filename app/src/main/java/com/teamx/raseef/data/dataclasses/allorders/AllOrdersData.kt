package com.teamx.raseef.dataclasses.allorders

import androidx.annotation.Keep

 
@Keep
data class AllOrdersData(
    val docs: List<DocX>,
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