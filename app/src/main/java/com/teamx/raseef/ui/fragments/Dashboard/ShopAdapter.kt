package com.teamx.raseef.ui.fragments.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.raseef.data.dataclasses.dashboard.PopularShop
import com.teamx.raseef.databinding.ItemShopBinding

class ShopAdapter(val arrayList: ArrayList<PopularShop>, val onTopShopListener: OnTopShopListener ) : RecyclerView.Adapter<ShopAdapter.TopShopViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopShopViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemTopShopBinding = ItemShopBinding.inflate(inflater, parent, false)
        return TopShopViewHolder(itemTopShopBinding)

    }

    override fun onBindViewHolder(holder: TopShopViewHolder, position: Int) {
        val shop : PopularShop = arrayList[position]
        holder.binding.shopName.text = shop.name
        holder.binding.shopDistance.text = "5m"
        holder.binding.shopAddress.text = shop.address.street_address
        holder.binding.ratingBar.rating = shop.rating.toFloat()
        Picasso.get().load(shop.logo).into(holder.binding.shopImage)


        holder.itemView.setOnClickListener {
            onTopShopListener.onTopshopClick(position)
        }

    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    class TopShopViewHolder(itemTopShopBinding: ItemShopBinding) : RecyclerView.ViewHolder(itemTopShopBinding.root){
        val binding =itemTopShopBinding

    }
}