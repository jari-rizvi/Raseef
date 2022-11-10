package com.teamx.rassef.ui.fragments.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.squareup.picasso.Picasso
import com.teamx.raseef.BR
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.databinding.FragmentUserProfileBinding
import com.teamx.raseef.ui.fragments.singup.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserProfileFragment() : BaseFragment<FragmentUserProfileBinding, SignupViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_user_profile
    override val viewModel: Class<SignupViewModel>
        get() = SignupViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val acct = GoogleSignIn.getLastSignedInAccount(requireContext())

        if (acct != null) {
            val personName = acct.displayName
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto = acct.photoUrl


            mViewDataBinding.userName.setText(personName)
            mViewDataBinding.userPhone.setText(personEmail)
            Picasso.get().load(personPhoto).into(mViewDataBinding.profilePicture)

            Log.d("photooooo",personPhoto.toString())

        }

    }






}