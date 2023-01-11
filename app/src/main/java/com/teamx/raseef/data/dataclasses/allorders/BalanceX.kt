package com.teamx.raseef.data.dataclasses.allorders

data class BalanceX(
    val _id: String,
    val admin_commission_rate: Int,
    val current_balance: Int,
    val payment_info: PaymentInfoX,
    val total_earnings: Int,
    val withdrawn_amount: Int
)