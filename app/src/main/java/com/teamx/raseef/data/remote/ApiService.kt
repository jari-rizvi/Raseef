package com.teamx.raseef.data.remote


import com.google.gson.JsonObject
import com.teamx.raseef.constants.NetworkCallPoints
import com.teamx.raseef.data.models.ResetPass.ResetPassData
import com.teamx.raseef.data.models.SignIn.SignInResponse
import com.teamx.raseef.data.models.SignUp.RegisterData
import com.teamx.raseef.data.models.forgotPass.ForgotData
import com.teamx.raseef.data.models.otpVerify.OtpVerifyData
import com.teamx.raseef.data.models.otpVerifyForgot.OtpVerifyForgotData
import com.teamx.raseef.data.models.resendOtp.ResendOtpData
import com.teamx.raseef.dataclasses.login.LoginData
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST(NetworkCallPoints.LOGIN)
    suspend fun login(@Body params: JsonObject?): Response<LoginData>

    @POST(NetworkCallPoints.LOGIN_PHONE)
    suspend fun loginPhone(@Body params: JsonObject?): Response<LoginData>

    @POST(NetworkCallPoints.SIGN_UP)
    suspend fun signup(@Body params: JsonObject?): Response<RegisterData>

    @POST(NetworkCallPoints.OTP_VERIFY)
    suspend fun otpVerify(@Body params: JsonObject?): Response<OtpVerifyData>

    @POST(NetworkCallPoints.OTP_VERIFY_FORGOT)
    suspend fun otpVerifyForgot(@Body params: JsonObject?): Response<OtpVerifyForgotData>

    @POST(NetworkCallPoints.RESEND_OTP_VERIFY)
    suspend fun resendOtp(@Body params: JsonObject?): Response<ResendOtpData>

    @POST(NetworkCallPoints.FORGOT_PASS)
    suspend fun forgotPass(@Body params: JsonObject?): Response<ForgotData>

    @POST(NetworkCallPoints.RESET_PASS)
    suspend fun resetPass(@Body params: JsonObject?): Response<ResetPassData>
}