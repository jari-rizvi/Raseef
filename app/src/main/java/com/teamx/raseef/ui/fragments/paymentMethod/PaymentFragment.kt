package com.teamx.raseef.ui.fragments.paymentMethod

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.databinding.FragmentPaymentMethodBinding
import com.teamx.raseef.dummyData.PaymentMethod
import com.teamx.raseef.ui.fragments.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.teamx.raseef.BR


@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentMethodBinding, LoginViewModel>(),
    OnTopSellerListener {
    override val layoutId: Int
        get() = R.layout.fragment_payment_method
    override val viewModel: Class<LoginViewModel>
        get() = LoginViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    lateinit var paymentAdapter: PaymentAdapter
    lateinit var paymentArrayList: ArrayList<PaymentMethod>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paymentAdapter();

    }

    private fun paymentAdapter() {
        paymentArrayList = ArrayList()
//        if (PrefHelper.getInstance(requireContext()).payment.equals(1)) {
//            paymentArrayList.add(
//                PaymentMethod(
//                    1,
//                    R.drawable.icon_master,
//                    getString(R.string.debit_card)
//                )
//            )
//            paymentArrayList.add(PaymentMethod(paymentId = 2, R.drawable.icon_cash, "Cash", true))
//        } else {
//            paymentArrayList.add(
//                PaymentMethod(
//                    1,
//                    R.drawable.icon_master,
//                    getString(R.string.debit_card),
//                    true
//                )
//            )
//            paymentArrayList.add(PaymentMethod(2, R.drawable.icon_cash, "Cash"))
//        }

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.paymentMethodRecyclerview.setLayoutManager(linearLayoutManager)

        paymentAdapter = PaymentAdapter(paymentArrayList, this)
        mViewDataBinding.paymentMethodRecyclerview.adapter = paymentAdapter

    }

    override fun onTopSellerClick(position: Int) {

//        PrefHelper.getInstance(requireContext()).savePayment(position)
//
//        for (cat in paymentArrayList) {
//            cat.value = false
//        }
//        paymentArrayList.get(position).value = true
//        paymentAdapter.notifyDataSetChanged()

    }


}