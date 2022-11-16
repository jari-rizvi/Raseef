package com.teamx.rassef.ui.fragments.login

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.core.content.ContentProviderCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.facebook.appevents.codeless.internal.ViewHierarchy.setOnClickListener
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import com.teamx.raseef.R
import com.teamx.raseef.BR
import com.teamx.raseef.SharedViewModel
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.databinding.FragmentForgotBinding
import com.teamx.raseef.databinding.FragmentForgotEmailBinding
import com.teamx.raseef.databinding.FragmentLoginBinding
import com.teamx.raseef.ui.fragments.forgotPass.ForgotPassViewModel
import com.teamx.raseef.ui.fragments.login.LoginViewModel
import com.teamx.raseef.utils.DialogHelperClass
import com.teamx.raseef.utils.snackbar
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException


@AndroidEntryPoint
class ForgotEmailFragment() : BaseFragment<FragmentForgotEmailBinding, ForgotPassViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_forgot_email
    override val viewModel: Class<ForgotPassViewModel>
        get() = ForgotPassViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


    private lateinit var options: NavOptions

    private var userEmail: String? = null


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

        mViewDataBinding.btnVerify.setOnClickListener {
            isValidate()
        }


        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()
        }

    }

    private fun initialization() {
        userEmail = mViewDataBinding.usermail.getText().toString().trim()
    }

    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        initialization()

        if (!userEmail!!.isEmpty()) {

            val params = JsonObject()
            try {
                params.addProperty("email", userEmail)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            Log.d("UserData", params.toString())

            mViewModel.forgotPass(params)

            mViewModel.forgotPassResponse.observe(requireActivity(), Observer {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        loadingDialog.show()
                    }
                    Resource.Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        it.data?.let { data ->

                            val bundle = Bundle()
                            bundle.putString("email",userEmail)


                            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                            navController.navigate(R.id.otpFragment, bundle,options)
                        }
                    }
                    Resource.Status.ERROR -> {
                        loadingDialog.dismiss()
                        DialogHelperClass.errorDialog(requireContext(), it.message!!)
                    }
                }
            })
        }
    }


    fun isValidate(): Boolean {
        if (mViewDataBinding.usermail.text.toString().trim().isEmpty()) {
            mViewDataBinding.root.snackbar(getString(R.string.enter_email))
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mViewDataBinding.usermail.text.toString().trim()).matches()){
            mViewDataBinding.root.snackbar(getString(R.string.invalid_email))
            return  false
        }

        subscribeToNetworkLiveData()
        return true
    }
}

