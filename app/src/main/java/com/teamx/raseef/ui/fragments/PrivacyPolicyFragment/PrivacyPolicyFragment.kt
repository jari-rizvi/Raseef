package com.teamx.raseef.ui.fragments.PrivacyPolicyFragment

import android.os.Bundle
import android.view.View
import com.teamx.raseef.R
import com.teamx.raseef.BR
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.databinding.FragmentPrivacyPolicyBinding
import com.teamx.raseef.ui.fragments.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrivacyPolicyFragment : BaseFragment<FragmentPrivacyPolicyBinding, LoginViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_privacy_policy
    override val viewModel: Class<LoginViewModel>
        get() = LoginViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()
        }

    }

}