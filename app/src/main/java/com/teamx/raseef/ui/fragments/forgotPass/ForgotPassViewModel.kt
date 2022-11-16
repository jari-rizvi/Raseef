package com.teamx.raseef.ui.fragments.forgotPass

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.teamx.raseef.baseclasses.BaseViewModel
import com.teamx.raseef.data.models.forgotPass.ForgotData
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.data.remote.reporitory.MainRepository
import com.teamx.raseef.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class ForgotPassViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _forgotPassResponse = MutableLiveData<Resource<ForgotData>>()
    val forgotPassResponse: LiveData<Resource<ForgotData>>
        get() = _forgotPassResponse

    fun forgotPass(param : JsonObject) {
        viewModelScope.launch {
            _forgotPassResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.forogtPass(param) .let {
                        if (it.isSuccessful) {
                            _forgotPassResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400 || it.code() == 422) {
                            val jsonObj = JSONObject(it.errorBody()!!.charStream().readText())
                            _forgotPassResponse.postValue(Resource.error(jsonObj.getString("message")))
                        } else {
                            _forgotPassResponse.postValue(Resource.error("Some thing went wrong", null))
                        }
                    }
                } catch (e: Exception) {
                    _forgotPassResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _forgotPassResponse.postValue(Resource.error("No internet connection", null))
        }
    }


}