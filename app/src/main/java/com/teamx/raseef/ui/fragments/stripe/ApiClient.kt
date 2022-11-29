package com.teamx.raseef.ui.fragments.stripe

import com.teamx.raseef.constants.AppConstants.ApiConfiguration.Companion.BASE_URL
import com.teamx.raseef.constants.AppConstants.ApiConfiguration.Companion.BASE_URL2
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import okhttp3.*
import java.io.IOException

class ApiClient {
    private val httpClient = OkHttpClient()

    fun createPaymentIntent(
        paymentMethodType: String,
        completion: (paymentIntentClientSecret: String?, error: String?) -> Unit
    ) {

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestJson = """
            {
               "amount": 199,
               "paymentMethodId": "pm_card_visa",
               "confirm": false
            }
            """/*"""
            {
               "amount": 199,
               "paymentMethodId": "pm_card_visa",
               "confirm": false
            }
            """*/
        /* val requestJson = """
            {
                "currency":"usd",
                "paymentMethodType":"$paymentMethodType"
            }
            """*/
        val body = requestJson.toRequestBody(mediaType)
        val request = Request.Builder()
            .url("$BASE_URL2" + "stripe/charge")
//            .url("$BASE_URL"+"stripe/cards")
            .addHeader(
                "Authorization",
//                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoidXNhbWFAdGVhbXguZ2xvYmFsIiwicm9sZXMiOlsiY3VzdG9tZXIiXSwiaWF0IjoxNjY5MjE3ODQxLCJleHAiOjE2Njk4MjI2NDF9.bxm8ErqXqKkWKsmfVNzQSNPlqh0h9NjYtGq9tjy4Tco"
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoidXNhbWFAdGVhbXguZ2xvYmFsIiwicm9sZXMiOlsiY3VzdG9tZXIiXSwiaWF0IjoxNjY5Mjg4ODI4LCJleHAiOjE2Njk4OTM2Mjh9.SdwsycfIrvJDBkj1DCP36WK8A1biyQoyw9u497HsfKA"
            )
            .post(body)
            .build()
        httpClient.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    completion(null, "$e")
                }

                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful) {
                        completion(null, "$response")
                    } else {
                        val responseData = response.body?.string()
                        val responseJson = responseData?.let { JSONObject(it) } ?: JSONObject()

                        // The response from the server contains the PaymentIntent's client_secret
//                        val paymentIntentClientSecret: String ="pi_3M7L93G5yM3A5WD31fF4JY7e_secret_YsTgM0h5Vt0zlcqEoNIQ00sSn"
                        val paymentIntentClientSecret: String =responseJson.getString("client_secret")
//                        val paymentIntentClientSecret: String =
//                            responseJson.toString()/*getString("clientSecret")*/
                        completion(paymentIntentClientSecret, null)
                    }
                }
            })
    }
}
