package com.teamx.raseef.ui.fragments.product

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.teamx.raseef.dataclasses.allreviews.Doc
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.raseef.BR
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.data.dataclasses.dashboard.PopularProduct
import com.teamx.raseef.data.dataclasses.dashboard.VariationOption
import com.teamx.raseef.data.dataclasses.dashboard.VariationS
import com.teamx.raseef.data.models.MusicModel
import com.teamx.raseef.data.remote.Resource
import com.teamx.raseef.databinding.*
import com.teamx.raseef.ui.fragments.Home.OnTopProductListener
import com.teamx.raseef.ui.fragments.product.variation.Categories
import com.teamx.raseef.ui.fragments.product.variation.ChangePriceVariation
import com.teamx.raseef.ui.fragments.product.variation.VariationCategoryAdapter
import com.teamx.raseef.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductPreviewFragment : BaseFragment<FragmentProductBinding, ProductPreviewViewModel>(),
    OnTopProductListener, ChangePriceVariation {

    override val layoutId: Int
        get() = R.layout.fragment_product
    override val viewModel: Class<ProductPreviewViewModel>
        get() = ProductPreviewViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var options: NavOptions

    lateinit var reviewListAdapter: ShortReviewAdapter
    private lateinit var reviewListArrayList: ArrayList<Doc>

    lateinit var productAdapter: ProductAdapter
    lateinit var productArrayList: ArrayList<PopularProduct>

    var adapter: VariationCategoryAdapter? = null
    var cats: ArrayList<Categories> = ArrayList()
    var variationOptionsButtons: ArrayList<VariationOption> = ArrayList()

    lateinit var variationButtons: ArrayList<VariationS>

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

        mViewDataBinding.btnReview.setOnClickListener {
            mViewDataBinding.reviewRecyclerView.visibility = View.VISIBLE
            mViewDataBinding.productDescriptio.visibility = View.GONE
            mViewDataBinding.btnupp.visibility = View.VISIBLE
            mViewDataBinding.btnReview.visibility = View.GONE
        }

        mViewDataBinding.btnupp.setOnClickListener {
            mViewDataBinding.reviewRecyclerView.visibility = View.GONE
            mViewDataBinding.btnReview.visibility = View.VISIBLE
            mViewDataBinding.btnupp.visibility = View.GONE
        }

        mViewDataBinding.btnDesc.setOnClickListener {
            mViewDataBinding.productDescriptio.visibility = View.VISIBLE
            mViewDataBinding.reviewRecyclerView.visibility = View.GONE
            mViewDataBinding.btnup.visibility = View.VISIBLE
            mViewDataBinding.btnDesc.visibility = View.GONE
        }

        mViewDataBinding.btnup.setOnClickListener {
            mViewDataBinding.productDescriptio.visibility = View.GONE
            mViewDataBinding.btnup.visibility = View.GONE
            mViewDataBinding.btnDesc.visibility = View.VISIBLE
        }


        mViewDataBinding.btnAddToCart.setOnClickListener {

            mViewModel.productPreviewResponse.value?.data?.let {

                mViewModel.insertCartProduct(MusicModel(0, it))

                navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

                navController.navigate(R.id.cartFragment, null, options)

                Toast.makeText(requireContext(), "Added To Cart Successfully", Toast.LENGTH_SHORT)
                    .show()
            }

        }


        mViewDataBinding.btnButnow.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.checkoutFragment, null, options)
        }

        val str = sharedViewModel.productBySlug
//        mViewModel.productPreview("62a3633db41c6704082d77a7")

        str.observe(requireActivity()) {
            Log.d("123", "onViewCreated: $it")
            mViewModel.productPreview(it)
        }

        mViewModel.productPreviewResponse.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }

                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let { data ->
                        mViewDataBinding.productName.text = data.name
                        mViewDataBinding.productDescriptio.text = data.description
                        mViewDataBinding.productType.text = data.slug
                        mViewDataBinding.ratingBar.rating = data.ratings.toFloat()
                        mViewDataBinding.productPrice.text = data.price.toString()

                        var bool = false

                        if (data.product_type == "variable") {

                            cats.clear()

                            for (i in data.variations) {

                                variationButtons = ArrayList()

                                for (cat in cats.indices) {

                                    if (cats[cat].title == i.attribute.name) {


                                        bool = true

                                        cats[cat].variation.add(i)

                                    }
                                }

                                variationButtons.add(i)

                                if (!bool) {
                                    variationButtons[0].boolCheck = true
                                    variationButtons[0].priceVariation = data.variation_options[0].price?.toFloat() ?: 0f
                                    priceProductActual = data.variation_options.get(0)?.price ?: 0.0
                                    mViewDataBinding.productPrice.text = variationButtons[0].priceVariation.toString() + " AED"
                                    cats.add(Categories(i.attribute.name, variationButtons))

                                }
                                bool = false
                            }

                            for (i in data.variation_options) {
                                variationOptionsButtons.add(i)
                            }

                            addCategories()

                        } else {
                            priceProductActual = data.price ?: 0.0
                            mViewDataBinding.productPrice.text = try {
                                data.price.toString() + " AED"
                            } catch (e: Exception) {
                                ""
                            }
                        }

                        Picasso.get().load(data.image).into(mViewDataBinding.img)

                    }
                }

                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        })

        mViewModel.getReviewList(sharedViewModel.productBySlug.value!!, 0, 10)

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
                            productArrayList.addAll(it.popularProducts)
                            productAdapter.notifyDataSetChanged()

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

        reviewAdapter()

        productRecyclerview()

        addCategories()

    }

    private fun reviewAdapter(){
        reviewListArrayList = ArrayList()
        ""

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.reviewRecyclerView.layoutManager = linearLayoutManager

        reviewListAdapter = ShortReviewAdapter(reviewListArrayList)
        mViewDataBinding.reviewRecyclerView.adapter = reviewListAdapter

    }

    private fun productRecyclerview() {
        productArrayList = ArrayList()

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        mViewDataBinding.ProductRecycler.layoutManager = linearLayoutManager

        productAdapter = ProductAdapter(productArrayList, this)
        mViewDataBinding.ProductRecycler.adapter = productAdapter

    }

    private fun addCategories() {
        adapter = VariationCategoryAdapter(cats, this)
        mViewDataBinding.recyclerView2.adapter = adapter
    }

    override fun onTopproductClick(position: Int) {
        TODO("Not yet implemented")
    }

    var priceProductActual = 0.0
    override fun onVariationClicked(cats: ArrayList<Categories>, title: String) {

        for (i in variationOptionsButtons) {
            if (i.title == title) {
                priceProductActual = i.price
                mViewDataBinding.productPrice.text = try {
                    "${i.price}   AED$title"
                } catch (e: Exception) {
                    ""
                }
            }
        }

    }

}