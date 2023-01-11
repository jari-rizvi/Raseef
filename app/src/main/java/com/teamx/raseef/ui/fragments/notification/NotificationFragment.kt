package com.teamx.raseef.ui.fragments.notification

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.BR
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.databinding.FragmentNotificationBinding
import com.teamx.raseef.dataclasses.notification.Doc
import com.teamx.raseef.dataclasses.notification.NotificationData
import com.teamx.raseef.ui.fragments.Home.NotificationViewModel
import com.teamx.raseef.ui.fragments.login.LoginViewModel
import com.teamx.raseef.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotificationFragment : BaseFragment<FragmentNotificationBinding, NotificationViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_notification
    override val viewModel: Class<NotificationViewModel>
        get() = NotificationViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var options: NavOptions
    lateinit var notificationAdapter: NotificationAdapter
    lateinit var notificationArrayList: ArrayList<Doc>


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

        mViewModel.notification()
        mViewModel.notificationResponse.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                    Log.e("ajdhsdsahkjhsd", "start")

                }

                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let {
                        it.docs.let {

                            notificationArrayList.clear()
                            notificationArrayList.addAll(it)
                            notificationAdapter.notifyDataSetChanged()

                        }
                    }
                }

                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    Log.e("ajdhsdsahkjhsd", "end")

                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        })

        initalizeAdapter()

    }
    private fun initalizeAdapter() {

        notificationArrayList = ArrayList()
//        notificationArrayList.add(NotificationData("Your Order has been Placed successfully.","2 days ago",R.drawable.notification,"Smiley’s Store, #1982984"))
//        notificationArrayList.add(NotificationData("Your Order has been Placed successfully.","2 days ago",R.drawable.notification,"Smiley’s Store, #1982984"))
//        notificationArrayList.add(NotificationData("Your Order has been Placed successfully.","2 days ago",R.drawable.notification,"Smiley’s Store, #1982984"))
//        notificationArrayList.add(NotificationData("Your Order has been Placed successfully.","2 days ago",R.drawable.notification,"Smiley’s Store, #1982984"))
//

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.notificationRecyclerView.layoutManager = linearLayoutManager

        notificationAdapter = NotificationAdapter(context,notificationArrayList)
        mViewDataBinding.notificationRecyclerView.adapter = notificationAdapter
    }




}