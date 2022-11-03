//package com.teamx.raseef.ui.fragments.Home
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.squareup.picasso.Picasso
//import com.teamx.raseef.data.dataclasses.dashboard.PopularProduct
//import com.teamx.raseef.databinding.ItemTopProductBinding
//
//class ProductAdapter(val arrayList: ArrayList<PopularProduct>, val onTopProductListener: OnTopProductListener ) : RecyclerView.Adapter<ProductAdapter.TopProductViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopProductViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val itemTopProductBinding = ItemTopProductBinding.inflate(inflater, parent, false)
//        return TopProductViewHolder(itemTopProductBinding)
//
//    }
//
//    override fun onBindViewHolder(holder: TopProductViewHolder, position: Int) {
//        val product : PopularProduct = arrayList[position]
//        holder.binding.productName.text = product.name
//        holder.binding.productPrice.text = "AED "+product.price.toString()
//        holder.binding.productType.text = product.product_type
//        holder.binding.productType .text = product.product_type
//        holder.binding.productRating.rating = product.ratings.toFloat()
//        Picasso.get().load(product.image).into(holder.binding.productImage)
//
//
//        holder.itemView.setOnClickListener {
//            onTopProductListener.onTopproductClick(position)
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//       return arrayList.size
//    }
//
//    class TopProductViewHolder(itemTopProductBinding: ItemTopProductBinding) : RecyclerView.ViewHolder(itemTopProductBinding.root){
//        val binding =itemTopProductBinding
//
//    }
//}