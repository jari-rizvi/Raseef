package com.teamx.raseef.ui.fragments.language

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.databinding.FragmentLanguageBinding
import com.teamx.raseef.ui.fragments.login.LoginViewModel
import com.teamx.raseef.BR
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LanguageFragment() : BaseFragment<FragmentLanguageBinding, LoginViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_language
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

    }




}