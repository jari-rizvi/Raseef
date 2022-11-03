package com.teamx.raseef.ui.fragments.shopHomePage

import com.teamx.raseef.baseclasses.BaseViewModel
import com.teamx.raseef.data.remote.reporitory.MainRepository
import com.teamx.raseef.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopBySlugViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {
//
//    private val _shopBySlugResponse = MutableLiveData<Resource<ShopBySlugData>>()
//    val shopBySlugResponse: LiveData<Resource<ShopBySlugData>>
//        get() = _shopBySlugResponse
//
//    fun shopBySlug(slug: String) {
//        viewModelScope.launch {
//            _shopBySlugResponse.postValue(Resource.loading(null))
//            if (networkHelper.isNetworkConnected()) {
//                try {
//                    Log.d("87878787887","starta")
//
//                    mainRepository.shopBySlug(slug) .let {
//                        if (it.isSuccessful) {
//                            _shopBySlugResponse.postValue(Resource.success(it.body()!!))
//                            Log.d("87878787887",it.body()!!.toString())
//                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
//                            Log.d("87878787887","secoonnddd")
//
//                            _shopBySlugResponse.postValue(Resource.error(it.message(), null))
//                        } else {
//                            _shopBySlugResponse.postValue(Resource.error("Some thing went wrong", null))
//                            Log.d("87878787887","third")
//
//                        }
//                    }
//                } catch (e: Exception) {
//                    _shopBySlugResponse.postValue(Resource.error("${e.message}", null))
//                }
//            } else _shopBySlugResponse.postValue(Resource.error("No internet connection", null))
//        }
//    }
//
//
//    private val _productsByShopResponse = MutableLiveData<Resource<ShopProductsData>>()
//    val productsByShopResponse: LiveData<Resource<ShopProductsData>>
//        get() = _productsByShopResponse
//
//    fun productsByShopId(id: String) {
//        viewModelScope.launch {
//            _productsByShopResponse.postValue(Resource.loading(null))
//            if (networkHelper.isNetworkConnected()) {
//                try {
//                    Log.d("87878787887","starta")
//
//                    mainRepository.productsByShopId(id) .let {
//                        if (it.isSuccessful) {
//                            _productsByShopResponse.postValue(Resource.success(it.body()!!))
//                            Log.d("87878787887",it.body()!!.toString())
//                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
//                            Log.d("87878787887","secoonnddd")
//
//                            _productsByShopResponse.postValue(Resource.error(it.message(), null))
//                        } else {
//                            _productsByShopResponse.postValue(Resource.error("Some thing went wrong", null))
//                            Log.d("87878787887","third")
//
//                        }
//                    }
//                } catch (e: Exception) {
//                    _productsByShopResponse.postValue(Resource.error("${e.message}", null))
//                }
//            } else _productsByShopResponse.postValue(Resource.error("No internet connection", null))
//        }
//    }

}