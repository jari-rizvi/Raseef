package com.teamx.raseef.ui.fragments.editProfile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.teamx.raseef.baseclasses.BaseViewModel
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.data.remote.reporitory.MainRepository
import com.teamx.raseef.dataclasses.profile.ProfileData
import com.teamx.raseef.dataclasses.profile.ProfileDataX
import com.teamx.raseef.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val mainRepository: MainRepository, private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _editProfileResponse = MutableLiveData<Resource<ProfileData>>()

    val editProfileResponse: LiveData<Resource<ProfileData>> get() = _editProfileResponse

    fun editProfile(/*id: String, param: JsonObject*/) {
        viewModelScope.launch {
            _editProfileResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.editProfile(/*id, param*/).let {
                        if (it.isSuccessful) {
                            _editProfileResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400 || it.code() == 403) {
                            val jsonObj = it.errorBody()!!.charStream().readText()
                            _editProfileResponse.postValue(Resource.error(jsonObj))
                        } else {
                            _editProfileResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong", it.body()
                                )
                            )
                        }
                    }

                } catch (e: Exception) {
                    _editProfileResponse.postValue(Resource.error("${e.message}"))
                }
            } else _editProfileResponse.postValue(Resource.error("No internet connection", null))
        }
    }

    private val _updateProfileResponse = MutableLiveData<Resource<ProfileData>>()
    val updateProfileResponse: LiveData<Resource<ProfileData>>
        get() = _updateProfileResponse

    fun updateProfile(param: JsonObject) {
        viewModelScope.launch {
            _updateProfileResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.updateProfile(param).let {
                        if (it.isSuccessful) {
                            _updateProfileResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400 || it.code() == 403) {
                            val jsonObj = it.errorBody()!!.charStream().readText()
                            _updateProfileResponse.postValue(Resource.error(jsonObj))
                        } else {
                            _updateProfileResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong", it.body()
                                )
                            )
                        }
                    }

                } catch (e: Exception) {
                    _updateProfileResponse.postValue(Resource.error("${e.message}"))
                }
            } else _updateProfileResponse.postValue(Resource.error("No internet connection", null))
        }
    }

    private val _updateImgProfileResponse = MutableLiveData<Resource<List<String>>>()
    val updateImgProfileResponse: LiveData<Resource<List<String>>>
        get() = _updateImgProfileResponse

    fun updateImgProfile(param: MultipartBody.Part) {
        viewModelScope.launch {
            _updateImgProfileResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.updateImgProfile(param).let {

                        if (it.isSuccessful) {
                            _updateImgProfileResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400 || it.code() == 403) {
                            val jsonObj = it.errorBody()!!.charStream().readText()
                            _updateImgProfileResponse.postValue(Resource.error(jsonObj))
                        } else {
                            _updateImgProfileResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong", it.body()
                                )
                            )
                        }
                    }

                } catch (e: Exception) {
                    Log.d("1235", "updateImgProfile:${e.printStackTrace()} ")
                    _updateImgProfileResponse.postValue(Resource.error("${e.message}"))
                }
            } else _updateImgProfileResponse.postValue(
                Resource.error(
                    "No internet connection", null
                )
            )
        }
    }


    fun updateImgProfile(inputStream: InputStream, filename: String) {
        val part = MultipartBody.Part.createFormData(
            "attachment", filename, RequestBody.create(
//                "image/*".toMediaTypeOrNull(),
                "multipart/form-data".toMediaTypeOrNull(),
                inputStream.readBytes(),
            )
        )
        /*  viewModelScope.launch(Dispatchers.IO) {
              _updateImgProfileResponse.postValue(Resource.loading(null))
              if (networkHelper.isNetworkConnected()) {
                  try {
                      Log.d("1235", "updateImgProfile:122 ")
                      mainRepository.updateImgProfile(part).let {
                          if (it.isSuccessful) {
                              Log.d("1235", "updateImgProfile:1221 ")
                              _updateImgProfileResponse.postValue(Resource.success(it.body()!!))
                          } else if (it.code() == 500 || it.code() == 404 || it.code() == 400 || it.code() == 403) {
                              val jsonObj = it.errorBody()!!.charStream().readText()
                              _updateImgProfileResponse.postValue(Resource.error(jsonObj))
                              Log.d("1235", "updateImgProfile:123 ")
                          } else {
                              Log.d("1235", "updateImgProfile:124 ")
                              _updateImgProfileResponse.postValue(
                                  Resource.error(
                                      "Some thing went wrong",
                                      it.body()
                                  )
                              )
                          }
                      }

                  } catch (e: Exception) {
                      Log.d("1235", "updateImgProfile:${e.printStackTrace()} ")
                      _updateImgProfileResponse.postValue(Resource.error("${e.message}"))
                  }
              } else _updateImgProfileResponse.postValue(
                  Resource.error(
                      "No internet connection",
                      null
                  )
              )
          }*/
    }

}