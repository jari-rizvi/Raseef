package com.teamx.raseef.data.models.otpVerifyForgot

import androidx.annotation.Keep


@Keep
data class OtpVerifyForgotData(
    val message: String,
    val success: Boolean,
    val token: String
)