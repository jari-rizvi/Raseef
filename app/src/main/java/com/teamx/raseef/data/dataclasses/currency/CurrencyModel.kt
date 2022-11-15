package com.teamx.raseef.data.dataclasses.currency

import androidx.annotation.Keep


@Keep
data class CurrencyModel(
    val name: String,
    val rate: Double,
    var isChecked : Boolean

)