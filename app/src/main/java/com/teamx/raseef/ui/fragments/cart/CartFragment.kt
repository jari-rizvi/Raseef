package com.teamx.raseef.ui.fragments.cart

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.BR
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.databinding.FragmentCartBinding
import com.teamx.raseef.databinding.FragmentNotificationBinding
import com.teamx.raseef.ui.fragments.login.LoginViewModel
import com.teamx.raseef.ui.fragments.notification.NotificationAdapter
import com.teamx.raseef.ui.fragments.notification.Notifications
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment() : BaseFragment<FragmentCartBinding, LoginViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_cart
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