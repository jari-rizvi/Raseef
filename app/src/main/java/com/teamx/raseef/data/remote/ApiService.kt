package com.teamx.raseef.data.remote


import com.google.gson.JsonObject
import com.teamx.raseef.constants.NetworkCallPoints
import com.teamx.raseef.data.models.SignIn.SignInResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST(NetworkCallPoints.LOGIN)
    suspend fun login(@Body params: JsonObject?): Response<SignInResponse>
}