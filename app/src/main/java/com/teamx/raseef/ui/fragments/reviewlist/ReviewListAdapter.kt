package com.teamx.raseef.ui.fragments.reviewlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.databinding.ItemReviewlistBinding
import com.teamx.raseef.dataclasses.allreviews.Doc

class ReviewListAdapter(private val reviewArrayList: ArrayList<Doc>) :
    RecyclerView.Adapter<OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(ItemReviewlistBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {

        val ratingList: Doc = reviewArrayList[position]

        holder.bind.textView26.text = ratingList._id
        holder.bind.tvName.text = ratingList.product.name
        holder.bind.textView31.text = ratingList.product.name

        holder.bind.userReview.text = ratingList.comment
        holder.bind.userName.text = ratingList.user.name

//        holder.itemView.setOnClickListener{
//            onOrderListListener.OnOrderClickListener(position)
//        }

    }

    override fun getItemCount(): Int {
        return reviewArrayList.size
    }
}

class OrderViewHolder(private var binding: ItemReviewlistBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val bind = binding

}