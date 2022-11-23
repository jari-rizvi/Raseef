package com.teamx.raseef.ui.fragments.reviewlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamx.raseef.baseclasses.BaseViewModel
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.data.remote.reporitory.MainRepository
import com.teamx.raseef.dataclasses.allreviews.AllReviews
import com.teamx.raseef.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewListViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

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


}