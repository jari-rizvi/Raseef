package com.teamx.rassef.ui.fragments.login

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.teamx.raseef.R
import com.teamx.raseef.BR
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.databinding.FragmentAllowLocationBinding
import com.teamx.raseef.ui.fragments.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AllowLocationFragment() : BaseFragment<FragmentAllowLocationBinding, LoginViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_allow_location
    override val viewModel: Class<LoginViewModel>
        get() = LoginViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


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

        mViewDataBinding.btnAllowLocation.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.homeFragment, null, options)
        }
        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()
        }


    }


}