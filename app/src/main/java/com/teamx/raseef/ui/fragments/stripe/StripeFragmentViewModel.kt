package com.teamx.raseef.ui.fragments.stripe

import com.teamx.raseef.baseclasses.BaseViewModel
import com.teamx.raseef.data.remote.reporitory.MainRepository
import com.teamx.raseef.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StripeFragmentViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {
    // TODO: Implement the ViewModel
}