package com.teamx.raseef.ui.fragments.createPass

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.teamx.raseef.baseclasses.BaseViewModel
import com.teamx.raseef.data.models.ResetPass.ResetPassData
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.data.remote.reporitory.MainRepository
import com.teamx.raseef.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateNewPassViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _resetPassResponse = MutableLiveData<Resource<ResetPassData>>()
    val resetPassResponse: LiveData<Resource<ResetPassData>>
        get() = _resetPassResponse

    fun resetPass(param: JsonObject) {
        viewModelScope.launch {
            _resetPassResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.resetPass(param).let {
                        if (it.isSuccessful) {
                            _resetPassResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
                            _resetPassResponse.postValue(Resource.error(it.message(), null))
                        } else {
                            _resetPassResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong",
                                    null
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    _resetPassResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _resetPassResponse.postValue(Resource.error("No internet connection", null))
        }
    }



}