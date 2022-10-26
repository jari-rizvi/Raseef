package com.teamx.raseef.data.remote.reporitory

import com.google.gson.JsonObject
import com.teamx.raseef.data.local.db.AppDao
import com.teamx.raseef.data.remote.ApiService
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    localDataSource: AppDao
) {
    suspend fun login(@Body param : JsonObject) = apiService.login(param)


}