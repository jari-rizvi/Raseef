package com.teamx.raseef.ui.fragments.product

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.teamx.raseef.BR
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.databinding.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductPreviewFragment() : BaseFragment<FragmentProductBinding, ProductPreviewViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_product
    override val viewModel: Class<ProductPreviewViewModel>
        get() = ProductPreviewViewModel::class.java
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


        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()
        }


    }


}