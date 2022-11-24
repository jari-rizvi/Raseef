package com.teamx.raseef.ui.fragments.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.BR
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.data.dataclasses.dashboard.PopularProduct
import com.teamx.raseef.data.dataclasses.dashboard.PopularShop
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.databinding.*
import com.teamx.raseef.dummyData.Categories
import com.teamx.raseef.ui.fragments.product.ProductAdapter
import com.teamx.raseef.ui.fragments.shopHomePage.CategoriesAdapter
import com.teamx.raseef.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment() : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    OnTopShopListener {

    override val layoutId: Int
        get() = R.layout.fragment_home
    override val viewModel: Class<HomeViewModel>
        get() = HomeViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


    lateinit var shopAdapter: ShopAdapter
    lateinit var shopArrayList: ArrayList<PopularShop>

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



        mViewModel.home()

        mViewModel.homeResponse.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                    Log.e("ajdhsdsahkjhsd","start")

                }

                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let {
                        it.let {

                            shopArrayList.addAll(it.popularShops)
                            shopAdapter.notifyDataSetChanged()

                        }
                    }
                }

                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    Log.e("ajdhsdsahkjhsd","end")

                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        })

        shopRecyclerview()

    }

    private fun shopRecyclerview() {
        shopArrayList = ArrayList()

        val linearLayoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        mViewDataBinding.ShopRecycler.layoutManager = linearLayoutManager

        shopAdapter = ShopAdapter(shopArrayList, this)
        mViewDataBinding.ShopRecycler.adapter = shopAdapter

    }

    override fun onTopshopClick(position: Int) {
        sharedViewModel.setShopBySlug(shopArrayList[position].slug)
        sharedViewModel.setShopById(shopArrayList[position]._id)


        navController = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )
        navController.navigate(R.id.shopHomePageFragment, null, options)
    }

}

