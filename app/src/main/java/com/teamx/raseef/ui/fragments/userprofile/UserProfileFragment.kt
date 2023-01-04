package com.teamx.raseef.ui.fragments.userprofile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.squareup.picasso.Picasso
import com.teamx.raseef.BR
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.databinding.FragmentUserProfileBinding
import com.teamx.raseef.ui.fragments.singup.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserProfileFragment : BaseFragment<FragmentUserProfileBinding, SignupViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_user_profile
    override val viewModel: Class<SignupViewModel>
        get() = SignupViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var userProfileFragment: UserProfileAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val acct = GoogleSignIn.getLastSignedInAccount(requireContext())

        if (acct != null) {
            val personName = acct.displayName
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto = acct.photoUrl


            mViewDataBinding.userName.text = personName
            mViewDataBinding.userPhone.text = personEmail
            Picasso.get().load(personPhoto).into(mViewDataBinding.profilePicture)

            Log.d("photooooo",personPhoto.toString())

        }

        productRecyclerview()
        productRecyclerview1()

    }


    private fun productRecyclerview() {

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        mViewDataBinding.recyclerView.layoutManager = linearLayoutManager

        userProfileFragment = UserProfileAdapter(context)
        mViewDataBinding.recyclerView.adapter = userProfileFragment

    }

    private fun productRecyclerview1() {

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        mViewDataBinding.recyclerView1.layoutManager = linearLayoutManager

        userProfileFragment = UserProfileAdapter(context)
        mViewDataBinding.recyclerView1.adapter = userProfileFragment

    }



}