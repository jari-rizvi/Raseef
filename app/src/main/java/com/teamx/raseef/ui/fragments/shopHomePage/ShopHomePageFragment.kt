package com.teamx.raseef.ui.fragments.shopHomePage

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
import com.squareup.picasso.Picasso
import com.teamx.raseef.BR
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.data.dataclasses.dashboard.PopularShop
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.databinding.*
import com.teamx.raseef.dummyData.Categories
import com.teamx.raseef.ui.fragments.Home.OnTopProductListener
import com.teamx.raseef.ui.fragments.Home.OnTopShopListener
import com.teamx.raseef.utils.DialogHelperClass
import com.teamx.raseef.data.models.productsShop.Doc
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class ShopHomePageFragment() : BaseFragment<FragmentShopHomePageBinding, ShopBySlugViewModel>(),
    OnTopProductListener, OnTopCategoriesListener{

    override val layoutId: Int
        get() = R.layout.fragment_shop_home_page
    override val viewModel: Class<ShopBySlugViewModel>
        get() = ShopBySlugViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var options: NavOptions

    lateinit var categoriesAdapter: CategoriesAdapter
    lateinit var categoriesArrayList2: ArrayList<Categories>

    lateinit var productAdapter: ProductByShopAdapter
    lateinit var productArrayList: ArrayList<Doc>



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

        val r = sharedViewModel.shopById

        r.observe(requireActivity()) {
            mViewModel.productsByShopId(it)
        }

        val str = sharedViewModel.shopBySlug

        str.observe(requireActivity()) {
            Log.d("shopByslug", "onViewCreated: $it")
            mViewModel.shopBySlug(it)
        }

        mViewModel.shopBySlugResponse.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()

                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let {
                        it.let {
                            Picasso.get().load(it.cover_image).into(mViewDataBinding.img)
                            Picasso.get().load(it.cover_image).into(mViewDataBinding.shopImg)
                            mViewDataBinding.shopName.text = it.name
                            mViewDataBinding.shopDistance.text = it.address.street_address
                            mViewDataBinding.ratingBar.rating = it.rating.toFloat()
                            mViewDataBinding.totalRating.text = it.ratings_count.toString() + " + Ratings"
                        }
                    }
                }

                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        })


        mViewModel.productsByShopResponse.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                    Log.e("ajdhsdsahkjhsd","start")

                }

                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let {
                        it.let {
                            productArrayList.addAll(it.docs)
                            productAdapter.notifyDataSetChanged()


//                            discountArrayList.addAll(it.docs)
//                            discountAdapter.notifyDataSetChanged()

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


        productRecyclerview()
        categoriesRecyclerview()


    }

    private fun productRecyclerview() {
        productArrayList = ArrayList()

        val linearLayoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL, false)
        mViewDataBinding.ProductRecycler.layoutManager = linearLayoutManager

        productAdapter = ProductByShopAdapter(productArrayList,this)
        mViewDataBinding.ProductRecycler.adapter = productAdapter

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
        sharedViewModel.setProductBySlug(productArrayList[position].slug)

        navController = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )
        navController.navigate(R.id.productPreviewFragment, null, options)
    }

    override fun onTopSellerClick(position: Int) {
        for(cat in categoriesArrayList2){
            cat.isChecked = false
        }


        categoriesArrayList2.get(position).isChecked = true

        categoriesAdapter.notifyDataSetChanged()
    }



}