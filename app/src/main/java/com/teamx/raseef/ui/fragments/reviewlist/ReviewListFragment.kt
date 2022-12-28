package com.teamx.raseef.ui.fragments.reviewlist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.dummyData.PaymentMethod
import com.teamx.raseef.ui.fragments.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.teamx.raseef.BR
import com.teamx.raseef.databinding.FragmentReviewlistBinding
import com.teamx.raseef.ui.fragments.pastOrder.PastOrderAdapter
import com.teamx.raseef.ui.fragments.paymentMethod.PaymentAdapter


@AndroidEntryPoint
class ReviewListFragment : BaseFragment<FragmentReviewlistBinding, LoginViewModel>(){
    override val layoutId: Int
        get() = R.layout.fragment_reviewlist
    override val viewModel: Class<LoginViewModel>
        get() = LoginViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


    private lateinit var notificationAdapter: ReviewListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initalizeAdapter()
    }


    private fun initalizeAdapter() {

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        mViewDataBinding.reviewRecyclerView.layoutManager = linearLayoutManager

        notificationAdapter = ReviewListAdapter(context)

        mViewDataBinding.reviewRecyclerView.adapter = notificationAdapter

    }


}