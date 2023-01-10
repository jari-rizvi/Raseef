package com.teamx.raseef.ui.fragments.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamx.raseef.baseclasses.BaseViewModel
import com.teamx.raseef.data.dataclasses.dashboard.DashboardData
import com.teamx.raseef.data.local.dbModel.CartTable
import com.teamx.raseef.data.models.MusicModel
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.data.remote.reporitory.MainRepository
import com.teamx.raseef.dataclasses.allreviews.AllReviews
import com.teamx.raseef.utils.NetworkHelper
import com.teamx.raseef.data.models.productBySlug.ProductBySlugData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class ProductPreviewViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _productPreviewResponse = MutableLiveData<Resource<ProductBySlugData>>()
    val productPreviewResponse: LiveData<Resource<ProductBySlugData>>
        get() = _productPreviewResponse

    fun productPreview(slug: String) {
        viewModelScope.launch {
            _productPreviewResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.productsBySlug(slug).let {
                        if (it.isSuccessful) {
                            _productPreviewResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400 || it.code() == 422) {
                            val jsonObj = JSONObject(it.errorBody()!!.charStream().readText())
                            _productPreviewResponse.postValue(Resource.error(jsonObj.getString("message")))
                        } else {
                            _productPreviewResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong",
                                    null
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    _productPreviewResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _productPreviewResponse.postValue(Resource.error("No internet connection", null))
        }
    }


    private val _reviewListResponse = MutableLiveData<Resource<AllReviews>>()
    val reviewListResponse: LiveData<Resource<AllReviews>>
        get() = _reviewListResponse

    fun getReviewList(slug: String, page: Int, limit: Int) {
        viewModelScope.launch {
            _reviewListResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.getRatingList(/*id, page, limit*/).let {
                        if (it.isSuccessful) {
                            _reviewListResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
                            _reviewListResponse.postValue(Resource.error(it.message(), null))
                        } else {
                            _reviewListResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong",
                                    null
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    _reviewListResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _reviewListResponse.postValue(Resource.error("No internet connection", null))
        }
    }


    private val _homeResponse = MutableLiveData<Resource<DashboardData>>()
    val homeResponse: LiveData<Resource<DashboardData>>
        get() = _homeResponse

    fun home() {
        viewModelScope.launch {
            _homeResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    Log.d("87878787887","starta")

                    mainRepository.home() .let {
                        if (it.isSuccessful) {
                            _homeResponse.postValue(Resource.success(it.body()!!))
                            Log.d("87878787887",it.body()!!.toString())
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
                            Log.d("87878787887","secoonnddd")

                            _homeResponse.postValue(Resource.error(it.message(), null))
                        } else {
                            _homeResponse.postValue(Resource.error("Some thing went wrong", null))
                            Log.d("87878787887","third")

                        }
                    }
                } catch (e: Exception) {
                    _homeResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _homeResponse.postValue(Resource.error("No internet connection", null))
        }
    }

    fun insertCartProduct(cartTable: MusicModel) {

        Log.d("TAG", "getCarts:1 ")
        viewModelScope.launch(Dispatchers.IO) {
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.insertCartProduct(cartTable)
                } catch (e: Exception) {
                    Log.d("TAG", "getCarts:3${e.printStackTrace()} ")
                }
            } else {

            }

        }
    }
}