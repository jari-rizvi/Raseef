package com.teamx.raseef.ui.fragments.intro

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.teamx.raseef.BR
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.data.models.IntroModel
import com.teamx.raseef.databinding.FragmentCartBinding
import com.teamx.raseef.databinding.FragmentEditProfileBinding
import com.teamx.raseef.databinding.FragmentIntroBinding
import com.teamx.raseef.databinding.FragmentNotificationBinding
import com.teamx.raseef.ui.fragments.login.LoginViewModel
import com.teamx.raseef.ui.fragments.notification.NotificationAdapter
import com.teamx.raseef.ui.fragments.notification.Notifications
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class IntroFragment() : BaseFragment<FragmentIntroBinding, LoginViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_intro
    override val viewModel: Class<LoginViewModel>
        get() = LoginViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var options: NavOptions
    lateinit var featureProductAdapter: IntroAdapter
    lateinit var featureProductArrayList: ArrayList<IntroModel>
    private var tabLayoutMediator: TabLayoutMediator? = null


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

        mViewDataBinding.btnNext.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.logInFragment, null, options)
        }

        initializeFeatureProducts()


    }


    private fun initializeFeatureProducts() {

        featureProductArrayList = ArrayList()
        featureProductArrayList.add(IntroModel("Order Head","lksjdalkjdlksjlksajdlksajdkjsalkjdslksjdalkjdlksjlksajdlksajdkjsalkjdsalkjsalkjsadlkjdsaalkjsalkjsadlkjdsa",R.drawable.starbuc))
        featureProductArrayList.add(IntroModel("Order Head","lksjdalkjdlksjlksajdlksajdkjsalkjdlksjdalkjdlksjlksajdlksajdkjsalkjdsalkjsalkjsadlkjdsasalkjsalkjsadlkjdsa",R.drawable.starbuc))
        featureProductArrayList.add(IntroModel("Order Head","lksjdalkjdlksjlksajdlksajdkjsalkjdlksjdalkjdlksjlksajdlksajdkjsalkjdsalkjsalkjsadlkjdsasalkjsalkjsadlkjdsa",R.drawable.starbuc))

        featureProductAdapter = IntroAdapter(featureProductArrayList)
        mViewDataBinding.screenViewpager.adapter = featureProductAdapter

        TabLayoutMediator(mViewDataBinding.tabIndicator, mViewDataBinding.screenViewpager) { tab, position ->
            tab.text = featureProductArrayList[position].toString()




        }.attach()


        tabLayoutMediator = TabLayoutMediator(
            mViewDataBinding.tabIndicator,
            mViewDataBinding.screenViewpager
        ) { tab: TabLayout.Tab, position: Int ->
            mViewDataBinding.screenViewpager.setCurrentItem(tab.position, true)
        }

        tabLayoutMediator!!.attach()
    }




}