package com.teamx.rassef.ui.fragments.login

import android.os.Bundle
import android.view.View
import com.teamx.raseef.R
import com.teamx.raseef.BR
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.databinding.FragmentSignupBinding
import com.teamx.raseef.ui.fragments.singup.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignupFragment() : BaseFragment<FragmentSignupBinding, SignupViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_signup
    override val viewModel: Class<SignupViewModel>
        get() = SignupViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }






}