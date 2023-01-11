package com.teamx.raseef.ui.fragments.currentOrder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.raseef.data.dataclasses.allorders.DocXX
import com.teamx.raseef.databinding.ItemCurrentorderBinding
import com.teamx.raseef.dataclasses.allorders.DocX

class OrderListAdapter(
    private val orderArrayList: ArrayList<DocXX>,
    private val onOrderListListener: OnOrderListListener
) : RecyclerView.Adapter<OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            ItemCurrentorderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {

//        val orderList: DocX = orderArrayList[position]
//        holder.bind.orderId.text = "Order Id # " + orderList._id.dropLast(18)
//        holder.bind.orderQty.text = "Qty: " + orderList.products!![0].product_id.quantity.toString()
//        holder.bind.ProductName.text = orderList.products!![0].product_id.name.toString()
//        holder.bind.orderType.text = orderList.products!![0].product_id.type.dropLast(18)
//        holder.bind.orderPrice.text = orderList.products!![0].product_id.price.toString()
//        holder.bind.price.text = "AED "+ orderList.products!![0].product_id.price.toString()
//        holder.bind.pickupdate.text = orderList.delivery_time.toString()
//        holder.bind.note.text = orderList.delivery_time.toString()
//        Picasso.get().load(orderList.products!![0].product_id.image).into(holder.bind.img)

        val orderList: DocXX = orderArrayList[position]
        holder.bind.orderId.text = try {
            "Order Id # " + orderList._id.dropLast(18)
        } catch (e: Exception) {
            ""
        }
        holder.bind.orderQty.text = try {
            orderList.products.get(0).product_id.quantity.toString()
        } catch (e: Exception) {
            ""
        }
        holder.bind.ProductName.text = try {
            orderList.products.get(0).product_id.name.toString()
        } catch (e: Exception) {
            ""
        }
        holder.bind.orderType.text = try {
            orderList.products.get(0).product_id.type.dropLast(18)
        } catch (e: Exception) {
            ""
        }
        holder.bind.orderPrice.text = try {
            "Aed " + orderList.products.get(0).product_id.price.toString()
        } catch (e: Exception) {
            ""
        }
        try {
            Picasso.get().load(orderList.products.get(0).product_id.image).into(holder.bind.img)
        }catch (e: Exception){

        }


//        holder.bind.btnReview.setOnClickListener {
//            onOrderListListener.onAddReviewClickListener(orderList._id)
//        }

        holder.itemView.setOnClickListener {
            onOrderListListener.OnOrderClickListener(orderList._id)
        }



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