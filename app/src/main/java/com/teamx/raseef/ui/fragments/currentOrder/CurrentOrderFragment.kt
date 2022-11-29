package com.teamx.raseef.ui.fragments.currentOrder

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.teamx.raseef.BR
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.databinding.FragmentCurrentordersBinding
import com.teamx.raseef.dataclasses.allorders.DocX
import com.teamx.raseef.dummyData.OrderList
import com.teamx.raseef.utils.DialogHelperClass


@AndroidEntryPoint
class CurrentOrderFragment : BaseFragment<FragmentCurrentordersBinding, CurrentOrderListViewModel>(),
    OnOrderListListener {
    override val layoutId: Int
        get() = R.layout.fragment_currentorders
    override val viewModel: Class<CurrentOrderListViewModel>
        get() = CurrentOrderListViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


    lateinit var orderListAdapter: OrderListAdapter
    lateinit var orderListArrayList: ArrayList<DocX>


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

        mViewModel.getOrderList(0, 10)

        mViewModel.orderListResponse.observe(requireActivity()) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let { data ->

                        orderListArrayList.clear()
                        orderListArrayList.addAll(data.docs)
                        orderListAdapter.notifyDataSetChanged()

                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        }

        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()
        }

        initializeAdapter()


    }

    private fun initializeAdapter() {

        orderListArrayList = ArrayList()

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.currentOrderRecyclerView.layoutManager = linearLayoutManager

        orderListAdapter = OrderListAdapter(orderListArrayList,this)
        mViewDataBinding.currentOrderRecyclerView.adapter = orderListAdapter
    }

    override fun OnOrderClickListener(position: String) {
//        val bundle = Bundle()
//        bundle.putString(
//            "itemId", position
//        ).toString()
//        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
//        navController.navigate(R.id.orderDetailFragment, bundle, options)
    }

    override fun onAddReviewClickListener(position: String) {
        TODO("Not yet implemented")
    }


}