package com.teamx.raseef.ui.fragments.paymentMethod

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.R
import com.teamx.raseef.baseclasses.BaseFragment
import com.teamx.raseef.databinding.FragmentPaymentMethodBinding
import com.teamx.raseef.dummyData.PaymentMethod
import com.teamx.raseef.ui.fragments.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.teamx.raseef.BR
import com.teamx.raseef.utils.PrefHelper


@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentMethodBinding, LoginViewModel>(),
    OnTopSellerListener {


    override val layoutId: Int get() = R.layout.fragment_payment_method

    override val viewModel: Class<LoginViewModel> get() = LoginViewModel::class.java

    override val bindingVariable: Int get() = BR.viewModel

    var viewListenerL: OnTopSellerListener? = null

    lateinit var paymentAdapter: PaymentAdapter
    lateinit var paymentArrayList: ArrayList<PaymentMethod>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (viewListenerL == null) {
            mViewDataBinding.nextBtn.text = "Save"
        } else {
            mViewDataBinding.nextBtn.text = "Confirm Payment"
        }

        paymentAdapter()

        mViewDataBinding.nextBtn.setOnClickListener {
//            naviagteFragment(R.id.payPalFragment, true);
            if (viewListenerL == null) {
                viewListenerL?.onTopSellerClick(2)
            } else {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            naviagteFragment(R.id.homeFragment, true)
//            viewListenerL.onClickPage(1)
        }


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

        var payTypeVal = "1"
        var payTypeVal2 = PrefHelper.getInstance(requireContext()).paymentMathod

//        dataStoreProvider.paymentType.asLiveData().observe(viewLifecycleOwner) {
//            it?.let {
//                payTypeVal = it
//            }
//        }

        if (payTypeVal2 == "2") {
            paymentArrayList.add(
                PaymentMethod(
                    0, R.drawable.icon_master, getString(R.string.debit_card)
                )
            )
            paymentArrayList.add(
                PaymentMethod(
                    paymentId = 1, R.drawable.ic_pass, "Paypal"
                )
            )
            paymentArrayList.add(
                PaymentMethod(
                    paymentId = 2, R.drawable.icon_cash, "Pay on Arrival", true
                )
            )
        } else if (payTypeVal2 == "1") {
            paymentArrayList.add(
                PaymentMethod(
                    0, R.drawable.icon_master, getString(R.string.debit_card)
                )
            )
            paymentArrayList.add(
                PaymentMethod(
                    paymentId = 1, R.drawable.ic_pass, "Paypal", true
                )
            )
            paymentArrayList.add(
                PaymentMethod(
                    paymentId = 2, R.drawable.icon_cash, "Pay on Arrival"
                )
            )
        } else {
            paymentArrayList.add(
                PaymentMethod(
                    0, R.drawable.icon_master, getString(R.string.debit_card), true
                )
            )
            paymentArrayList.add(
                PaymentMethod(
                    paymentId = 1, R.drawable.ic_pass, "Paypal"
                )
            )
            paymentArrayList.add(
                PaymentMethod(
                    paymentId = 2, R.drawable.icon_cash, "Pay on Arrival"
                )
            )
        }

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.paymentMethodRecyclerview.layoutManager = linearLayoutManager

        paymentAdapter = PaymentAdapter(paymentArrayList, this)
        mViewDataBinding.paymentMethodRecyclerview.adapter = paymentAdapter

    }

    override fun onTopSellerClick(position: Int) {

        try {
            PrefHelper.getInstance(requireContext()).savePaymentMethod("$position")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        when (position) {
            0 -> {
                naviagteFragment(R.id.cart, true)
            }
            1 -> {
//                naviagteFragment(R.id.addCardFrag, true)
            }
        }

    }

    override fun onTopSellerSelectClick(position: Int) {

        for (cat in paymentArrayList) {
            cat.value = false
        }
        paymentArrayList[position].value = true
        try {
            PrefHelper.getInstance(requireContext()).savePaymentMethod("$position")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        paymentAdapter.notifyDataSetChanged()
        Log.d("1235", "onTopSellerSelectClick: ")
    }

}