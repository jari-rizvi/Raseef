package com.teamx.raseef.ui.fragments.userprofile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.squareup.picasso.Picasso
import com.teamx.raseef.BR
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.data.dataclasses.dashboard.PopularProduct
import com.teamx.raseef.data.dataclasses.dashboard.PopularShop
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.databinding.FragmentUserProfileBinding
import com.teamx.raseef.ui.fragments.Home.HomeViewModel
import com.teamx.raseef.ui.fragments.Home.OnTopProductListener
import com.teamx.raseef.ui.fragments.Home.OnTopShopListener
import com.teamx.raseef.ui.fragments.Home.ShopAdapter
import com.teamx.raseef.ui.fragments.product.ProductAdapter
import com.teamx.raseef.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserProfileFragment() : BaseFragment<FragmentUserProfileBinding, HomeViewModel>(),
    OnTopShopListener, OnTopProductListener {

    override val layoutId: Int
        get() = R.layout.fragment_user_profile
    override val viewModel: Class<HomeViewModel>
        get() = HomeViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    lateinit var productAdapter: ProductAdapter
    lateinit var productArrayList: ArrayList<PopularProduct>

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

        mViewDataBinding.btnReview.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.reviewListFragment, null, options)
        }

        mViewDataBinding.btnCurrent.setOnClickListener {

            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.currentOrderFragment, null, options)
        }

        mViewDataBinding.btnPast.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.pastOrderFragment, null, options)
        }

        mViewDataBinding.btnSetting.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.settingsFragment, null, options)
        }
        mViewDataBinding.imgPayment.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.paymentFragment, null, options)
        }
        mViewDataBinding.imgSupport.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.settingsFragment, null, options)
        }


        val acct = GoogleSignIn.getLastSignedInAccount(requireContext())

        if (acct != null) {
            val personName = acct.displayName
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto = acct.photoUrl


            mViewDataBinding.userName.setText(personName)
            mViewDataBinding.userPhone.setText(personEmail)
            Picasso.get().load(personPhoto).into(mViewDataBinding.profilePicture)

            Log.d("photooooo", personPhoto.toString())

        }


        mViewDataBinding.btnAddPicture.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.logInFragment, null, null)
        }

        mViewModel.home()

        mViewModel.homeResponse.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                    Log.e("ajdhsdsahkjhsd", "start")

                }

                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let {
                        it.let {


                            shopArrayList.clear()
                            shopArrayList.addAll(it.popularShops)
                            shopAdapter.notifyDataSetChanged()

                            productArrayList.clear()
                            productArrayList.addAll(it.popularProducts)
                            productAdapter.notifyDataSetChanged()

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

        shopRecyclerview()
        keepshopRecyclerview()
    }

    private fun shopRecyclerview() {
        shopArrayList = ArrayList()

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        mViewDataBinding.ShopRecycler.layoutManager = linearLayoutManager

        shopAdapter = ShopAdapter(shopArrayList, this)
        mViewDataBinding.ShopRecycler.adapter = shopAdapter

    }

    private fun keepshopRecyclerview() {
        productArrayList = ArrayList()

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        mViewDataBinding.reviewRecyclerView.layoutManager = linearLayoutManager

        productAdapter = ProductAdapter(productArrayList, this)
        mViewDataBinding.reviewRecyclerView.adapter = productAdapter

    }



    override fun onTopproductClick(position: Int) {

        sharedViewModel.setProductBySlug(productArrayList[position].slug)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        navController.navigate(R.id.productPreviewFragment, null, options)

    }

    override fun onTopshopClick(position: Int) {

        sharedViewModel.setShopBySlug(shopArrayList[position].slug)
        sharedViewModel.setShopById(shopArrayList[position]._id)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        navController.navigate(R.id.shopHomePageFragment, null, options)
    }

}

