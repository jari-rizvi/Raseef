package com.teamx.raseef.constants

class NetworkCallPoints {
    companion object{
        const val LOGIN_PHONE ="token/phone";
        const val LOGIN ="token";
        const val SIGN_UP ="register/phone";
        const val HOME ="dashboard";
        const val OTP_VERIFY ="register/phone-verify";
        const val OTP_VERIFY_FORGOT ="verify-forget-password-token";
        const val RESEND_OTP_VERIFY ="register/resend-otp";
        const val RESET_PASS ="reset-password";
        const val FORGOT_PASS ="forget-password";
        const val SHOP_BY_SLUG ="shops/{slug}";
        const val PRODUCTS_BY_SHOP_ID ="products";
        const val PRODUCTS_BY_SLUG ="products/{slug}";



        //Reviews
        const val GET_ALL_REVIEWS = "reviews"

        //Orders
        const val ORDER_LIST = "orders"
        const val GET_ORDER_BY_ID = "orders/{id}"



        var TOKENER = ""
    }
}