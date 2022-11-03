package com.teamx.raseef.ui.fragments.Home

import com.teamx.raseef.baseclasses.BaseViewModel
import com.teamx.raseef.data.remote.reporitory.MainRepository
import com.teamx.raseef.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

//    private val _homeResponse = MutableLiveData<Resource<DashboardData>>()
//    val homeResponse: LiveData<Resource<DashboardData>>
//        get() = _homeResponse
//
//    fun home() {
//        viewModelScope.launch {
//            _homeResponse.postValue(Resource.loading(null))
//            if (networkHelper.isNetworkConnected()) {
//                try {
//                    Log.d("87878787887","starta")
//
//                    mainRepository.home() .let {
//                        if (it.isSuccessful) {
//                            _homeResponse.postValue(Resource.success(it.body()!!))
//                            Log.d("87878787887",it.body()!!.toString())
//                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
//                            Log.d("87878787887","secoonnddd")
//
//                            _homeResponse.postValue(Resource.error(it.message(), null))
//                        } else {
//                            _homeResponse.postValue(Resource.error("Some thing went wrong", null))
//                            Log.d("87878787887","third")
//
//                        }
//                    }
//                } catch (e: Exception) {
//                    _homeResponse.postValue(Resource.error("${e.message}", null))
//                }
//            } else _homeResponse.postValue(Resource.error("No internet connection", null))
//        }
//    }

}