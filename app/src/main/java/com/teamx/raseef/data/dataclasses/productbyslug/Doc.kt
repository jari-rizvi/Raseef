package com.teamx.raseef.dataclasses.productbyslug

import androidx.annotation.Keep


@Keep
data class Doc(
    val category: Category,
    val products: List<Product>
)