package com.teamx.raseef.ui.fragments.currentOrder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.raseef.databinding.ItemCurrentorderBinding
import com.teamx.raseef.dataclasses.allorders.DocX

class OrderListAdapter(
    val orderArrayList: ArrayList<DocX>,
    val onOrderListListener: OnOrderListListener
) : RecyclerView.Adapter<OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(ItemCurrentorderBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {

        val orderList: DocX = orderArrayList[position]
        holder.bind.orderId.text = "Order Id # " + orderList._id.dropLast(18)
        holder.bind.orderQty.text = "Qty: " +orderList.products.get(0).product_id.quantity.toString()
        holder.bind.ProductName.text = orderList.products.get(0).product_id.name.toString()
        holder.bind.orderType.text = orderList.products.get(0).product_id.type.dropLast(18)
        holder.bind.orderPrice.text = orderList.products.get(0).product_id.price.toString()
        holder.bind.price.text = "AED "+orderList.products.get(0).product_id.price.toString()
        holder.bind.pickupdate.text = orderList.delivery_time.toString()
        holder.bind.note.text = orderList.delivery_time.toString()
        Picasso.get().load(orderList.products.get(0).product_id.image).into(holder.bind.img)



        holder.itemView.setOnClickListener {
            onOrderListListener.OnOrderClickListener(orderList._id)
        }

    }

    override fun getItemCount(): Int {
        return orderArrayList.size
    }
}

class OrderViewHolder(private var binding: ItemCurrentorderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val bind = binding

}