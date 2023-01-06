package com.teamx.raseef.data.remote


import com.google.gson.JsonObject
import com.teamx.raseef.constants.NetworkCallPoints
import com.teamx.raseef.constants.NetworkCallPoints.Companion.TOKENER
import com.teamx.raseef.data.dataclasses.dashboard.DashboardData
import com.teamx.raseef.data.models.ResetPass.ResetPassData
import com.teamx.raseef.data.models.SignUp.RegisterData
import com.teamx.raseef.data.models.forgotPass.ForgotData
import com.teamx.raseef.data.models.otpVerify.OtpVerifyData
import com.teamx.raseef.data.models.otpVerifyForgot.OtpVerifyForgotData
import com.teamx.raseef.data.models.resendOtp.ResendOtpData
import com.teamx.raseef.dataclasses.allorders.AllOrdersData
import com.teamx.raseef.dataclasses.allreviews.AllReviews
import com.teamx.raseef.dataclasses.login.LoginData
import com.teamx.raseef.data.models.productBySlug.ProductBySlugData
import com.teamx.raseef.data.models.productsShop.ShopProductsData
import com.teamx.raseef.data.models.shopBySlug.ShopBySlugData
import com.teamx.raseef.dataclasses.profile.ProfileData
import com.teamx.raseef.dataclasses.profile.ProfileDataX
import com.teamx.raseef.dataclasses.profile.UploadModelData
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    //Get Post Update Delete

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

    @GET(NetworkCallPoints.SHOP_BY_SLUG)
    suspend fun shopBySlug(
        @Path("slug") slug: String
    ): Response<ShopBySlugData>

    @GET(NetworkCallPoints.PRODUCTS_BY_SHOP_ID)
    suspend fun productsByShopID(
        @Query("shop") id: String
    ): Response<ShopProductsData>

    @GET(NetworkCallPoints.PRODUCTS_BY_SLUG)
    suspend fun productsBySlug(
        @Path("slug") slug: String,
        @Header("Authorization") basicCredentials: String = "Bearer $TOKENER"
    ): Response<ProductBySlugData>

    @Multipart
    @POST(NetworkCallPoints.UPLOAD_ATTACH)
    suspend fun uploadAttachment(@Part filePart: MultipartBody.Part): Response<UploadModelData>


    @GET(NetworkCallPoints.PROFILE_USER)
    suspend fun editProfile(
        @Header("Authorization") basicCredentials: String = "Bearer $TOKENER"
    ): Response<ProfileData>

    @PUT(NetworkCallPoints.PROFILE_USER)
    suspend fun updateProfile(
        @Body params: JsonObject?,
        @Header("Authorization") basicCredentials: String = "Bearer $TOKENER"
    ): Response<ProfileData>

    @GET(NetworkCallPoints.GET_ALL_REVIEWS)
    suspend fun getRatingList(
        /*       @Path("id") id: String,
               @Query("page") page: Int,
               @Query("limit") limit: Int,*/
        @Header("Authorization") basicCredentials: String = "Bearer $TOKENER"
    ): Response<AllReviews>

    @GET(NetworkCallPoints.HOME)
    suspend fun home(): Response<DashboardData>

    @GET(NetworkCallPoints.ORDER_LIST)
    suspend fun getOrders(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Header("Authorization") basicCredentials: String = "Bearer $TOKENER"
    ): Response<AllOrdersData>
}