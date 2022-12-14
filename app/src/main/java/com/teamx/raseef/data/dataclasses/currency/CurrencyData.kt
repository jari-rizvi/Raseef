package com.teamx.raseef.data.dataclasses.currency

import androidx.annotation.Keep

 
@Keep
data class CurrencyData(
    val base_code: String,
    val documentation: String,
    val provider: String,
    val rates: Rates,
    val result: String,
    val terms_of_use: String,
    val time_eol_unix: Int,
    val time_last_update_unix: Int,
    val time_last_update_utc: String,
    val time_next_update_unix: Int,
    val time_next_update_utc: String
)