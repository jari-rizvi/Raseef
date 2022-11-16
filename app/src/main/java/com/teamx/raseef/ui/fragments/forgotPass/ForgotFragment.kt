package com.teamx.rassef.ui.fragments.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import com.teamx.raseef.R
import com.teamx.raseef.BR
import com.teamx.raseef.SharedViewModel
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.databinding.FragmentForgotBinding
import com.teamx.raseef.databinding.FragmentLoginBinding
import com.teamx.raseef.ui.fragments.forgotPass.ForgotPassViewModel
import com.teamx.raseef.ui.fragments.login.LoginViewModel
import com.teamx.raseef.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException


@AndroidEntryPoint
class ForgotFragment() : BaseFragment<FragmentForgotBinding, ForgotPassViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_forgot
    override val viewModel: Class<ForgotPassViewModel>
        get() = ForgotPassViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


    private lateinit var options: NavOptions

    private var useremail: String? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        options = navOptions {
            anim {
                enter = R.anim.enter_from_left
                exit = R.anim.exit_to_left
                popEnter = R.anim.nav_default_pop_enter_anim
                popExit = R.anim.nav_default_pop_exit_anim
            }
        }


        mViewDataBinding.btnEmail.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.forgotEmailFragment, null, options)

        }

        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()
        }



    }




}

