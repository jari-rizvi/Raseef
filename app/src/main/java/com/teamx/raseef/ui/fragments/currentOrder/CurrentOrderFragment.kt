package com.teamx.raseef.ui.fragments.currentOrder

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
import com.teamx.raseef.databinding.FragmentCurrentordersBinding
import com.teamx.raseef.ui.fragments.paymentMethod.OnTopSellerListener
import com.teamx.raseef.ui.fragments.paymentMethod.PaymentAdapter


@AndroidEntryPoint
class CurrentOrderFragment : BaseFragment<FragmentCurrentordersBinding, LoginViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_currentorders
    override val viewModel: Class<LoginViewModel>
        get() = LoginViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }




}