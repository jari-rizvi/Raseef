package com.teamx.raseef.ui.fragments.reviewlist

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.ui.fragments.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.teamx.raseef.BR
import com.teamx.raseef.databinding.FragmentReviewlistBinding


@AndroidEntryPoint
class ReviewListFragment : BaseFragment<FragmentReviewlistBinding, LoginViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_reviewlist
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
                popEnter = R.anim.enter_from_left
                popExit = R.anim.exit_to_left
            }
        }

        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()
        }

    }

}