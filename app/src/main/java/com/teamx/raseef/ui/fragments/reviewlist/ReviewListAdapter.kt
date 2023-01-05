package com.teamx.raseef.ui.fragments.reviewlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.raseef.databinding.ItemReviewlistBinding
import com.teamx.raseef.dataclasses.allreviews.Doc

class ReviewListAdapter(private val reviewArrayList: ArrayList<Doc>) :
    RecyclerView.Adapter<OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            ItemReviewlistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {

        val ratingList: Doc = reviewArrayList[position]

        holder.bind.textView26.text = "Qty: " + ratingList._id
        holder.bind.tvName.text = ratingList.product.name
        holder.bind.textView31.text = ratingList.product.product_type
        holder.bind.qty.text = "Qty: " + ratingList.product.quantity
        holder.bind.textView38.text = ratingList.product.status
        holder.bind.price.text = ratingList.product.price.toString()
        holder.bind.tvNote.text = ratingList.product.description

        Picasso.get().load(ratingList.product.image).into(holder.bind.imageView10)

        Picasso.get().load(ratingList.user.profile.avatar).into(holder.bind.notificationImage)

        holder.bind.userReview.text = ratingList.comment
        holder.bind.userName.text = ratingList.user.name
        holder.bind.months.text = ratingList.user.createdAt
        holder.bind.ratingBar.rating = ratingList.rating.toFloat()


//        holder.itemView.setOnClickListener{
//            onOrderListListener.OnOrderClickListener(position)
//        }

    }

    override fun getItemCount(): Int {
        return reviewArrayList.size
    }
}

class OrderViewHolder(binding: ItemReviewlistBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val bind = binding

}