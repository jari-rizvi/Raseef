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

    private var email: String? = null

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
            verifyotpForgot()
        }

    }

    fun initialization() {
        val bundle = arguments
        if (bundle != null) {
            email = bundle.getString("email").toString()
        }
    }

    fun verifyotpForgot() {
        initialization()

        val code = mViewDataBinding.pinView.text.toString()

        if (email!!.isNotEmpty() ) {
            val params = JsonObject()
            try {
                params.addProperty("email", email)
                params.addProperty("token", code)

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            mViewModel.otpVerifyForgot(params)

            mViewModel.otpVerifyForogtResponse.observe(requireActivity(), Observer {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        loadingDialog.show()
                    }
                    Resource.Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        it.data?.let { data ->
                            val bundle = Bundle()
                            bundle.putString("email", email)
                            bundle.putString("token",code)

                            navController = Navigation.findNavController(
                                requireActivity(),
                                R.id.nav_host_fragment
                            )
                            navController.navigate(R.id.createPassFragment, bundle, options)

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



    }


