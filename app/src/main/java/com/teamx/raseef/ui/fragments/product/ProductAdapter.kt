package com.teamx.raseef.ui.fragments.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.raseef.data.dataclasses.dashboard.PopularProduct
import com.teamx.raseef.databinding.ItemServiceBinding
import com.teamx.raseef.ui.fragments.Home.OnTopProductListener

class ProductAdapter(val arrayList: ArrayList<PopularProduct>, val onTopProductListener: OnTopProductListener) : RecyclerView.Adapter<ProductAdapter.TopProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemTopProductBinding = ItemServiceBinding.inflate(inflater, parent, false)
        return TopProductViewHolder(itemTopProductBinding)

    }

    override fun onBindViewHolder(holder: TopProductViewHolder, position: Int) {
        val product : PopularProduct = arrayList[position]
        holder.binding.serivceName.text = product.name
        holder.binding.price.text = "AED "+product.price.toString()
        holder.binding.rating.text = product.ratings.toString()
        Picasso.get().load(product.image).into(holder.binding.cvrimg)


        holder.itemView.setOnClickListener {
            onTopProductListener.onTopproductClick(position)
        }

    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    class TopProductViewHolder(itemTopProductBinding: ItemServiceBinding) : RecyclerView.ViewHolder(itemTopProductBinding.root){
        val binding =itemTopProductBinding

    }
}