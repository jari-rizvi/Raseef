package com.teamx.rassef.ui.fragments.login

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.google.gson.JsonObject
import com.teamx.raseef.R
import com.teamx.raseef.BR
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.databinding.FragmentSignupBinding
import com.teamx.raseef.ui.fragments.singup.SignupViewModel
import com.teamx.raseef.utils.DialogHelperClass
import com.teamx.raseef.utils.snackbar
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException


@AndroidEntryPoint
class SignupFragment() : BaseFragment<FragmentSignupBinding, SignupViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_signup
    override val viewModel: Class<SignupViewModel>
        get() = SignupViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel
    private var userEmail: String? = null
    private var password: String? = null
    private var name: String? = null
    private var userNumber: String? = null


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
        mViewDataBinding.btnSignup.setOnClickListener {
            isValidate()
        }
        mViewDataBinding.btnLogin.setOnClickListener {
            popUpStack()
        }

    }


    private fun initialization() {
        name = mViewDataBinding.etName.getText().toString().trim()
        userEmail = mViewDataBinding.etemail.getText().toString().trim()
        password = mViewDataBinding.etPass.getText().toString().trim()
        userNumber = mViewDataBinding.etMob.getText().toString().trim()

    }

    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        initialization()

        if (!userNumber!!.isEmpty() || !password!!.isEmpty() || name!!.isNotEmpty()) {

            val params = JsonObject()
            try {
                params.addProperty("name", name.toString())
                params.addProperty("contact", userNumber.toString())
                params.addProperty("password", password.toString())
                params.addProperty("permission", "customer")
                Log.e("UserData", params.toString())
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            mViewModel.signup(params)

            mViewModel.signupResponse.observe(requireActivity(), Observer {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        loadingDialog.show()
                    }
                    Resource.Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        it.data?.let { data ->

                            val bundle = Bundle()
                            bundle.putString("phone",data.phone_number)
                            bundle.putString("Sid",data.twilio.sid)
                            bundle.putString("otpid",data.id)


                            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                            navController.navigate(R.id.codeFragment, bundle,options)
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

        if (mViewDataBinding.etName.text.toString().trim().isEmpty()) {
            mViewDataBinding.root.snackbar(getString(R.string.enter_name))
            return false
        }
        if (mViewDataBinding.etName.text.toString().trim().length < 3) {
            mViewDataBinding.root.snackbar(getString(R.string.name_must_have_atleast_3_character_long))
            return false
        }

        if (mViewDataBinding.etName.text.toString().trim().length > 15) {
            mViewDataBinding.root.snackbar(getString(R.string.name_maximum))
            return false
        }
        if (mViewDataBinding.etemail.text.toString().trim().isEmpty()) {
            mViewDataBinding.root.snackbar(getString(R.string.enter_email))
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mViewDataBinding.etemail.text.toString().trim()).matches()){
            mViewDataBinding.root.snackbar(getString(R.string.invalid_email))
            return  false
        }
        if (mViewDataBinding.etMob.text.toString().trim().isEmpty()) {
            mViewDataBinding.root.snackbar(getString(R.string.enter_your_password))
            return false
        }
        if (mViewDataBinding.etMob.text.toString().trim().startsWith("0")) {
            mViewDataBinding.root.snackbar(getString(R.string.enter_Number_with_Country_Code))
            return false
        }
        if (mViewDataBinding.etPass.text.toString().trim().isEmpty()) {
            mViewDataBinding.root.snackbar(getString(R.string.enter_your_password))
            return false
        }
        if (mViewDataBinding.etPass.text.toString().trim().length < 8) {
            mViewDataBinding.root.snackbar(getString(R.string.password_8_character))
            return false
        }
        subscribeToNetworkLiveData()
        return true
    }



}