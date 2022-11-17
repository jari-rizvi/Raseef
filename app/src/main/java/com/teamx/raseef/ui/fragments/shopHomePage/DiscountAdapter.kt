package com.teamx.raseef.ui.fragments.shopHomePage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.raseef.data.dataclasses.dashboard.PopularShop
import com.teamx.raseef.databinding.ItemProductsBinding
import com.teamx.raseef.ui.fragments.Home.OnTopShopListener

class DiscountAdapter(val arrayList: ArrayList<PopularShop>, val onTopShopListener: OnTopShopListener) : RecyclerView.Adapter<DiscountAdapter.TopShopViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopShopViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemTopShopBinding = ItemProductsBinding.inflate(inflater, parent, false)
        return TopShopViewHolder(itemTopShopBinding)

    }

    override fun onBindViewHolder(holder: TopShopViewHolder, position: Int) {
        val shop : PopularShop = arrayList[position]
        holder.binding.name.text = shop.name
        holder.binding.type.text = shop.slug
        holder.binding.rating.text = shop.rating.toFloat().toString()
        Picasso.get().load(shop.logo).into(holder.binding.img)


        holder.itemView.setOnClickListener {
            onTopShopListener.onTopshopClick(position)
        }

    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    class TopShopViewHolder(itemTopShopBinding: ItemProductsBinding) : RecyclerView.ViewHolder(itemTopShopBinding.root){
        val binding =itemTopShopBinding

    }
}