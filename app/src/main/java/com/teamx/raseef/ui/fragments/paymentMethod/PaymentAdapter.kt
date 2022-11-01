package com.teamx.raseef.ui.fragments.paymentMethod

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.databinding.ItemPaymentBinding
import com.teamx.raseef.dummyData.PaymentMethod

class PaymentAdapter(val arrayList: ArrayList<PaymentMethod>, val onTopSellerListener: OnTopSellerListener)
    :RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    var row_index = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemPaymentViewedBinding =   ItemPaymentBinding.inflate(inflater,parent,false)
        return PaymentViewHolder(itemPaymentViewedBinding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment : PaymentMethod = arrayList[position]
        holder.binding.paymentName.text = payment.paymentName
        holder.binding.paymentImage.setImageResource(payment.paymentImage)
        holder.binding.paymentType.setChecked(row_index == position)
//        holder.binding.paymentType.isChecked = payment.value

        holder.itemView.setOnClickListener {
            onTopSellerListener.onTopSellerClick(position)
            row_index = position

            notifyDataSetChanged()
        }

    }



    override fun getItemCount(): Int {
        return if (arrayList == null) 0 else arrayList.size
    }

    class PaymentViewHolder(itemPaymentViewedBinding : ItemPaymentBinding) : RecyclerView.ViewHolder(itemPaymentViewedBinding.root){

        val binding = itemPaymentViewedBinding


    }

}