package com.teamx.raseef.ui.fragments.shopHomePage

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.teamx.raseef.BR
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.databinding.*
import com.teamx.raseef.ui.fragments.Home.OnTopProductListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class shopHomePageFragment() : BaseFragment<FragmentShopHomePageBinding, ShopBySlugViewModel>(),
    OnTopProductListener {

    override val layoutId: Int
        get() = R.layout.fragment_shop_home_page
    override val viewModel: Class<ShopBySlugViewModel>
        get() = ShopBySlugViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var options: NavOptions

//    lateinit var productAdapter: ProductByShopAdapter
//    lateinit var productArrayList: ArrayList<Doc>

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

    override fun onTopproductClick(position: Int) {
//        sharedViewModel.setProductBySlug(productArrayList[position].slug)
//
//        navController = Navigation.findNavController(
//            requireActivity(),
//            R.id.nav_host_fragment
//        )
//        navController.navigate(R.id.productFragment, null, options)
    }
}