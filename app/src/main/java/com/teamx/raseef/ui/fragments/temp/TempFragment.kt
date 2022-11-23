package com.teamx.raseef.ui.fragments.temp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.asLiveData
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.databinding.FragmentTempBinding
import com.teamx.raseef.BR
import com.teamx.raseef.constants.NetworkCallPoints.Companion.TOKENER
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TempFragment : BaseFragment<FragmentTempBinding, TempViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_temp
    override val viewModel: Class<TempViewModel>
        get() = TempViewModel::class.java
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

        Handler(Looper.getMainLooper()).postDelayed({
            if (isAdded) {

                dataStoreProvider.token.asLiveData().observe(
                    requireActivity()
                ) {

                    val token = it
                    Log.d("Databsae Token ", token.toString())
                    Log.d("Databsae Token ", token.toString())
                    /*NetworkCallPointsNest.*/TOKENER = token.toString()

                    if (token == null) {
                        navController =
                            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        navController.navigate(R.id.logInFragment, null, options)


                    } else {
                        navController =
                            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        navController.navigate(R.id.shopHomePageFragment, null, options)


                    }
                }

            }

        }, 2000)

//
//        Handler(Looper.getMainLooper()).postDelayed({
//            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
//            navController.navigate(R.id.introFragment, null,options)
//
//        }, 2000)


        clickListener()
    }

    private fun clickListener() {

    }

}