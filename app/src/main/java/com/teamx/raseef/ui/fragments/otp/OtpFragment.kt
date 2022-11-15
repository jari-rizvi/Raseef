package com.teamx.rassef.ui.fragments.login

import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.google.gson.JsonObject
import com.teamx.raseef.R
import com.teamx.raseef.BR
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.databinding.FragmentOtpBinding
import com.teamx.raseef.ui.fragments.login.LoginViewModel
import com.teamx.raseef.ui.fragments.otp.OtpViewModel
import com.teamx.raseef.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException


@AndroidEntryPoint
class OtpFragment() : BaseFragment<FragmentOtpBinding, OtpViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_otp
    override val viewModel: Class<OtpViewModel>
        get() = OtpViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


    private var phoneNumber: String? = null
    private var sid: String? = null
    private var otpid: String? = null

    private lateinit var options: NavOptions


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

            verifyotp()
        }

        mViewDataBinding.btnResend.setOnClickListener {
            resendOtp()

        }

    }


    fun initialization() {
        val bundle = arguments
        if (bundle != null) {
            phoneNumber = bundle.getString("phone").toString()
            sid = bundle.getString("Sid").toString()
            otpid = bundle.getString("otpid")

        }
    }

    fun verifyotp() {
        initialization()

        val code = mViewDataBinding.pinView.text.toString()
        if (sid!!.isNotEmpty() || otpid!!.isNotEmpty() || phoneNumber!!.isNotEmpty()) {
            val params = JsonObject()
            try {
                params.addProperty("sid", sid.toString())
                params.addProperty("otp_id", otpid.toString())
                params.addProperty("phone_number", phoneNumber.toString())
                params.addProperty("code", code)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            mViewModel.otpVerify(params)

            mViewModel.otpVerifyResponse.observe(requireActivity(), Observer {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        loadingDialog.show()
                    }
                    Resource.Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        it.data?.let { data ->

                            navController = Navigation.findNavController(
                                requireActivity(),
                                R.id.nav_host_fragment
                            )
                            navController.navigate(R.id.userProfileFragment, null, options)

                        }
                    }
                    Resource.Status.ERROR -> {
                        loadingDialog.dismiss()
//                        DialogHelperClass.errorDialog(ContentProviderCompat.requireContext(), it.message!!)
                    }
                }
            })


        }
    }

    fun resendOtp() {

        initialization()

        if (phoneNumber!!.isNotEmpty()) {
            val params = JsonObject()
            try {
                params.addProperty("contact", phoneNumber)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            mViewModel.resendOtp(params)

            mViewModel.resendOtpResponse.observe(requireActivity(), Observer {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        loadingDialog.show()
                    }
                    Resource.Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        it.data?.let { data ->
                            showToast(data.message)

                        }
                    }
                    Resource.Status.ERROR -> {
                        loadingDialog.dismiss()
//                        DialogHelperClass.errorDialog(ContentProviderCompat.requireContext(), it.message!!)
                    }
                }
            })


        }
    }



}