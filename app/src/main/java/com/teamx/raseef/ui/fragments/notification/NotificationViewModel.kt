package com.teamx.raseef.ui.fragments.Home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamx.raseef.baseclasses.BaseViewModel
import com.teamx.raseef.data.dataclasses.dashboard.DashboardData
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.data.remote.reporitory.MainRepository
import com.teamx.raseef.dataclasses.notification.NotificationData
import com.teamx.raseef.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _notificationResponse = MutableLiveData<Resource<NotificationData>>()
    val notificationResponse: LiveData<Resource<NotificationData>> get() = _notificationResponse

    fun notification() {
        viewModelScope.launch {
            _notificationResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    Log.d("87878787887", "starta")

                    mainRepository.notification().let {
                        if (it.isSuccessful) {
                            _notificationResponse.postValue(Resource.success(it.body()!!))
                            Log.d("87878787887", it.body()!!.toString())
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
                            Log.d("87878787887", "secoonnddd")

                            _notificationResponse.postValue(Resource.error(it.message(), null))
                        } else {
                            _notificationResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong",
                                    null
                                )
                            )
                            Log.d("87878787887", "third")

                        }
                    }
                } catch (e: Exception) {
                    _notificationResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _notificationResponse.postValue(Resource.error("No internet connection", null))
        }
    }

}