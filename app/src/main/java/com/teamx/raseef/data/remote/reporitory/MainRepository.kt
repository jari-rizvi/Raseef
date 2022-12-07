package com.teamx.raseef.data.remote.reporitory

import com.google.gson.JsonObject
import com.teamx.raseef.data.local.db.AppDao
import com.teamx.raseef.data.local.dbModel.CartTable
import com.teamx.raseef.data.models.MusicModel
import com.teamx.raseef.data.remote.ApiService
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    var localDataSource: AppDao
) {
    suspend fun login(@Body param: JsonObject) = apiService.login(param)

    suspend fun loginPhone(@Body param: JsonObject) = apiService.loginPhone(param)

    suspend fun signup(@Body param: JsonObject) = apiService.signup(param)

    suspend fun otpVerify(@Body param: JsonObject) = apiService.otpVerify(param)

    suspend fun otpVerifyForgot(@Body param: JsonObject) = apiService.otpVerifyForgot(param)

    suspend fun resendOtp(@Body param: JsonObject) = apiService.resendOtp(param)

    suspend fun forogtPass(@Body param: JsonObject) = apiService.forgotPass(param)

    suspend fun resetPass(@Body param: JsonObject) = apiService.resetPass(param)

    suspend fun shopBySlug(@Path("slug") slug: String) = apiService.shopBySlug(slug)

    suspend fun productsByShopId(@Query("shop") id: String) = apiService.productsByShopID(id)

    suspend fun productsBySlug(@Path("slug") slug: String) = apiService.productsBySlug(slug)

    suspend fun updateProfile(@Body param: JsonObject) = apiService.updateProfile(param)

    suspend fun updateImgProfile(param: MultipartBody.Part) = apiService.uploadAttachment(param)



    suspend fun editProfile(/*@Query("id") id: String, @Body param: JsonObject*/) =
        apiService.editProfile(/*id,param*/)

    suspend fun getRatingList() = apiService.getRatingList(/*id, page, limit*/)

    suspend fun home() = apiService.home()

    suspend fun getOrderList(@Query("page") page: Int, @Query("limit") limit: Int) =
        apiService.getOrders(page, limit)


    suspend fun getAllProducts2(): List<MusicModel> = localDataSource.getAllProducts2()

    suspend fun insertCartProduct(cartTable: MusicModel) = localDataSource.insert(cartTable)
    suspend fun deleteCartItem(Id: Int) = localDataSource.deleteByProductId(Id)

}