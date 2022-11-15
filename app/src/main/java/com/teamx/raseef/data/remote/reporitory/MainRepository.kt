package com.teamx.raseef.data.remote.reporitory

import com.google.gson.JsonObject
import com.teamx.raseef.constants.NetworkCallPoints
import com.teamx.raseef.data.local.db.AppDao
import com.teamx.raseef.data.models.SignUp.RegisterData
import com.teamx.raseef.data.remote.ApiService
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    localDataSource: AppDao
) {
    suspend fun login(@Body param: JsonObject) = apiService.login(param)

    suspend fun loginPhone(@Body param: JsonObject) = apiService.loginPhone(param)

    suspend fun signup(@Body param: JsonObject) = apiService.signup(param)

    suspend fun otpVerify(@Body param: JsonObject) = apiService.otpVerify(param)

    suspend fun otpVerifyForgot(@Body param: JsonObject) = apiService.otpVerifyForgot(param)

    suspend fun resendOtp(@Body param: JsonObject) = apiService.resendOtp(param)

    suspend fun forogtPass(@Body param: JsonObject) = apiService.forgotPass(param)

    suspend fun resetPass(@Body param: JsonObject) = apiService.resetPass(param)
}