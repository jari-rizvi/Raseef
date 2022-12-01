package com.teamx.raseef.ui.fragments.checkout

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.BR
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.databinding.FragmentCheckoutBinding
import com.teamx.raseef.dummyData.PaymentMethod
import com.teamx.raseef.ui.fragments.login.LoginViewModel
import com.teamx.raseef.ui.fragments.paymentMethod.OnTopSellerListener
import com.teamx.raseef.ui.fragments.paymentMethod.PaymentAdapter
import com.teamx.raseef.utils.PrefHelper
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CheckoutFragment() : BaseFragment<FragmentCheckoutBinding, LoginViewModel>(),
    OnTopSellerListener {

    override val layoutId: Int
        get() = R.layout.fragment_checkout
    override val viewModel: Class<LoginViewModel>
        get() = LoginViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var options: NavOptions

    lateinit var paymentAdapter: PaymentAdapter
    lateinit var paymentArrayList: ArrayList<PaymentMethod>

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

        mViewDataBinding.btnPaynow.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.orderSucessFragment, null, options)

        }

        paymentAdapter();

    }

    private fun paymentAdapter(){
        paymentArrayList = ArrayList()
        if(PrefHelper.getInstance(requireContext()).payment.equals(1)){
            paymentArrayList.add(PaymentMethod(1,R.drawable.icon_master,getString(R.string.debit_card)))
            paymentArrayList.add(PaymentMethod(paymentId =  2,R.drawable.icon_cash,"Cash",true))
        }
        else{
            paymentArrayList.add(PaymentMethod(1,R.drawable.icon_master,getString(R.string.debit_card),true))
            paymentArrayList.add(PaymentMethod(2,R.drawable.icon_cash,"Cash"))
        }

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.paymentMethodRecyclerview.setLayoutManager(linearLayoutManager)

        paymentAdapter = PaymentAdapter(paymentArrayList,this)
        mViewDataBinding.paymentMethodRecyclerview.adapter = paymentAdapter

    }

    override fun onTopSellerClick(position: Int) {

        PrefHelper.getInstance(requireContext()).savePayment(position)

        for(cat in paymentArrayList){
            cat.value = false }
        paymentArrayList.get(position).value = true
        paymentAdapter.notifyDataSetChanged()

    }


}