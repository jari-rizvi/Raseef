package com.teamx.raseef.ui.fragments.singup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.teamx.raseef.baseclasses.BaseViewModel
import com.teamx.raseef.data.models.SignIn.SignInResponse
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.data.remote.reporitory.MainRepository
import com.teamx.raseef.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {





}