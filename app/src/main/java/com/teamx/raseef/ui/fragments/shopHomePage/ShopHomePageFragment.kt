package com.teamx.raseef.ui.fragments.shopHomePage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.BR
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.databinding.*
import com.teamx.raseef.dummyData.Categories
import com.teamx.raseef.ui.fragments.Home.OnTopProductListener
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class ShopHomePageFragment() : BaseFragment<FragmentShopHomePageBinding, ShopBySlugViewModel>(),
    OnTopProductListener, OnTopCategoriesListener {

    override val layoutId: Int
        get() = R.layout.fragment_shop_home_page
    override val viewModel: Class<ShopBySlugViewModel>
        get() = ShopBySlugViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var options: NavOptions

    lateinit var categoriesAdapter: CategoriesAdapter
    lateinit var categoriesArrayList2: ArrayList<Categories>


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


        productRecyclerview()
        categoriesRecyclerview()

    }

    private fun productRecyclerview() {
//        productArrayList = ArrayList()
//
//        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
//        mViewDataBinding.ProductRecycler.layoutManager = linearLayoutManager
//
//        productAdapter = ProductByShopAdapter(productArrayList,this)
//        mViewDataBinding.ProductRecycler.adapter = productAdapter

    }


    private fun categoriesRecyclerview() {
        categoriesArrayList2 = ArrayList()
        categoriesArrayList2.add(Categories("Popular", true))
        categoriesArrayList2.add(Categories("Discounts", false))
        categoriesArrayList2.add(Categories("Drinks", false))
        categoriesArrayList2.add(Categories("Snacks",false))

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        mViewDataBinding.CatRecycler.layoutManager = linearLayoutManager

        categoriesAdapter = CategoriesAdapter(categoriesArrayList2,this)
        mViewDataBinding.CatRecycler.adapter = categoriesAdapter
    }


    override fun onTopproductClick(position: Int) {
//        sharedViewModel.setProductBySlug(productArrayList[position].slug)
//
//        navController = Navigation.findNavController(
//            requireActivity(),
//            R.id.nav_host_fragment
//        )
//        navController.navigate(R.id.productFragment, null, options)
    }

    override fun onTopSellerClick(position: Int) {
        for(cat in categoriesArrayList2){
            cat.isChecked = false
        }
    }


}