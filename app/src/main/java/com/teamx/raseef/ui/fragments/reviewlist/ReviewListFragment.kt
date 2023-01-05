package com.teamx.raseef.ui.fragments.reviewlist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.teamx.raseef.BR
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.databinding.FragmentReviewlistBinding
import com.teamx.raseef.dataclasses.allreviews.Doc
import com.teamx.raseef.utils.DialogHelperClass

@AndroidEntryPoint
class ReviewListFragment : BaseFragment<FragmentReviewlistBinding, ReviewListViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_reviewlist
    override val viewModel: Class<ReviewListViewModel>
        get() = ReviewListViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var options: NavOptions

    lateinit var reviewListAdapter: ReviewListAdapter
    lateinit var reviewListArrayList: ArrayList<Doc>

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

        initializeAdapter()

        mViewModel.getReviewList(sharedViewModel.productBySlug.value?:"", 0, 10)

        mViewModel.reviewListResponse.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {

                    Log.d("1235", "onViewCreated: success")

                    loadingDialog.dismiss()
                    it.data?.let { data ->
                        reviewListArrayList.clear()
                        reviewListArrayList.addAll(data.docs)
                        reviewListAdapter.notifyDataSetChanged()

                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        })
    }

    private fun initializeAdapter() {
        reviewListArrayList = ArrayList()

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.reviewRecyclerView.layoutManager = linearLayoutManager

        reviewListAdapter = ReviewListAdapter(reviewListArrayList)
        mViewDataBinding.reviewRecyclerView.adapter = reviewListAdapter
    }

}